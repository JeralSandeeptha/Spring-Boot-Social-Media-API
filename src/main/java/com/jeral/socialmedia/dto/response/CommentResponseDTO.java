package com.jeral.socialmedia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDTO {

    private Long commentId;
    private String comment;
    private Date createdAt;
    private Date updatedAt;
    private Long postId;
    private Long userId;
}
