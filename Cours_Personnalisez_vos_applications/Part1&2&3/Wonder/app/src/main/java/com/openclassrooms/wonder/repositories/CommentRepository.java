package com.openclassrooms.wonder.repositories;

import com.openclassrooms.wonder.api.BehanceService;
import com.openclassrooms.wonder.models.ApiResponse;

import io.reactivex.Observable;

/**
 * Created by Philippe on 27/03/2018.
 */

public class CommentRepository {

    private final BehanceService behanceService;

    public CommentRepository(BehanceService behanceService) {
        this.behanceService = behanceService;
    }

    // ---

    public Observable<ApiResponse> getComments(Integer projectId){
        return this.behanceService.getComments(projectId);
    }
}
