package com.lorram.elections.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.elections.entities.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>{

}
