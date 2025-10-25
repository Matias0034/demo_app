package com.example.demo.Application;

import org.springframework.stereotype.Service;

import com.example.demo.Domain.Model.User;
import com.example.demo.Domain.Repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;
    
    public void create(User user){
        this.userRepository.save(user);
    }
}
