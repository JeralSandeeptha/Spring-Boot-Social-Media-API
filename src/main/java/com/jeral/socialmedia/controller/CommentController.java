package com.jeral.socialmedia.controller;

import com.jeral.socialmedia.dto.request.CommentRequestDTO;
import com.jeral.socialmedia.dto.response.CommentResponseDTO;
import com.jeral.socialmedia.model.Comment;
import com.jeral.socialmedia.service.impl.CommentServiceImpl;
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
@RequestMapping(path = "/api/v1/comment")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/{postId}/{userId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> createComment(@PathVariable Long postId, @PathVariable Long userId, @RequestBody CommentRequestDTO commentRequestDTO) {
        try {
            Comment comment = commentService.createComment(postId, userId, commentRequestDTO);
            logger.info("Create comment query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Create comment query was successful",
                            comment
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Create comment query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Create comment query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @GetMapping(path = "/")
    public CompletableFuture<ResponseEntity<StandardResponse>> getComments() {
        try {
            List<Comment> comments = commentService.getAllComments();
            logger.info("Get comments query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get comments query was successful",
                            comments
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Get comment query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get comments query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @GetMapping(path = "/{id}")
    public CompletableFuture<ResponseEntity<StandardResponse>> getComment(@PathVariable Long id) {
        try {
            CommentResponseDTO comment = commentService.getComment(id);
            logger.info("Get comment query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get comment query was successful",
                            comment
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Get comment query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get comment query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @DeleteMapping(path = "/{id}")
    public CompletableFuture<ResponseEntity<StandardResponse>> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            logger.info("Delete comment query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            204,
                            "Delete comment query was successful",
                            "Delete comment query was successful"
                    ),
                    HttpStatus.NO_CONTENT
            ));
        }catch (Exception ex) {
            logger.error("Delete comment query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Delete comment query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PutMapping(path = "/{id}")
    public CompletableFuture<ResponseEntity<StandardResponse>> updateComment(@PathVariable Long id, @RequestBody CommentRequestDTO commentRequestDTO) {
        try {
            Comment comment = commentService.updateComment(id, commentRequestDTO);
            logger.info("Update comment query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Update comment query was successful",
                            comment
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Update comment query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Update comment query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @GetMapping(path = "/getByPost/{postId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> getAllCommentsByPostId(@PathVariable Long postId) {
        try {
            List<Comment> comments = commentService.getAllCommentsByPostId(postId);
            logger.info("Get All Comments by Post ID query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get All Comments by Post ID query was successful",
                            comments
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Get All Comments by Post ID query was failed");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get All Comments by Post ID was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }
}
