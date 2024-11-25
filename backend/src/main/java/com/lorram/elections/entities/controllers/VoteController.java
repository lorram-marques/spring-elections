package com.lorram.elections.entities.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lorram.elections.dto.VoteDTO;
import com.lorram.elections.services.VoteService;

@RestController
@RequestMapping(value = "/votes")
public class VoteController {
				
	@Autowired
	private VoteService service;
	
	@GetMapping
	public ResponseEntity<Page<VoteDTO>> findAll(Pageable pageable) {
		Page<VoteDTO> list = service.findAll(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VoteDTO> findById(@PathVariable Long id) {
		VoteDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping(value = "/votecandidate/{id}")
	public ResponseEntity<VoteDTO> insert(@RequestBody VoteDTO dto, @PathVariable Long candidateId) {
		VoteDTO vote = service.insert(dto, candidateId);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.id()).toUri();
		return ResponseEntity.created(uri).body(vote);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VoteDTO> update(@RequestBody VoteDTO dto, @PathVariable Long id) {
		service.update(dto, id);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
}
