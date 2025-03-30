package com.example.assignment3_n01620006.service;

import com.example.assignment3_n01620006.model.Admin;
import com.example.assignment3_n01620006.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin authenticate(String username, String password) {
        return adminRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));
    }
}

