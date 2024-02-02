package com.jeral.socialmedia.service;

import com.jeral.socialmedia.dto.request.AuthRequest;
import com.jeral.socialmedia.dto.request.UserDetailsRequestDTO;
import com.jeral.socialmedia.dto.response.UserResponseDTO;
import com.jeral.socialmedia.model.User;

import java.util.List;

public interface UserService {

    public String loginUser(AuthRequest authRequest);
    public User registerUser(UserDetailsRequestDTO userDetailsRequestDTO);
    public List<User> getAllUsers();
    public UserResponseDTO getUser(Long id);
    public void deleteUser(Long id);
    public User updateUser(Long id, UserDetailsRequestDTO userDetailsRequestDTO);
}
