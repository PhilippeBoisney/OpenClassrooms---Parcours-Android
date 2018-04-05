package com.openclassrooms.wonder.controllers.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.openclassrooms.wonder.adapters.CommentAdapter;
import com.openclassrooms.wonder.injection.Injection;
import com.openclassrooms.wonder.injection.ViewModelFactory;
import com.openclassrooms.wonder.models.ApiResponse;
import com.openclassrooms.wonder.viewmodels.CommentsViewModel;
import com.openclassrooms.wonderfuloc.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Philippe on 29/03/2018.
 */

public class CommentsModalFragment extends BottomSheetDialogFragment {

    // FOR DESIGN
    @BindView(R.id.modal_fragment_comments_recycler_view) RecyclerView recyclerView;

    // FOR DATA
    private CommentsViewModel commentsViewModel;
    private CommentAdapter commentAdapter;
    private Disposable disposable;
    private static final String KEY_PROJECT_ID = "KEY_PROJECT_ID";

    // FOR CONSTRUCTING
    public static CommentsModalFragment newInstance(Integer projectId){
        CommentsModalFragment bottomSheetFragment = new CommentsModalFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_PROJECT_ID, projectId);
        bottomSheetFragment .setArguments(bundle);
        return bottomSheetFragment ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_fragment_comments, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.configureViewModel();
        this.configureRecyclerView();
        this.getComments();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -------------------
    // CONFIGURATION
    // -------------------

    private void configureViewModel(){
        Integer projectId = getArguments().getInt(KEY_PROJECT_ID);
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory();
        this.commentsViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CommentsViewModel.class);
        this.commentsViewModel.init(projectId);
    }

    private void configureRecyclerView(){
        this.commentAdapter = new CommentAdapter(new ArrayList<>(), Glide.with(this));
        this.recyclerView.setAdapter(this.commentAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // -------------------
    // DATA
    // -------------------

    private void getComments(){
        this.disposable = this.commentsViewModel.getComments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS)
                .subscribe(this::updateDesign, throwable -> Log.e("TAG", "ERROR: ", throwable));
    }

    // -------------------
    // UI
    // -------------------

    private void updateDesign(ApiResponse projectResponse){
        this.commentAdapter.update(projectResponse.getComments());
    }

    // -------------------
    // UTILS
    // -------------------

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }
}