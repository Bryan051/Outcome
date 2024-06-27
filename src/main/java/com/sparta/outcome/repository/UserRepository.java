package com.sparta.outcome.repository;


import com.sparta.outcome.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {//pk의 타입

    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByUserEmail(String userEmail);

}
