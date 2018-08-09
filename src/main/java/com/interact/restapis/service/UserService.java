package com.interact.restapis.service;


import com.interact.restapis.model.Report;
import com.interact.restapis.model.User;
import com.interact.restapis.repository.ReportRepository;
import com.interact.restapis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {  //TODO Add other USER functions PRIORITY - HIGH

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;

    /* get all customers */
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    /*to save a user*/
    public User addUser(User user){
        return userRepository.save(user);
    }

    /*get a user by id */
    public User getUser(Long Id){
        Optional<User> optionalUser = userRepository.findById(Id);
        return optionalUser.orElse(null);
    }

    public User getUserByEmail(String email) {
        String emailid = email.toUpperCase();
        return userRepository.getUserByEmail(email);
    }

    public User updateUser(User user, Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(!userOptional.isPresent())
            return null;
        return userRepository.save(user);
    }

    /* delete a user */
    public boolean deleteUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent())
            return false;

        //Fetch and delete all reports (Inter-Actions) related to the user
        List<Report> sentReports = reportRepository.findReportByFromUserId(id);
        List<Report> receivedReports = reportRepository.findReportByToUserId(id);
        for(Report report: sentReports)
            reportRepository.deleteById(report.getId());
        for(Report report: receivedReports)
            reportRepository.deleteById(report.getId());

        userRepository.deleteById(id);
        return true;
    }

}
