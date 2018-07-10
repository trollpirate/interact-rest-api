package com.interact.restapis.repository;

import com.interact.restapis.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findReportByFromUserId(Long fromUserId);
    List<Report> findReportByToUserId(Long toUserId);
}
