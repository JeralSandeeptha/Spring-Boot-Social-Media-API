package com.jeral.socialmedia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    private Long postId;
    private Long userId;
    private String title;
    private String imageUrl;
    private List<Long> likes;
    private Date createdAt;
    private Date updatedAt;
}
