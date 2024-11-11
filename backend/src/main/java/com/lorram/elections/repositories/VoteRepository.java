package com.lorram.elections.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.elections.entities.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

}
