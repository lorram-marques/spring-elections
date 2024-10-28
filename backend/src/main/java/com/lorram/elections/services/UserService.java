package com.lorram.elections.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.elections.dto.UserDTO;
import com.lorram.elections.entities.User;
import com.lorram.elections.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserDTO(x));
	}
	
	public UserDTO findById(Long id) {
		Optional<User> user = repository.findById(id);
		
		return null;
	}
	
}
