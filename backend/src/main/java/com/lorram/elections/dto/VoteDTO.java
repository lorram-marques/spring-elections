package com.lorram.elections.dto;

import com.lorram.elections.entities.Vote;

public record VoteDTO(Long id, CandidateDTO candidate, UserDTO user) {

	public VoteDTO(Vote vote) {
		this(vote.getId(), 
			 new CandidateDTO(vote.getCandidate()),
			 new UserDTO(vote.getUser()));
	}
}
