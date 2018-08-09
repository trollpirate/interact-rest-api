package com.interact.restapis.service;

import com.interact.restapis.model.Action;
import com.interact.restapis.model.Report;
import com.interact.restapis.repository.ActionRepository;
import com.interact.restapis.repository.ReportRepository;
import com.interact.restapis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActionRepository actionRepository;

    /*get all reports */
    public List<Report> getAllReports(){
        return reportRepository.findAll();
    }

    /*get a single report */
    public Report getReport(Long id){
        Optional<Report> optionalReport = reportRepository.findById(id);
        return optionalReport.orElse(null);
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
//        Report report1 = reportRepository.getOne(id);
//        System.out.println("REPORT SERVICE (UPDATE) - \n" + report1.getId() + " OBS: " + report1.getObservation());
        Optional<Report> optionalReport = reportRepository.findById(id);
        return optionalReport.orElse(null);
    }

    public boolean deleteReport(Long id) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if(!optionalReport.isPresent())
            return false;
        else {
            List<Action> actions = actionRepository.findActionByReportId(id);
            for(Action action: actions){
                actionRepository.deleteById(action.getId());
            }
            reportRepository.deleteById(id);
        }
        return true;
    }
}
