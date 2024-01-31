package com.jeral.socialmedia.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDTO {
    private long id;
    private String username;
    private String password;
    private String role;
    private Date createdAt;
    private Date updatedAt;
}
