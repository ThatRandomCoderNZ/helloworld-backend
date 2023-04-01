package com.helloworld.learn.app.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Table(name = "user")
public class DAOUser {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    @Nonnull
    private String username;
    @Column
    @JsonIgnore
    private String password;

    @Column
    private UserRoles role;

    @Column(unique=true)
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String uuid;

    public DAOUser(){}

    public DAOUser(long id, String username, String password, UserRoles role, String uuid)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}