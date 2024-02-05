package com.jeral.socialmedia.service.impl;

import com.jeral.socialmedia.dto.request.PostRequestDTO;
import com.jeral.socialmedia.dto.response.PostResponseDTO;
import com.jeral.socialmedia.exceptions.NotFoundException;
import com.jeral.socialmedia.model.Post;
import com.jeral.socialmedia.repo.PostRepo;
import com.jeral.socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepo postRepo;

    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public Post createPost(Long userId, PostRequestDTO postRequestDTO) {
        UUID uuid = UUID.randomUUID();
        Long id = uuid.getMostSignificantBits();
        Post post = Post.builder()
                .postId(id)
                .userId(userId)
                .title(postRequestDTO.getTitle())
                .imageUrl(postRequestDTO.getImageUrl())
                .likes(new ArrayList<>())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        postRepo.save(post);
        return post;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepo.findAll();
        return posts;
    }

    @Override
    public PostResponseDTO getPost(Long postId) {
        Optional<Post> selectedPost = postRepo.findById(postId);
        if (selectedPost.isEmpty()) {
            throw new NotFoundException("Post id not found");
        }else {
            Post post = selectedPost.get();
            PostResponseDTO postResponseDTO = PostResponseDTO.builder()
                    .postId(post.getPostId())
                    .userId(post.getUserId())
                    .title(post.getTitle())
                    .imageUrl(post.getImageUrl())
                    .likes(post.getLikes())
                    .createdAt(post.getCreatedAt())
                    .updatedAt(post.getUpdatedAt())
                    .build();
            return postResponseDTO;
        }
    }

    @Override
    public Post updatePost(Long postId, Long userId, PostRequestDTO postRequestDTO) {
        Optional<Post> selectedPost = postRepo.findById(postId);
        if (selectedPost.isEmpty()) {
            throw new NotFoundException("Post id not found");
        }else {
            Post post = selectedPost.get();
            post.setTitle(postRequestDTO.getTitle());
            post.setImageUrl(postRequestDTO.getImageUrl());
            post.setUpdatedAt(new Date());
            postRepo.save(post);
            return post;
        }
    }

    @Override
    public void deletePost(Long postId) {
        Optional<Post> selectedPost = postRepo.findById(postId);
        if (selectedPost.isEmpty()) {
            throw new NotFoundException("Post id not found");
        }else {
            postRepo.deleteById(postId);
        }
    }

    @Override
    public List<Post> getAllPostsByUserId(Long userId) {
//        postRepo.findAllPostsByUserId(userId);
        List<Post> posts = postRepo.findByUserId(userId);
        return posts;
    }
}
