package com.lorram.elections.dto;

import com.lorram.elections.entities.Candidate;

public record CandidateDTO(Long id, String name, Integer number) {

	public CandidateDTO(Candidate candidate) {
		this(candidate.getId(), 
			 candidate.getName(),
			 candidate.getNumber());
	}
}
