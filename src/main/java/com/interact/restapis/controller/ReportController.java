package com.interact.restapis.controller;

import com.interact.restapis.model.Report;
import com.interact.restapis.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    //Get all reports
    @GetMapping("/interactions")
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    //Get single report
    @GetMapping("/interactions/{id}")
    public Report getReport(@PathVariable Long id) {
        return reportService.getReport(id);
    }

    @PostMapping("/interactions")
    public ResponseEntity<Report> addReport(@RequestBody Report report) {
        Report report1 = reportService.addReport(report);
        if(report1 == null)
            return new ResponseEntity<>(report, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(report1, HttpStatus.OK);
    }

    @PutMapping("/interactions/{id}")
    public ResponseEntity<Report> updateReport(@RequestBody Report report, @PathVariable(value = "id") Long id) {
        if(reportService.updateReport(report, id) == null)
            return new ResponseEntity<>(report, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/interactions/{id}")
    public ResponseEntity<Report> deleteReport(@PathVariable Long id) {
        if(!reportService.deleteReport(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
