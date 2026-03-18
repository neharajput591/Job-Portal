package com.jobportal.model.UnusedModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Setter
// @Getter
@Document(collection = "candidates")
public class Candidate {

    @Id
    private String candidateId;

    String name = null;
    String email;
    int age  = 18;
    String skills = "i.e, HTML , CSS , JAVASCRIPT";
    String city = "Indore";
    String state = "Madhya Pradesh";
    String country = "Indore";
    long contact = 1234567889;
    String aboutcandidate = "I am a Frontend Developer";
    int experience = 2;
    String education = "B.tech";

    byte[] profilepic;

    public void setCandidateId(String user_id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // 🔹 Getter
    // public String getCandidateId() {
    //     return candidateId;
    // }

    // public void setEmail(String email) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }

    // public String getName() {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }

  
    
}
