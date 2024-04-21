package com.jeral.socialmedia.controller;

import com.jeral.socialmedia.dto.request.AuthRequest;
import com.jeral.socialmedia.model.User;
import com.jeral.socialmedia.repo.UserRepo;
import com.jeral.socialmedia.service.impl.UserServiceImpl;
import com.jeral.socialmedia.utils.StandardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/v1/user/auth")
public class UserAuthController {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthController.class);

    private final UserServiceImpl userService;

    private final UserRepo userRepo;

    public UserAuthController(UserServiceImpl userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @PostMapping(path = "/login")
    public CompletableFuture<ResponseEntity<StandardResponse>> loginUser(@RequestBody AuthRequest authRequest) {
        try {
            User user = userRepo.findByUsername(authRequest.getUsername());
            if (user != null) {
                if (user.getPassword().equals(authRequest.getPassword())) {
                    String token = userService.loginUser(authRequest);
                    HashMap<String, Object> responseData = new HashMap<>();
                    responseData.put("user", user);
                    responseData.put("token", token);
                    logger.info("User login query was successful");
                    return CompletableFuture.completedFuture(new ResponseEntity<>(
                            new StandardResponse(
                                    200,
                                    "User login query was successful",
                                    responseData
                            ),
                            HttpStatus.OK
                    ));
                }else {
                    logger.info("User login query was failed");
                    return CompletableFuture.completedFuture(new ResponseEntity<>(
                            new StandardResponse(
                                    401,
                                    "User login query was failed",
                                    "Password is incorrect"
                            ),
                            HttpStatus.OK
                    ));
                }
            }else {
                logger.info("User login query was failed");
                return CompletableFuture.completedFuture(new ResponseEntity<>(
                        new StandardResponse(
                                401,
                                "User login query was failed",
                                "Username is incorrect"
                        ),
                        HttpStatus.OK
                ));
            }
        }catch (Exception ex) {
            logger.error("User login query was failed: " + ex);
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "User login query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }
}
