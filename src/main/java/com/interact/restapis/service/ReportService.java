package com.interact.restapis.service;

import com.interact.restapis.model.Report;
import com.interact.restapis.repository.ReportRepository;
import com.interact.restapis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    /*get all reports */
    public List<Report> getAllReports(){
        return reportRepository.findAll();
    }

    /*get a single report */
    public Report getReport(Long id){
        return reportRepository.getOne(id);
    }

    /*add a report*/
    public Report addReport(Report report){
//        UserService userService = new UserService();
        String fromEmail = userRepository.getOne(report.getFromUserId()).getEmail();
        String toEmail = userRepository.getOne(report.getToUserId()).getEmail();
        report.setFromUserEmail(fromEmail);
        report.setToUserEmail(toEmail);
        return reportRepository.save(report);
    }

    public Report updateReport(Report report, Long id){
        if(reportRepository.getOne(id) == null)
            return null;
        return reportRepository.save(report);
    }

    public boolean deleteReport(Long id) {
        if(reportRepository.getOne(id)==null)
            return false;
        reportRepository.deleteById(id);
        return true;
    }
}
