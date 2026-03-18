package com.jobportal.repository.UnusedRepo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.model.UnusedModel.Candidate;

public interface CandidateRepo extends MongoRepository<Candidate, String> {

    Optional<Candidate> findByCandidateId(String candidateId);

    Optional<Candidate> findByEmail(String email);
}
