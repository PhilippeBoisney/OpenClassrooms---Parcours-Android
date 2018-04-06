package com.openclassrooms.wonder.controllers.fragments;

import android.animation.Animator;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.wonder.base.BaseFragment;
import com.openclassrooms.wonder.controllers.activities.DetailActivity;
import com.openclassrooms.wonder.injection.ViewModelFactory;
import com.openclassrooms.wonder.models.Project;
import com.openclassrooms.wonderfuloc.R;
import com.openclassrooms.wonder.injection.Injection;
import com.openclassrooms.wonder.models.ApiResponse;
import com.openclassrooms.wonder.viewmodels.ProjectDetailViewModel;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends BaseFragment {

    // FOR DATA
    private ProjectDetailViewModel projectDetailViewModel;
    private Project currentProject;

    // FOR DESIGN
    @BindView(R.id.fragment_detail_image) ImageView imageProject;
    @BindView(R.id.fragment_detail_title) TextView titleProject;
    @BindView(R.id.fragment_detail_description) TextView descriptionProject;
    @BindView(R.id.social_view_views) TextView viewsProject;
    @BindView(R.id.social_view_likes) TextView likesProject;
    @BindView(R.id.social_view_comments) TextView commentsProject;
    @BindView(R.id.fragment_detail_root_view) CoordinatorLayout rootView;
    @BindView(R.id.fragment_detail_fab) FloatingActionButton fabButton;

    @Override
    protected int getLayoutId() { return R.layout.fragment_detail; }

    @Override
    protected void updateData() {
        this.configureViewModel();
        this.getProject();
        this.updateDesignWhenStarting();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private void configureViewModel(){
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory();
        this.projectDetailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProjectDetailViewModel.class);
        this.projectDetailViewModel.init(this.getProjectIdFromBundle());
    }

    // -------------------
    // ACTIONS
    // -------------------

    @OnClick(R.id.fragment_detail_share)
    public void onClickShareButton(View view) {
        this.showMessage();
    }

    @OnClick(R.id.social_view_comments_button)
    public void onClickCommentsButton(View view) {
        CommentsModalFragment.newInstance(this.currentProject.getId()).show(getActivity().getSupportFragmentManager(), "MODAL");
    }

    // -------------------
    // DATA
    // -------------------

    private void getProject(){
        this.disposable = this.projectDetailViewModel.getProject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS)
                .subscribe(this::updateDesign, throwable -> Log.e("TAG", "ERROR: ", throwable));
    }

    // -------------------
    // UI
    // -------------------

    private void updateDesignWhenStarting(){
        Glide.with(this).load(this.getImageURLFromBundle()).into(this.imageProject);
    }

    private void updateDesign(ApiResponse projectResponse){
        this.currentProject = projectResponse.getProject();
        this.titleProject.setText(this.currentProject.getName());
        this.descriptionProject.setText(this.currentProject.getDescription());
        this.viewsProject.setText(getString(R.string.detail_fragment_stats_views, this.currentProject.getStats().getViews().toString()));
        this.likesProject.setText(getString(R.string.detail_fragment_stats_likes, this.currentProject.getStats().getAppreciations().toString()));
        this.commentsProject.setText(getString(R.string.detail_fragment_stats_comments, this.currentProject.getStats().getComments().toString()));
    }

    private void showMessage(){
        Snackbar snackbar = Snackbar.make(rootView, R.string.detail_fragment_snackbar_message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    // -------------------
    // UTILS
    // -------------------

    private Integer getProjectIdFromBundle(){
        Bundle bundle = getActivity().getIntent().getExtras();
        return bundle.getInt(DetailActivity.BUNDLE_KEY_PROJECT_ID);
    }

    private String getImageURLFromBundle(){
        Bundle bundle = getActivity().getIntent().getExtras();
        return bundle.getString(DetailActivity.BUNDLE_KEY_PROJECT_IMAGE_URL);
    }
}
