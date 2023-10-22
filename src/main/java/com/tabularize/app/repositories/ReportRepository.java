package com.tabularize.app.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tabularize.app.model.Report;

public interface ReportRepository extends JpaRepository<Report, String> {
	List<Report> findAll();
}
