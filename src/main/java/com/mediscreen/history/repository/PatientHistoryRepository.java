package com.mediscreen.history.repository;

import com.mediscreen.history.model.PatientHistory;
import com.mongodb.internal.operation.FindAndUpdateOperation;

import org.springframework.stereotype.Repository;

@Repository
public interface PatientHistoryRepository {
    
    PatientHistory save(PatientHistory patientHistory);
    PatientHistory findOne(int patId);
    PatientHistory update(PatientHistory patientHistory);

    
}
