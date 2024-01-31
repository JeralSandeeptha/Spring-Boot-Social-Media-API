package com.jeral.socialmedia.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotEmpty(message = "Username must be required")
    private String username;
    @NotEmpty(message = "Password must be required")
    private String password;
}
