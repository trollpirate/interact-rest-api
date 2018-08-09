package com.interact.restapis.repository;

import com.interact.restapis.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {
    List<Action> findActionByReportId(Long reportId);
}
