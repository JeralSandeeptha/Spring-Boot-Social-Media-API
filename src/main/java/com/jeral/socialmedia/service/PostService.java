package com.jeral.socialmedia.service;

import com.jeral.socialmedia.dto.request.PostRequestDTO;
import com.jeral.socialmedia.dto.response.PostResponseDTO;
import com.jeral.socialmedia.model.Post;

import java.util.List;

public interface PostService {
    public Post createPost(Long userId, PostRequestDTO postRequestDTO);
    public List<Post> getAllPosts();
    public PostResponseDTO getPost(Long postId);
    public Post updatePost(Long postId, Long userId, PostRequestDTO postRequestDTO);
    public void deletePost(Long postId);
    public List<Post> getAllPostsByUserId(Long userId);
    public void deleteAllPostsByUserId(Long userId);
}
