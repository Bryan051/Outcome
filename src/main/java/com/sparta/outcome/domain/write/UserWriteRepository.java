package com.sparta.outcome.domain.write;


import com.sparta.outcome.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserWriteRepository extends JpaRepository<User, Long> {//pk의 타입


}
