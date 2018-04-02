package com.openclassrooms.wonder.base;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.wonderfuloc.BuildConfig;
import com.openclassrooms.wonderfuloc.R;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * Created by Philippe on 27/03/2018.
 */

public abstract class BaseFragment extends Fragment {

    // FOR DATA
    protected Disposable disposable;

    public BaseFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        this.updateData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // ---

    protected abstract int getLayoutId();
    protected abstract void updateData();

    // ---

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }
}
