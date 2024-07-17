package com.sparta.outcome.domain.read;

import com.sparta.outcome.domain.AdView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AdViewReadRepository extends JpaRepository<AdView,Long> {


}

