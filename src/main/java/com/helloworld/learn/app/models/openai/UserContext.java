package com.helloworld.learn.app.models.openai;

import com.helloworld.learn.app.models.user.DAOUser;
import org.springframework.security.core.userdetails.User;

import jakarta.persistence.*;

@Entity
public class UserContext {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private DAOUser user;

    @Column(columnDefinition = "TEXT")
    private String context;

    public UserContext(){}

    public UserContext(
        Long id,
        DAOUser user,
        String context
    ){
        this.id = id;
        this.user = user;
        this.context = context;
    }

    public DAOUser getUser() {
        return user;
    }

    public void setUser(DAOUser user) {
        this.user = user;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
