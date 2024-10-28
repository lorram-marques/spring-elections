package com.lorram.elections.dto;

import com.lorram.elections.entities.User;

public record UserDTO (Long id, String name) {

	public UserDTO(User user) {
		this(user.getId(), 
			 user.getName());
	}
}
