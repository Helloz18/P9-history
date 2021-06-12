package com.mediscreen.history.repository;

import java.util.List;

import com.mediscreen.history.model.PatientHistory;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PatientHistoryRepository extends MongoRepository<PatientHistory, String> {
    
    List<PatientHistory> findBypatId(int patId);

    
}
