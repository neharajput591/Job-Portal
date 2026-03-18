package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// import com.jobportal.model.Candidate;
// import com.jobportal.model.Recruiter;
// import com.jobportal.model.User;

import com.jobportal.model.Jobs;
import com.jobportal.model.User12;
import com.jobportal.repository.JobRepo;
import com.jobportal.repository.UserRepo12;
import com.jobportal.repository.UnusedRepo.CandidateRepo;
import com.jobportal.repository.UnusedRepo.RecruiterRepo;
import com.jobportal.repository.UnusedRepo.UserRepo;


@Controller
public class AuthController {

    // @Autowired
    // UserRepo userRepo;

    @Autowired
    UserRepo12 userRepo12;

    @Autowired
    CandidateRepo candidateRepo;

    // @Autowired
    // RecruiterRepo recruiterRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JobRepo jobRepo;

    @GetMapping("/")
    public String home(Model model){

         List<Jobs> job = jobRepo.findAll();

        model.addAttribute("job", job);
        return "newindexYoutube";
    }

    // @GetMapping("/")
    // public String home(Model model) {

    //     List<Jobs> job = jobRepo.findAll();

    //     model.addAttribute("job", job);
    //     return "index1";
    // }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        // model.addAttribute("userdata", new User());
        model.addAttribute("userdata" , new User12());
        return "register";
    }

    // @PostMapping("/RegisterData")
    // public String saveUser(@ModelAttribute("userdata") User user) {

    // user.setPassword(passwordEncoder.encode(user.getPassword()));

    // userRepo.save(user);

    // return "redirect:/login?registerSuccess=true";
    // }

    // @PostMapping("/RegisterData")
    // public ResponseEntity<String> registerUser(@RequestBody User user) {
    // if (userRepo.existsByEmail(user.getEmail())) {
    // return ResponseEntity.badRequest().body("Email is already registered");
    // }

    // userRepo.save(user);
    // return ResponseEntity.ok("Registration successful");
    // }
    @PostMapping("/RegisterData")
    public String registerUser(@ModelAttribute("userdata") User12 user, Model model) {

        // Check if email already exists
        if (userRepo12.existsByEmail(user.getEmail())) {
            model.addAttribute("errorMessage", "This email is already registered!");
            return "register"; // return to the form page with error
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the new user
        user = userRepo12.save(user);

        if ("candidate".equalsIgnoreCase(user.getRole())) {
            // Candidate candidate = new Candidate();
            // candidate.setCandidateId(user.getUser_id());
            // candidate.setEmail(user.getEmail());
            // candidateRepo.save(candidate);

        
        } else if ("recruiter".equalsIgnoreCase(user.getRole())) {
            // Recruiter recruiter = new Recruiter();
            // recruiter.setRecruiterId(user.getUser_id());
            // recruiter.setEmail(user.getEmail());
            // recruiterRepo.save(recruiter);
        }


        return "redirect:/login?registerSuccess=true"; // redirect or render success page
    }

    // @GetMapping("/candidate_home")
    // public String candidateHome(Model model) {

    //     model.addAttribute("candidateProfile", new Candidate());

    //     return "candidate_home";
    // }

    // @GetMapping("/recruiter_home")
    // public String recruiterHome() {
    //     return "recruiter_home";
    // }

    // @GetMapping("/admin_home")
    // public String adminHome() {
    //     return "admin_home";
    // }


    // @PostMapping("/index1/search")

      @GetMapping("/index1/search")
    public String searchJobs(@RequestParam("keyword") String keyword, Model model) {

        System.out.println("Keyword: " + keyword);
        // List<Jobs> jobs = jobRepo.searchJobs(keyword);

        List<Jobs> jobs = jobRepo
                .findByTitleContainingIgnoreCaseOrSkillsRequiredContainingIgnoreCaseOrCompanyNameContainingIgnoreCase(
                        keyword, keyword, keyword);

        model.addAttribute("jobsOnSearch", jobs);

        List<Jobs> job = jobRepo.findAll();

        model.addAttribute("job", job);
        return "newindexYoutube";
    }
}
