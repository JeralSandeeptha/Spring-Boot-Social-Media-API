package com.jeral.socialmedia.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private long id;
    private String username;
    private String password;
    private String role;
    @Embedded
    private Details details;
    private Date createdAt;
    private Date updatedAt;
}
