package com.lorram.elections.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.elections.dto.CandidateDTO;
import com.lorram.elections.entities.Candidate;
import com.lorram.elections.repositories.CandidateRepository;
import com.lorram.elections.services.exceptions.ResourceNotFoundException;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository repository;
	
	public Page<CandidateDTO> findAll(Pageable pageable) {
		Page<Candidate> list = repository.findAll(pageable);
		return list.map(x -> new CandidateDTO(x));
	}
	
	public CandidateDTO findById(Long id) {
		Optional<Candidate> obj = repository.findById(id);
		Candidate candidate = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new CandidateDTO(candidate);
	}
	
	public CandidateDTO insert(CandidateDTO dto) {
		Candidate candidate = new Candidate();
		candidate.setName(dto.name());
		candidate.setNumber(dto.number());
		repository.save(candidate);
		return new CandidateDTO(candidate);
	}
	
	public CandidateDTO update(CandidateDTO dto, Long id) {
		Candidate candidate = repository.getReferenceById(id);
		candidate.setName(dto.name());
		candidate.setNumber(dto.number());
		candidate = repository.save(candidate);
		return new CandidateDTO(candidate);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
