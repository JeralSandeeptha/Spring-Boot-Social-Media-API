package com.jeral.socialmedia.controller;

import com.jeral.socialmedia.dto.request.UserDetailsRequestDTO;
import com.jeral.socialmedia.dto.response.UserResponseDTO;
import com.jeral.socialmedia.model.User;
import com.jeral.socialmedia.service.impl.CommentServiceImpl;
import com.jeral.socialmedia.service.impl.PostServiceImpl;
import com.jeral.socialmedia.service.impl.UserServiceImpl;
import com.jeral.socialmedia.utils.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserServiceImpl userService;
    private final PostServiceImpl postService;
    private final CommentServiceImpl commentService;

    public UserController(UserServiceImpl userService, PostServiceImpl postService, CommentServiceImpl commentService) {
        this.userService = userService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping(path = "/")
    public CompletableFuture<ResponseEntity<StandardResponse>> registerUser(@RequestBody UserDetailsRequestDTO userDetailsRequestDTO) {
        try {
            User user = userService.registerUser(userDetailsRequestDTO);
            logger.info("User register query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            201,
                            "User register query was successful",
                            user
                    ),
                    HttpStatus.CREATED
            ));
        }catch (Exception ex) {
            logger.error("User register query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "User register query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @GetMapping(path = "/list")
    public CompletableFuture<ResponseEntity<StandardResponse>> getAllUsers() {
        try {
            List<User> allUsers = userService.getAllUsers();
            logger.info("Get all users query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            201,
                            "Get all users query was successful",
                            allUsers
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Get all users query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get all users query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PutMapping(path = "/{userId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> updateUser(@PathVariable(value = "userId", required = false) long userId, @RequestBody UserDetailsRequestDTO userDetailsRequestDTO) {
        try {
            User user = userService.updateUser(userId, userDetailsRequestDTO);
            logger.info("Update user query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Update user query was successful",
                            user
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Update user query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Update user query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @GetMapping(path = "/{userId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> getAdmin(@PathVariable(value = "userId", required = false) long userId) {
        try {
            UserResponseDTO user = userService.getUser(userId);
            logger.info("Get user query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get user query was successful",
                            user
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Get user query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get user query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @DeleteMapping(path = "/{userId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> deleteUser(@PathVariable(value = "userId", required = false) long userId) {
        try {
            userService.deleteUser(userId);
            //delete user all posts
            postService.deleteAllPostsByUserId(userId);
            //delete user all comments
            commentService.deleteAllCommentsbyUserId(userId);
            logger.info("Delete user query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            204,
                            "Delete user query was successful",
                            "Delete user query was successful"
                    ),
                    HttpStatus.NO_CONTENT
            ));
        }catch (Exception ex) {
            logger.error("Delete user query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Delete user query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }
}
