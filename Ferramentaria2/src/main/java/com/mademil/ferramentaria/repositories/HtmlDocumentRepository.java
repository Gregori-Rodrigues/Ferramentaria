package com.mademil.ferramentaria.repositories;

import com.mademil.ferramentaria.entities.HtmlDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HtmlDocumentRepository extends JpaRepository<HtmlDocument, Integer> {
    
    HtmlDocument findBySubmissionId(Integer submissionId);
}
