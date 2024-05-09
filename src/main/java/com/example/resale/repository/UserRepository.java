package com.example.resale.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.resale.entity.User;
@Repository

public interface UserRepository extends JpaRepository<User, UUID> {

}
