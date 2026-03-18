package com.jobportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.model.Application;
import com.jobportal.repository.ApplicationRepo;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepo applicationRepo;


    public List<Application> findByJobId(String jobId){

       return applicationRepo.findByJobId(jobId);

    }

    public List<Application> findAllApplications(){
        return applicationRepo.findAll();
    }

    public List<Application> findByJobIdOrderByAppliedDateDesc(String jobId){
        return applicationRepo.findByJobIdOrderByAppliedDateDesc(jobId);
    }
    
    public List<Application> findAllApplicationsOrderByAppliedDateDesc(){
        return applicationRepo.findAllByOrderByAppliedDateDesc();
    }
}
