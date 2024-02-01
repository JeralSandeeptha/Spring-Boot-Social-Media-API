package com.jeral.socialmedia.service.impl;

import com.jeral.socialmedia.dto.request.AdminRequestDTO;
import com.jeral.socialmedia.dto.request.AuthRequest;
import com.jeral.socialmedia.dto.response.AdminResponseDTO;
import com.jeral.socialmedia.exceptions.NotFoundException;
import com.jeral.socialmedia.model.Admin;
import com.jeral.socialmedia.repo.AdminRepo;
import com.jeral.socialmedia.service.AdminService;
import com.jeral.socialmedia.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private final AdminRepo adminRepo;

    @Autowired
    private final JwtService jwtService;

    public AdminServiceImpl(AdminRepo adminRepo, JwtService jwtService) {
        this.adminRepo = adminRepo;
        this.jwtService = jwtService;
    }

    @Override
    public Admin registerAdmin(AdminRequestDTO adminRequestDTO) {
        UUID uuid = UUID.randomUUID();
        long id = uuid.getMostSignificantBits();
        Admin admin = new Admin();
        admin.setId(id);
        admin.setUsername(adminRequestDTO.getUsername());
        admin.setPassword(adminRequestDTO.getPassword());
        admin.setRole("ADMIN");
        admin.setCreatedAt(new Date());
        admin.setUpdatedAt(new Date());
        adminRepo.save(admin);
        return admin;
    }

    @Override
    public String loginAdmin(AuthRequest authRequest) {
        Admin admin = adminRepo.findByUsername(authRequest.getUsername());
        if (admin != null) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return token;
        }else {
            throw new NotFoundException("Can not find this username");
        }
    }

    @Override
    public List<Admin> getAddAdmins() {
        List<Admin> adminList = adminRepo.findAll();
        return adminList;
    }

    @Override
    public AdminResponseDTO getAdmin(long adminId) {
        Optional<Admin> selectedAdmin = adminRepo.findById(adminId);
        if (selectedAdmin.isEmpty()) {
            throw new NotFoundException("Admin id not found");
        }else {
            Admin admin = selectedAdmin.get();
            AdminResponseDTO adminResponseDTO = new AdminResponseDTO();
            adminResponseDTO.setId(admin.getId());
            adminResponseDTO.setUsername(admin.getUsername());
            adminResponseDTO.setPassword(admin.getPassword());
            adminResponseDTO.setRole(admin.getRole());
            adminResponseDTO.setCreatedAt(admin.getCreatedAt());
            adminResponseDTO.setUpdatedAt(admin.getUpdatedAt());
            return adminResponseDTO;
        }
    }

    @Override
    public Admin updateAdmin(AdminRequestDTO adminRequestDTO, long adminId) {
        Optional<Admin> selectedAdmin = adminRepo.findById(adminId);
        if (selectedAdmin.isEmpty()) {
            throw new NotFoundException("Admin id not found");
        }else {
            Admin admin = selectedAdmin.get();
            admin.setUsername(adminRequestDTO.getUsername());
            admin.setPassword(adminRequestDTO.getPassword());
            admin.setUpdatedAt(new Date());
            adminRepo.save(admin);
            return admin;
        }
    }

    @Override
    public void deleteAdmin(long adminId) {
        Optional<Admin> selectedAdmin = adminRepo.findById(adminId);
        if (selectedAdmin.isEmpty()) {
            throw new NotFoundException("Admin id not found");
        }else {
            adminRepo.deleteById(adminId);
        }
    }
}
