package com.mediscreen.history.service;

import java.util.List;

import com.mediscreen.history.model.PatientHistory;
import com.mediscreen.history.repository.PatientHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    
    @Autowired
    PatientHistoryRepository patientHistoryRepository;

    public List<PatientHistory> getListOfHistoriesByPatId(int patId) {
        return patientHistoryRepository.findBypatId(patId);
    }

    public PatientHistory getHistoryById(String historyId) {
        return patientHistoryRepository.findById(historyId).get();
    }

    public PatientHistory savePatientHistory(PatientHistory patientHistory) {
        return patientHistoryRepository.save(patientHistory);
    }
}
