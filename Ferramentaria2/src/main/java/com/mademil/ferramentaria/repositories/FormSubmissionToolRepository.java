package com.mademil.ferramentaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mademil.ferramentaria.entities.FormSubmissionTool;

import java.util.List;

public interface FormSubmissionToolRepository extends JpaRepository<FormSubmissionTool, Integer> {
    
    List<FormSubmissionTool> findAllBySubmissionId(Integer submission_id);
}
