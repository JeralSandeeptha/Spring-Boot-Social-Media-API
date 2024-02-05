package com.jeral.socialmedia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDTO {

    private Long postId;
    private Long userId;
    private String title;
    private String imageUrl;
    private List<Long> likes;
    private Date createdAt;
    private Date updatedAt;
}
