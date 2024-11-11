package com.lorram.elections.dto;

import com.lorram.elections.entities.Vote;

public record VoteDTO(Long id, Long candidateId, Long userId) {

	public VoteDTO(Vote vote) {
		this(vote.getId(), 
			 vote.getCandidate().getId(),
			 vote.getUser().getId());
	}
}
