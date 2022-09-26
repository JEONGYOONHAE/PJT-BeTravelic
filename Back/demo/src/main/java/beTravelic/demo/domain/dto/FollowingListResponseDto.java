package beTravelic.demo.domain.dto;

import beTravelic.demo.domain.entity.Follow;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowingListResponseDto {

    private Long id;
    private String following_id;

    public static FollowingListResponseDto of(Follow follow){
        return FollowingListResponseDto.builder()
                .id(follow.getFollow_id())
                .following_id(follow.getFollowing().getId())
                .build();
    }
}
