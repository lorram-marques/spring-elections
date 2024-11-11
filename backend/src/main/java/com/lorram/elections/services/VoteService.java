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
	
	private CandidateRepository candidateRepository;
	
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
	
	public VoteDTO insert(VoteDTO dto) {
		Vote vote = new Vote();
		vote.setCandidate(candidateRepository.getReferenceById(dto.candidateId()));
		vote.setUser(userRepository.getReferenceById(dto.userId()));
		repository.save(vote);
		return new VoteDTO(vote);
	}
	
	public VoteDTO update(VoteDTO dto, Long id) {
		Vote vote = repository.getReferenceById(id);
		vote.setCandidate(candidateRepository.getReferenceById(dto.candidateId()));
		vote.setUser(userRepository.getReferenceById(dto.userId()));
		vote = repository.save(vote);
		return new VoteDTO(vote);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
