package com.accepted.match_api.repository;

import com.accepted.match_api.entity.Match;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @EntityGraph(attributePaths = "odds")
    List<Match> findAll();

    @EntityGraph(attributePaths = "odds")
    Page<Match> findAll(Pageable pageable);
}
