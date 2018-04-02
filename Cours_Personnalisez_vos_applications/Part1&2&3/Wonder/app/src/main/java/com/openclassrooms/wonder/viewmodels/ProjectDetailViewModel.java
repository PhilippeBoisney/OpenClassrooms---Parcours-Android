package com.openclassrooms.wonder.viewmodels;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.openclassrooms.wonder.models.ApiResponse;
import com.openclassrooms.wonder.repositories.ProjectRepository;

import io.reactivex.Observable;

/**
 * Created by Philippe on 27/03/2018.
 */

public class ProjectDetailViewModel extends ViewModel {

    // REPOSITORIES
    private final ProjectRepository projectRepository;

    // DATA
    @Nullable
    private Observable<ApiResponse> currentProject;

    public ProjectDetailViewModel(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void init(Integer projectId) {
        if (this.currentProject != null) return;
        this.currentProject = this.projectRepository.getProject(projectId);
    }

    // ---

    public Observable<ApiResponse> getProject(){ return this.currentProject; }
}
