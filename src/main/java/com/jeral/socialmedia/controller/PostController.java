package com.jeral.socialmedia.controller;

import com.jeral.socialmedia.dto.request.PostRequestDTO;
import com.jeral.socialmedia.dto.response.PostResponseDTO;
import com.jeral.socialmedia.model.Post;
import com.jeral.socialmedia.service.impl.PostServiceImpl;
import com.jeral.socialmedia.utils.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/v1/post")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping(path = "/{userId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> createPost(@PathVariable Long userId, @RequestBody PostRequestDTO postRequestDTO) {
        try {
            Post post = postService.createPost(userId, postRequestDTO);
            logger.info("Create post query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            201,
                            "Create post query was successful",
                            post
                    ),
                    HttpStatus.CREATED
            ));
        }catch (Exception ex) {
            logger.error("Create post query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Create post query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @GetMapping(path = "/")
    public CompletableFuture<ResponseEntity<StandardResponse>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            logger.info("Get posts query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get posts query was successful",
                            posts
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Get posts query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get posts query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @GetMapping(path = "/{postId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> getPost(@PathVariable Long postId) {
        try {
            PostResponseDTO post = postService.getPost(postId);
            logger.info("Get post query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get post query was successful",
                            post
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Get post query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get post query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @DeleteMapping(path = "/{postId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> deletePost(@PathVariable Long postId) {
        try {
            postService.deletePost(postId);
            logger.info("Delete post query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            204,
                            "Delete post query was successful",
                            "Delete post query was successful"
                    ),
                    HttpStatus.NO_CONTENT
            ));
        }catch (Exception ex) {
            logger.error("Delete post query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Delete post query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PutMapping(path = "/{postId}/{userId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> updatePost(@PathVariable Long postId, @PathVariable Long userId, @RequestBody PostRequestDTO postRequestDTO) {
        try {
            Post post = postService.updatePost(postId, userId, postRequestDTO);
            logger.info("Update post query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Update post query was successful",
                            post
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Update post query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Update post query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @GetMapping(path = "/getByUser/{userId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> getAllPostsByUserId(@PathVariable Long userId) {
        try {
            List<Post> allPostsByUserId = postService.getAllPostsByUserId(userId);
            logger.info("Get All Posts by UserId query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get All Posts by UserId query was successful",
                            allPostsByUserId
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Get All Posts by UserId query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get All Posts by UserId query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }
}