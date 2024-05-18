package com.example.resale.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.resale.entity.User;
@Repository

public interface UserRepository extends JpaRepository<User, UUID> {
	
//	@Query(value = "SELECT * FROM resale where user_Name =:username",nativeQuery = true)
//
//	Object findbyuserName(String userName);

	Optional<User> findByUserName(String userName);

}
