package com.jobportal.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jobportal.model.User12;
import com.jobportal.model.UnusedModel.Candidate;
import com.jobportal.repository.UserRepo12;

@Service
public class User12Service {

    @Autowired
    UserRepo12 user12Repo;

    // public User12 getByEmail(String email) {
    // return user12Repo.findByEmail(email).orElse(null);
    // }

    public User12 getByEmail(String email) {
        return user12Repo.findByEmail(email).orElse(null);
    }

    public void saveCandidate(User12 candidateProfile) {
        user12Repo.save(candidateProfile);
    }

    // public List<User12> findByVerify12(String verifyOrNot) {

    //     return user12Repo.findByVerify(verifyOrNot);

    // }

    public List<User12> findByRoleAndVerify(String role, String verify) {
        return user12Repo.findByRoleAndVerify(role, verify);
    }

    public List<User12> findAllCandidate(String role1){
        return user12Repo.findByRole(role1);
    }

    public Optional<User12> findRecruiter(String idUser){
        return user12Repo.findById(idUser);
    }

    public List<User12> findAllCandidateByOrderByCreatedDateDesc(String role1){
        return user12Repo.findByRoleOrderByCreatedDateDesc(role1);
    }

    public List<User12> findByRoleAndVerify12(String role, String verify){
        return user12Repo.findByRoleAndVerifyOrderByCreatedDateDesc(role,verify);
    }
//   public List<User12> findAllRecruiter(String role , )

}
