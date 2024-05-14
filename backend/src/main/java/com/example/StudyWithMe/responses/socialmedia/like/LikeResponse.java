package com.example.StudyWithMe.responses.socialmedia.like;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeResponse {
    private long totalLike;
    private boolean isLike;
    public static LikeResponse fromLike(long totalLike,boolean isLike){
        return LikeResponse.builder()
                .totalLike(totalLike)
                .isLike(isLike)
                .build();
    }
}
