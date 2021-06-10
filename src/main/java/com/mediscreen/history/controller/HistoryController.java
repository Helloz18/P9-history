package com.mediscreen.history.controller;

import java.util.List;

import com.mediscreen.history.model.PatientHistory;
import com.mediscreen.history.repository.PatientHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class HistoryController {
    
@Autowired
PatientHistoryRepository repo;

/**
 * get the list of notes taken for a patient
 * @param patientId
 * @return
 */
@GetMapping(path = "/history/{patId}")
public ResponseEntity<List<PatientHistory>> listNotesOfAPatient(@PathVariable("patId") int patId) {
      List<PatientHistory> stories =  repo.findBypatId(patId);
      if(stories == null || stories.isEmpty() ) {
        return ResponseEntity.noContent().build();
      }
        return ResponseEntity.ok().body(stories);
    }

    /**
     * get a history by its id
     * @param historyId 
     */
  @GetMapping(path="/history-update/{historyId}")
    public ResponseEntity<PatientHistory> getOneHistoryByItsId(
      @PathVariable("historyId") String historyId ) {
        PatientHistory patientHistory = repo.findById(historyId).get();
        return ResponseEntity.ok().body(patientHistory);
      }
   

   /**
    * add a new history
    * @param patientHistory
    * @return
    */
@PostMapping(path = "/history/add")
public ResponseEntity<PatientHistory> addAhistoryToApatient(@RequestBody PatientHistory patientHistory) {
    PatientHistory patientHistoryAdded = repo.save(patientHistory);
    return ResponseEntity.ok().body(patientHistoryAdded);
    }

    /**
     * update a history of a patient
     * @param historyId
     * @param patientHistoryUpdated
     * @return
     */
    @PutMapping(path = "/history-update/{historyId}")
    public ResponseEntity<PatientHistory> updateHistoryOfApatient( 
      @PathVariable("historyId") String historyId, 
      @RequestBody PatientHistory patientHistoryUpdated) {
        if (historyId == null) {
          return ResponseEntity.badRequest().build();
        }
        PatientHistory patientHistory = repo.findById(historyId).get();
        patientHistory.setNote(patientHistoryUpdated.getNote());
        repo.save(patientHistory);

        return ResponseEntity.ok().body(patientHistory);
      }

}



