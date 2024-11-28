package com.mademil.ferramentaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mademil.ferramentaria.repositories.HtmlDocumentRepository;
import com.mademil.ferramentaria.entities.HtmlDocument;

import java.util.List;

@Service
public class HtmlDocumentService {
    @Autowired
    HtmlDocumentRepository htmlDocumentRepository;

    public List<HtmlDocument> getAllHtmlDocuments(){
        return htmlDocumentRepository.findAll();
    }

    public HtmlDocument getHtmlDocumentBySubmissionId(Integer submissionId){
        return htmlDocumentRepository.findBySubmissionId(submissionId);
    }

    public void saveHtmlDocument(HtmlDocument htmlDocument){
        htmlDocumentRepository.saveAndFlush(htmlDocument);
    }
}
