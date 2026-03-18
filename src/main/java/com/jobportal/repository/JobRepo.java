package com.jobportal.repository;

import java.util.Date;
import java.util.List;

import org.springframework.boot.autoconfigure.batch.BatchProperties.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobportal.model.Jobs;

public interface JobRepo extends MongoRepository<Jobs, String> {
    
    List<Jobs> getByRecruiterId(String recruiterId);

    int countByPostedDateBetween(Date start, Date end);

    long count();

    List<Jobs> findAllByOrderByPostedDateDesc();

//     @Query("SELECT j FROM Jobs j WHERE j.title LIKE %:keyword% OR j.skillsRequired LIKE %:keyword% OR j.companyName LIKE %:keyword%")
// List<Jobs> searchJobs(String keyword);

//    @Query("SELECT j FROM Jobs j WHERE j.title LIKE %:keyword% OR j.skillsRequired LIKE %:keyword% OR j.companyName LIKE %:keyword%")
//     List<Jobs> searchJobs(@Param("keyword") String keyword);

List<Jobs> findByTitleContainingIgnoreCaseOrSkillsRequiredContainingIgnoreCaseOrCompanyNameContainingIgnoreCase(
        String title, String skills, String company);
}
