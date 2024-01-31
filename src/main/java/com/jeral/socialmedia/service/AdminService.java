package com.jeral.socialmedia.service;

import com.jeral.socialmedia.dto.request.AdminRequestDTO;
import com.jeral.socialmedia.dto.request.AuthRequest;
import com.jeral.socialmedia.dto.response.AdminResponseDTO;
import com.jeral.socialmedia.model.Admin;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AdminService {

    public Admin registerAdmin(@RequestBody AdminRequestDTO adminRequestDTO);
    public String loginAdmin(@RequestBody AuthRequest authRequest);
    public List<Admin> getAddAdmins();
    public AdminResponseDTO getAdmin(long adminId);
    public Admin updateAdmin(@RequestBody AdminRequestDTO adminRequestDTO, long adminId);
    public void deleteAdmin(long adminId);
}
