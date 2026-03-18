package com.jobportal.model.UnusedModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "recruiters")
public class Recruiter {

    @Id
    private String recruiterId;

    String email;
    int contactRecruiter;
    String department;
    String designation;
    int experienceRecruiter;
    String companyName;
    Boolean verified;

    byte[] profilepicRecruiter;

    public String getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(String recruiterId) {
        this.recruiterId = recruiterId;
    }

    public void setEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
