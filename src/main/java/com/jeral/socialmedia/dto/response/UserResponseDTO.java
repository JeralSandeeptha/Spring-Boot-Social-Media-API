package com.jeral.socialmedia.dto.response;

import com.jeral.socialmedia.model.Details;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private long id;
    private String username;
    private String password;
    private String role;
    @Embedded
    private Details details;
    private Date createdAt;
    private Date updatedAt;
}
