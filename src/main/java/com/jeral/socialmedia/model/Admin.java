package com.jeral.socialmedia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    //@ID should be import from jakarta persistance
    @Id
    private long id;
    private String username;
    private String password;
    private String role;
    private Date createdAt;
    private Date updatedAt;
}
