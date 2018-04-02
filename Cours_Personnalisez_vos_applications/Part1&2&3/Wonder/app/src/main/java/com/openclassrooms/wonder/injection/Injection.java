package com.openclassrooms.wonder.injection;

import com.openclassrooms.wonder.api.BehanceService;
import com.openclassrooms.wonder.repositories.CommentRepository;
import com.openclassrooms.wonder.repositories.ProjectRepository;

/**
 * Created by Philippe on 27/02/2018.
 */

public class Injection {

    public static ProjectRepository provideProjectDataSource() {
        BehanceService behanceService = BehanceService.retrofit.create(BehanceService.class);
        return new ProjectRepository(behanceService);
    }

    public static CommentRepository provideCommentDataSource() {
        BehanceService behanceService = BehanceService.retrofit.create(BehanceService.class);
        return new CommentRepository(behanceService);
    }

    public static ViewModelFactory provideViewModelFactory() {
        ProjectRepository dataSourceProject = provideProjectDataSource();
        CommentRepository dataSourceComment = provideCommentDataSource();

        return new ViewModelFactory(dataSourceProject, dataSourceComment);
    }
}
