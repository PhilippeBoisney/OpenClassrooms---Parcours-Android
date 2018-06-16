package com.openclassrooms.wonder.repositories;

import com.openclassrooms.wonder.api.BehanceService;
import com.openclassrooms.wonder.models.ApiResponse;

import io.reactivex.Observable;

/**
 * Created by Philippe on 27/03/2018.
 */

public class ProjectRepository {

    private final BehanceService behanceService;

    public ProjectRepository(BehanceService behanceService) {
        this.behanceService = behanceService;
    }

    // ---

    public Observable<ApiResponse> getProjects(String request){
        return this.behanceService.getProjects(request);
    }

    public Observable<ApiResponse> getProject(Integer projectID){
        return this.behanceService.getProject(projectID);
    }
}
