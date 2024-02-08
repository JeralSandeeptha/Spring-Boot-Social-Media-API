package com.jeral.socialmedia.service.impl;

import com.jeral.socialmedia.dto.request.AuthRequest;
import com.jeral.socialmedia.dto.request.UserDetailsRequestDTO;
import com.jeral.socialmedia.dto.response.UserResponseDTO;
import com.jeral.socialmedia.exceptions.NotFoundException;
import com.jeral.socialmedia.model.Details;
import com.jeral.socialmedia.model.User;
import com.jeral.socialmedia.repo.UserRepo;
import com.jeral.socialmedia.service.JwtService;
import com.jeral.socialmedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final UserRepo userRepo;

    public UserServiceImpl(JwtService jwtService, UserRepo userRepo) {
        this.jwtService = jwtService;
        this.userRepo = userRepo;
    }

    @Override
    public String loginUser(AuthRequest authRequest) {
        User user = userRepo.findByUsername(authRequest.getUsername());
        if (user != null) {
            String token = jwtService.generateToken(user, authRequest.getUsername());
            return token;
        }else {
            throw new NotFoundException("Can not find this username");
        }
    }

    @Override
    public User registerUser(UserDetailsRequestDTO userDetailsRequestDTO) {
        UUID uuid = UUID.randomUUID();
        long id = uuid.getMostSignificantBits();
        Details details = Details.builder()
                .city(userDetailsRequestDTO.getDetailsRequestDTO().getCity())
                .contactNumber(userDetailsRequestDTO.getDetailsRequestDTO().getContactNumber())
                .postalCode(userDetailsRequestDTO.getDetailsRequestDTO().getPostalCode())
                .build();
        User user = User.builder()
                .id(id)
                .username(userDetailsRequestDTO.getUserRequestDTO().getUsername())
                .password(userDetailsRequestDTO.getUserRequestDTO().getPassword())
                .role("USER")
                .details(details)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        userRepo.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @Override
    public UserResponseDTO getUser(Long id) {
        Optional<User> selectedUser = userRepo.findById(id);
        if (selectedUser.isEmpty()) {
            throw new NotFoundException("User id not found");
        }else {
            User user = selectedUser.get();
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setPassword(user.getPassword());
            userResponseDTO.setRole(user.getRole());
            userResponseDTO.setDetails(user.getDetails());
            userResponseDTO.setCreatedAt(user.getCreatedAt());
            userResponseDTO.setUpdatedAt(user.getUpdatedAt());
            return userResponseDTO;
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> selectedUser = userRepo.findById(id);
        if (selectedUser.isEmpty()) {
            throw new NotFoundException("User id not found");
        }else {
            userRepo.deleteById(id);
        }
    }

    @Override
    public User updateUser(Long id, UserDetailsRequestDTO userDetailsRequestDTO) {
        Optional<User> selectedUser = userRepo.findById(id);
        if (selectedUser.isEmpty()) {
            throw new NotFoundException("User id not found");
        }else {
            User user = selectedUser.get();
            Details details = Details.builder()
                    .city(userDetailsRequestDTO.getDetailsRequestDTO().getCity())
                            .contactNumber(userDetailsRequestDTO.getDetailsRequestDTO().getContactNumber())
                                    .postalCode(userDetailsRequestDTO.getDetailsRequestDTO().getPostalCode())
                                            .build();
            user.setUsername(userDetailsRequestDTO.getUserRequestDTO().getUsername());
            user.setPassword(userDetailsRequestDTO.getUserRequestDTO().getPassword());
            user.setRole("USER");
            user.setDetails(details);
            user.setUpdatedAt(new Date());
            user.setCreatedAt(user.getCreatedAt());
            userRepo.save(user);
            return user;
        }
    }
}
