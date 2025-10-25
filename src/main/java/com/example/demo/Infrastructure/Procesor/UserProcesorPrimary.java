package com.example.demo.Infrastructure.Procesor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.example.demo.Domain.Model.User;
import com.example.demo.Domain.Repository.UserRepository;
import com.example.demo.Infrastructure.Entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Primary
public interface UserProcesorPrimary extends JpaRepository<UserEntity, Long>, UserRepository {

    @Override
    default void save(User user) {
        save(UserEntity.fromDomain(user)); // usa el save de JpaRepository
    }
}
