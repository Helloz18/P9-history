package com.mediscreen.history.controller;

import java.util.ArrayList;
import java.util.List;

import com.mediscreen.history.model.Note;
import com.mediscreen.history.model.PatientHistory;
import com.mediscreen.history.repository.MongoDbPatientHistoryRepository;
import com.mongodb.client.FindIterable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class HistoryController {
    
@Autowired
MongoDbPatientHistoryRepository repo;

// /**
//  * get the list of notes taken for a patient
//  * @param patientId
//  * @return
//  */
// @GetMapping(path = "/history/")
// public ResponseEntity<FindIterable<PatientHistory>> listNotesOfAPatient(int patientId) {
//     FindIterable<PatientHistory> h = repo.find(patientId);
//     return ResponseEntity.ok().body(h);
//     }


/**
 * get the list of notes taken for a patient
 * @param patientId
 * @return
 */
@GetMapping(path = "/history/{patId}")
public ResponseEntity<List<Note>> listNotesOfAPatient(int patId) {
    PatientHistory h = repo.findOne(patId);
    List<Note> notes = h.getNotes();
    return ResponseEntity.ok().body(notes);
   }

@PostMapping(path = "history/add")
public ResponseEntity<List<Note>> addAnoteToAPatientHistory(@RequestBody PatientHistory patientHistory) {
    // on récupère l'history du patient que l'on souhaite compléter
    PatientHistory historyExisting = repo.findOne(patientHistory.getPatId());
    // si l'history existante ne contient pas de notes, alors on créer une liste vide que l'on set à cet history.
    if(historyExisting.getNotes()==null) {
        List<Note> notestoadd = new ArrayList<>();
    // on ajoute la note contenu dans les informations envoyées à la liste des notes
        for(Note note : patientHistory.getNotes()) {
            notestoadd.add(note);
        }
        historyExisting.setNotes(notestoadd);
    } else {
        for(Note note : patientHistory.getNotes()) {
        historyExisting.getNotes().add(note);
        }     
    }
    return ResponseEntity.ok().body(patientHistory.getNotes());   
    }
}



