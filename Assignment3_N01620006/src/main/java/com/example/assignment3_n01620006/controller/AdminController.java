package com.example.assignment3_n01620006.controller;

import com.example.assignment3_n01620006.model.Admin;
import com.example.assignment3_n01620006.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class); // Add Logger

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody Admin admin) {
        logger.info("Admin login attempt: {}", admin.getUsername()); // Log login attempt
        try {
            Admin authenticatedAdmin = adminService.authenticate(admin.getUsername(), admin.getPassword());
            logger.info("Admin login successful: {}", authenticatedAdmin.getUsername()); // Log success
            return ResponseEntity.ok(authenticatedAdmin);
        } catch (RuntimeException e) {
            logger.error("Invalid admin credentials for username: {}", admin.getUsername()); // Log error
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}


