package com.makershark.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makershark.ums.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByUserName(String userName);
	UserEntity findByEmail(String email);
	UserEntity findByMobileNo(String mobileNo);
}
