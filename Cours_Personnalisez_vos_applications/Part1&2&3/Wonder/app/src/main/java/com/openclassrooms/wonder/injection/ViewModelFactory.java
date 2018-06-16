package com.openclassrooms.wonder.injection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.openclassrooms.wonder.repositories.CommentRepository;
import com.openclassrooms.wonder.repositories.ProjectRepository;
import com.openclassrooms.wonder.viewmodels.CommentsViewModel;
import com.openclassrooms.wonder.viewmodels.ProjectViewModel;
import com.openclassrooms.wonder.viewmodels.ProjectDetailViewModel;

/**
 * Created by Philippe on 27/02/2018.
 */


public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ProjectRepository projectRepository;
    private final CommentRepository commentRepository;

    public ViewModelFactory(ProjectRepository projectRepository, CommentRepository commentRepository) {
        this.projectRepository = projectRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProjectViewModel.class)) {
            return (T) new ProjectViewModel(projectRepository);
        }
        if (modelClass.isAssignableFrom(ProjectDetailViewModel.class)) {
            return (T) new ProjectDetailViewModel(projectRepository);
        }
        if (modelClass.isAssignableFrom(CommentsViewModel.class)) {
            return (T) new CommentsViewModel(commentRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}