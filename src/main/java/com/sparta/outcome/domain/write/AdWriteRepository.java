package com.sparta.outcome.domain.write;

import com.sparta.outcome.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AdWriteRepository extends JpaRepository<Ad,Long> {
}
