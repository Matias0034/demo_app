package com.example.demo.Infrastructure.Procesor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Domain.Repository.UserRepository;
import com.example.demo.Infrastructure.Entity.UserEntity;

@Repository
public interface UserProcesor extends JpaRepository<UserEntity, Long>, UserRepository{}
