package com.jobportal.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.repository.ApplicationRepo;
import com.jobportal.repository.JobRepo;
import com.jobportal.repository.UserRepo12;

@Service
public class AdminService {

    @Autowired
    UserRepo12 user12Repo;

    @Autowired
    JobRepo jobsRepo;

    @Autowired 
    ApplicationRepo applicationRepo;

    public int getTotalCandidates(){

        return user12Repo.countByRole("Candidate");

    }

    public int getTotalRecruiters(){
        return user12Repo.countByRole("Recruiter");
    }

    public long  getTotalJobs(){
        return jobsRepo.count();
    }

    public long getTotalApplications(){
        return applicationRepo.count();
    }

    public int getTodayJobs(){
        LocalDate today = LocalDate.now();

    Date start = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date end = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

    return jobsRepo.countByPostedDateBetween(start, end);
    }

    public int getNewCandidate(){
        LocalDate today = LocalDate.now();

        Date start = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println("Start: " + start);
System.out.println("End: " + end);
        return user12Repo.countByCreatedDateBetween("Candidate",start,end);
    }
    
    public int getNewrecruiter(){
        LocalDate today = LocalDate.now();

        Date start = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return user12Repo.countByCreatedDateBetween("Recruiter", start, end);
    }
}
