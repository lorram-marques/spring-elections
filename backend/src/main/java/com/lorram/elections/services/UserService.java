package com.lorram.elections.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.elections.dto.UserDTO;
import com.lorram.elections.entities.User;
import com.lorram.elections.repositories.UserRepository;
import com.lorram.elections.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> list = repository.findAll(pageable);
		return list.map(x -> new UserDTO(x));
	}
	
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User user = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(user);
	}
	
	public UserDTO insert(UserDTO dto) {
		User user = new User();
		user.setName(dto.name());
		repository.save(user);
		return new UserDTO(user);
	}
	
	public UserDTO update(UserDTO dto, Long id) {
		User user = repository.getReferenceById(id);
		user.setName(dto.name());
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
