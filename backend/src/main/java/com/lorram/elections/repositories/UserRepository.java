package com.lorram.elections.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.elections.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
