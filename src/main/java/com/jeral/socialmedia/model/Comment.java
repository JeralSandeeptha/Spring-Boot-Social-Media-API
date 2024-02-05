package com.jeral.socialmedia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    private Long commentId;
    private String comment;
    private Date createdAt;
    private Date updatedAt;
    private Long postId;
    private Long userId;
}
