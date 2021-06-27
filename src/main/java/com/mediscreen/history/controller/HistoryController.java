package com.mediscreen.history.controller;

import java.util.List;

import com.mediscreen.history.model.PatientHistory;
import com.mediscreen.history.service.HistoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  Logger logger = LoggerFactory.getLogger(HistoryController.class);

  @Autowired
  HistoryService historyService;

  /**
   * get the list of histories taken for a patient
   * 
   * @param patientId
   * @return
   */
  @GetMapping(path = "/history/{patId}")
  public ResponseEntity<List<PatientHistory>> listNotesOfAPatient(@PathVariable("patId") int patId) {
    logger.info("get all patients history");
    List<PatientHistory> stories = historyService.getListOfHistoriesByPatId(patId);
    if (stories == null || stories.isEmpty()) {
      logger.warn("no history found");
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok().body(stories);
  }

  /**
   * get a history by its id
   * 
   * @param historyId
   */
  @GetMapping(path = "/history-get/{historyId}")
  public ResponseEntity<PatientHistory> getOneHistoryByItsId(@PathVariable("historyId") String historyId) {
    logger.info("get a history with id: " + historyId);
    PatientHistory patientHistory = historyService.getHistoryById(historyId);
    return ResponseEntity.ok().body(patientHistory);
  }

  /**
   * add a new history to a patient
   * 
   * @param patientHistory
   * @return
   */
  @PostMapping(path = "/history/add")
  public ResponseEntity<PatientHistory> addAhistoryToApatient(@RequestBody PatientHistory patientHistory) {
    logger.info("add a history");
    PatientHistory patientHistoryAdded = historyService.savePatientHistory(patientHistory);
    return ResponseEntity.ok().body(patientHistoryAdded);
  }

  /**
   * update a history of a patient
   * 
   * @param historyId
   * @param patientHistoryUpdated
   * @return
   */
  @PutMapping(path = "/history-update/{historyId}")
  public ResponseEntity<PatientHistory> updateHistoryOfApatient(@PathVariable("historyId") String historyId,
      @RequestBody PatientHistory patientHistoryUpdated) {
    logger.info("update history with id: " + historyId);
    if (historyId == null) {
      logger.warn("bad request, no history updated : id null");
      return ResponseEntity.badRequest().build();
    }
    PatientHistory patientHistory = historyService.getHistoryById(historyId);
    patientHistory.setNote(patientHistoryUpdated.getNote());
    historyService.savePatientHistory(patientHistory);

    return ResponseEntity.ok().body(patientHistory);
  }

}
