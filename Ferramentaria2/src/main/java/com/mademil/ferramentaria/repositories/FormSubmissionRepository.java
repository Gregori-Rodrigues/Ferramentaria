package com.mademil.ferramentaria.repositories;

import com.mademil.ferramentaria.entities.FormSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormSubmissionRepository extends JpaRepository<FormSubmission, Integer> {

    @Query(value = "SELECT fs.* " +
    "FROM form_submissions fs " +
    "WHERE fs.is_saved = true " +
    "AND fs.submission_id IN (" +
    "  SELECT MAX(submission_id) " +
    "  FROM form_submissions " +
    "  WHERE is_saved = true " +
    "  GROUP BY machine_id, item, operation_id" +
    ") ORDER BY fs.created_at DESC", nativeQuery = true)
    List<FormSubmission> findLatestSavedSubmissions();

    List<FormSubmission> findAllByMachineIdAndItemAndOperationIdAndIsSavedTrueOrderByCreatedAtDesc(Integer machineId, String item, Integer operationId);
}
