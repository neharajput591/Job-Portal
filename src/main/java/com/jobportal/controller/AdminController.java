package com.jobportal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jobportal.model.Application;
import com.jobportal.model.User12;
import com.jobportal.model.Jobs;

import com.jobportal.repository.UserRepo12;
import com.jobportal.service.AdminService;
import com.jobportal.service.ApplicationService;
import com.jobportal.service.User12Service;
import com.jobportal.service.JobService;


@Controller
public class AdminController {

    @Autowired
    User12Service user12Service;

    @Autowired
    UserRepo12 user12Repo;

    @Autowired
    AdminService adminService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    JobService jobService;
    // @GetMapping("/admin_home")
    // public String adminhome(Model model){

    // String verifyOrNot = "Rejected";

    // List<User12> newRecruiter = user12Service.findByVerify12(verifyOrNot);

    // model.addAttribute("newRecruiter" , newRecruiter);

    // return "AdminDashboard";
    // }

    @GetMapping("/admin_home")
    public String adminhome(Model model) {

        // verify recruiter
        String role = "Recruiter";
        String verifyStatus = "Pending";

        List<User12> newRecruiter = user12Service.findByRoleAndVerify12(role, verifyStatus);

        int totalCandidates = adminService.getTotalCandidates();

        model.addAttribute("totalCandidates", totalCandidates);
        model.addAttribute("totalRecruiters", adminService.getTotalRecruiters());
        model.addAttribute("totalJobs", adminService.getTotalJobs());
        model.addAttribute("totalApplications", adminService.getTotalApplications());

        model.addAttribute("getTodayJobs", adminService.getTodayJobs());
        model.addAttribute("getNewCandidate", adminService.getNewCandidate());
        model.addAttribute("getNewRecruiter", adminService.getNewrecruiter());
        model.addAttribute("newRecruiter", newRecruiter);

        // =============================================================

        // view all candidate

        String role1 = "Candidate";

        List<User12> allCandidates = user12Service.findAllCandidateByOrderByCreatedDateDesc(role1);

        // findAllByOrderByPostedDateDesc()
        // findByJobIdOrderByAppliedDateDesc
        // ===============================================================
        List<Application> allApplications = applicationService.findAllApplicationsOrderByAppliedDateDesc();

        model.addAttribute("allCandidates", allCandidates);
        model.addAttribute("allApplications",allApplications);

        // =============================================================

        // =========================================================================

        String status = "Verified";

        List<User12> allRecruiter = user12Service.findByRoleAndVerify12(role, status);

        List<Jobs> allJobs =  jobService.findAllJobsByOrderByPostedDateDesc();

        model.addAttribute("allRecruiter", allRecruiter);
        model.addAttribute("allJobs",allJobs);

        return "AdminDashboard";
    }

    @GetMapping("/Admin/recruiterProfile/{idUser}")
    public  String viewProfile(@PathVariable("idUser") String idUser , Model model){

        System.out.println(idUser);

        Optional<User12> recruiterProfile12 = user12Service.findRecruiter(idUser);

        User12 recruiterProfile = recruiterProfile12.get();

        model.addAttribute("recruiterProfile" , recruiterProfile);

        return "viewRecruiterProfile";
    }

    @GetMapping("/Admin/verifyRecruiter/{idUser}")
    public String verifyrec(@PathVariable("idUser") String idUser, Model model) {

        // String verifyOrNot = "Rejected";

        // List<User12> newRecruiter = user12Service.findByVerify12(verifyOrNot);

        // model.addAttribute("newRecruiter" , newRecruiter);

        // String userId = idUser;
        Optional<User12> recruiter12 = user12Repo.findById(idUser);

        User12 recruiter = recruiter12.get();

        recruiter.setVerify("Verified");
        user12Repo.save(recruiter);

        return "redirect:/admin_home";
    }

    @GetMapping("/Admin/rejectRecruiter/{idUser}")
    public String rejectrec(@PathVariable("idUser") String idUser, Model model) {

        Optional<User12> recruiter12 = user12Repo.findById(idUser);

        User12 recruiter = recruiter12.get();

        recruiter.setVerify("Rejected");
        user12Repo.save(recruiter);

        return "redirect:/admin_home";
    }
}
