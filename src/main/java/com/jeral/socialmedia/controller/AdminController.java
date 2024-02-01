package com.jeral.socialmedia.controller;

import com.jeral.socialmedia.dto.request.AdminRequestDTO;
import com.jeral.socialmedia.dto.response.AdminResponseDTO;
import com.jeral.socialmedia.model.Admin;
import com.jeral.socialmedia.service.impl.AdminServiceImpl;
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
@RequestMapping(path = "/api/v1/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private final AdminServiceImpl adminService;

    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path = "/list")
    public CompletableFuture<ResponseEntity<StandardResponse>> getAdmins() {
        try {
            List<Admin> allAdmins = adminService.getAddAdmins();
            logger.info("Get admin list query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get admin list query was successful",
                            allAdmins
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Get admin list query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get admin list query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @GetMapping(path = "/{adminId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> getAdmin(@PathVariable(value = "adminId", required = false) long adminId) {
        try {
            AdminResponseDTO admin = adminService.getAdmin(adminId);
            logger.info("Get admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Get admin query was successful",
                            admin
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Get admin query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Get admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @PutMapping(path = "/{adminId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> updateAdmin(@PathVariable(value = "adminId", required = false) long adminId, @RequestBody AdminRequestDTO adminRequestDTO) {
        try {
            Admin admin = adminService.updateAdmin(adminRequestDTO, adminId);
            logger.info("Update admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Update admin query was successful",
                            admin
                    ),
                    HttpStatus.OK
            ));
        }catch (Exception ex) {
            logger.error("Update admin query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Update admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }

    @DeleteMapping(path = "/{adminId}")
    public CompletableFuture<ResponseEntity<StandardResponse>> deleteAdmin(@PathVariable(value = "adminId", required = false) long adminId) {
        try {
                adminService.deleteAdmin(adminId);
            logger.info("Delete admin query was successful");
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            204,
                            "Delete admin query was successful",
                            "Delete admin query was successful"
                    ),
                    HttpStatus.NO_CONTENT
            ));
        }catch (Exception ex) {
            logger.error("Delete admin query was failed: " + ex.getMessage());
            return CompletableFuture.completedFuture(new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Delete admin query was failed",
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            ));
        }
    }
}
