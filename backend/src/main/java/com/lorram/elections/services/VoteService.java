package com.lorram.elections.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.elections.dto.VoteDTO;
import com.lorram.elections.entities.Vote;
import com.lorram.elections.repositories.CandidateRepository;
import com.lorram.elections.repositories.UserRepository;
import com.lorram.elections.repositories.VoteRepository;
import com.lorram.elections.services.exceptions.ResourceNotFoundException;

@Service
public class VoteService {

	@Autowired
	private VoteRepository repository;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<VoteDTO> findAll(Pageable pageable) {
		Page<Vote> list = repository.findAll(pageable);
		return list.map(x -> new VoteDTO(x));
	}
	
	public VoteDTO findById(Long id) {
		Optional<Vote> obj = repository.findById(id);
		Vote vote = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new VoteDTO(vote);
	}
	
	public VoteDTO insert(VoteDTO dto, Long candidateId) {
		Vote vote = new Vote();
		fromVoteDto(dto, vote);
		vote.setCandidate(candidateRepository.getReferenceById(candidateId));
		vote = repository.save(vote);
		return new VoteDTO(vote);
	}
	
	public VoteDTO update(VoteDTO dto, Long id) {
		Vote vote = repository.getReferenceById(id);
		fromDto(dto, vote);
		vote = repository.save(vote);
		return new VoteDTO(vote);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	private void fromDto(VoteDTO voteDto, Vote vote) {
		vote.setCandidate(candidateRepository.getReferenceById(voteDto.candidateId()));
		vote.setUser(userRepository.getReferenceById(voteDto.userId()));
	}
	
	private void fromVoteDto(VoteDTO voteDto, Vote vote) {
		vote.setUser(userRepository.getReferenceById(voteDto.userId()));
	}
}
