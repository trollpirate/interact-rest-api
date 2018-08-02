package com.interact.restapis.service;

import com.interact.restapis.model.Report;
import com.interact.restapis.model.User;
import com.interact.restapis.repository.ReportRepository;
import com.interact.restapis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service(value = "UserService")
public class UserService {  //TODO Add other USER functions PRIORITY - HIGH

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;

//    @Override
//    public UserDetails loadUserByUsername(String id){
//        User user = userRepository.getUserByEmail(id);
//
//        if(user == null)
//            throw new UsernameNotFoundException("Invalid username or password");
//
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities());
//    }

    private List getAuthorities(){
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    /* get all customers */
    public List<User> getAllUser() {
        System.out.println(userRepository.findAll());

        System.out.println("anjomav");
        return userRepository.findAll();
    }

    /*to save a user*/
    public User addUser(User user){
        return userRepository.save(user);
    }

    /*get a user by id */
    public User getUser(Long Id){
        return userRepository.getOne(Id);
    }

    public User getUserByEmail(String email) {
        String emailid = email.toUpperCase();
        return userRepository.getUserByEmail(emailid);
    }

    public User updateUser(User user, Long id){
        if(userRepository.getOne(id) == null)
            return null;
        return userRepository.save(user);
    }

    /* delete a user */
    public boolean deleteUser(Long id){
        if(userRepository.getOne(id) == null)
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
