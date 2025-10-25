package com.example.demo.Infrastructure.Entity;

import com.example.demo.Domain.Model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class UserEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public String name;
    public String lastName;

    public static UserEntity fromDomain(User user) {
        return UserEntity.builder()
                .name(user.getName())
                .lastName(user.getLastName())
                .build();
    }
    
}
