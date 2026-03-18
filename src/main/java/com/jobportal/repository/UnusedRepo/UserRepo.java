package com.jobportal.repository.UnusedRepo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.model.UnusedModel.User;

public interface UserRepo extends MongoRepository<User, String> {

     User findByEmail(String email);

      boolean existsByEmail(String email);
}

