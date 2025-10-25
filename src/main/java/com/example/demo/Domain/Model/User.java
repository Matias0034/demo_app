package com.example.demo.Domain.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class User {

    private String id;
    private String name;
    private String lastName;
}
