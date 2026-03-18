package com.jobportal.repository.UnusedRepo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jobportal.model.User12;
import com.jobportal.model.UnusedModel.Recruiter;

public interface RecruiterRepo extends MongoRepository<User12, String> {


}
