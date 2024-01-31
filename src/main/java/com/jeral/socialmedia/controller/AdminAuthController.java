package com.jeral.socialmedia.controller;

import com.jeral.socialmedia.dto.request.AdminRequestDTO;
import com.jeral.socialmedia.dto.request.AuthRequest;
import com.jeral.socialmedia.model.Admin;
import com.jeral.socialmedia.repo.AdminRepo;
import com.jeral.socialmedia.service.impl.AdminServiceImpl;
import com.jeral.socialmedia.utils.StandardResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/v1/admin/auth")
public class AdminAuthController {

    private static final Logger logger = LoggerFactory.getLogger(AdminAuthController.class);

    @Autowired
    private final AdminServiceImpl adminService;

    @Autowired
    private final AdminRepo adminRepo;

    public AdminAuthController(AdminServiceImpl adminService, AdminRepo adminRepo) {
        this.adminService = adminService;
        this.adminRepo = adminRepo;
    }

    @PostMapping("/register")
    public CompletableFuture<ResponseEntity<StandardResponse>> registerAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {
        try {
            Admin admin = adminService.registerAdmin(adminRequestDTO);
            logger.info("Admin register query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            201,
                            "Admin register query was successful",
                            admin
                    ),
                    HttpStatus.CREATED
            ));
        }catch (Exception ex) {
            logger.error("Admin register query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Admin register query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PostMapping("/login")
    public CompletableFuture<ResponseEntity<StandardResponse>> loginAdmin(@RequestBody AuthRequest authRequest) {
        try {
            Admin admin = adminRepo.findByUsername(authRequest.getUsername());
            if (admin != null) {
                if (admin.getPassword() == authRequest.getPassword()) {
                    String token = adminService.loginAdmin(authRequest);
                    HashMap<String, Object> responseData = new HashMap<>();
                    responseData.put("user", admin);
                    responseData.put("token", token);
                    logger.info("Admin login query was successful");
                    return CompletableFuture.completedFuture(new ResponseEntity<>(
                            new StandardResponse(
                                    200,
                                    "Admin login query was successful",
                                    responseData
                            ),
                            HttpStatus.OK
                    ));
                }else {
                    logger.info("Admin login query was failed");
                    return CompletableFuture.completedFuture(new ResponseEntity<>(
                            new StandardResponse(
                                    401,
                                    "Admin login query was failed",
                                    "Password is incorrect"
                            ),
                            HttpStatus.OK
                    ));
                }
            }else {
                logger.info("Admin login query was failed: Password is incorrect");
                return CompletableFuture.completedFuture(new ResponseEntity<>(
                        new StandardResponse(
                                404,
                                "Admin login query was failed",
                                "Username is incorrect"
                        ),
                        HttpStatus.NOT_ACCEPTABLE
                ));
            }
        }catch (Exception ex) {
            logger.error("Admin login query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Admin login query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }
}
