package com.jobportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.model.Jobs;
import com.jobportal.repository.JobRepo;

@Service
public class JobService {

    @Autowired
    JobRepo jobRepo;

    public void postAjob(Jobs newJobs){
        jobRepo.save(newJobs);
    }

    public List<Jobs> findAllJobByRecruiter(String recruiterId){

        return jobRepo.getByRecruiterId(recruiterId);
    }
    
    public Jobs getByJobId(String jobId){

        Optional<Jobs> updateit = jobRepo.findById(jobId);

        Jobs updateItsData = updateit.get();

        return updateItsData;
    }

    public void savejobupdate(Jobs updatedone){

        jobRepo.save(updatedone);
    }

    public void DeleteJob(String jobId){

        jobRepo.deleteById(jobId);
    }

    public List<Jobs> findAllJobs(){
        return jobRepo.findAll();
    }

    public List<Jobs> findAllJobsByOrderByPostedDateDesc(){
        return jobRepo.findAllByOrderByPostedDateDesc();
    }
}
