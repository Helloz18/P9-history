package com.mediscreen.history;


import java.util.ArrayList;
import java.util.List;

import com.mediscreen.history.model.Note;
import com.mediscreen.history.model.PatientHistory;
import com.mediscreen.history.repository.MongoDbPatientHistoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
public class HistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoryApplication.class, args);
		
		
		// MongoDbPatientHistoryRepository repo = new MongoDbPatientHistoryRepository();
		
		// List<Note> notes = new ArrayList<>();
		// Note note = new Note("ici titre", "ici du texte en veux-tu en voilà", "12/12/12", "Martin Mystère");
		// notes.add(note);
		// PatientHistory hist = new PatientHistory(1, notes);
		// repo.save(hist);
		// PatientHistory test = repo.findOne(1);
		
	
		// System.out.println("test "+test);

		// List<Note> notesrecuperees = test.getNotes();
		// Note note2 = new Note("nouveau titre", "nouveau texte", "28/10/81", "Martin Mystère");
		// note2.setNoteId(notesrecuperees.size());
		// notesrecuperees.add(note2);
		// test.setNotes(notesrecuperees);
		// System.out.println("test étape 2 "+test);

		// //on créé un array de Note vide
		// List<Note> notesPatient2 = new ArrayList<>();
		// //on assigne cet array vide à un patient dont l'id est 2
		// PatientHistory hist2 = new PatientHistory(2, notesPatient2);
		// //on save ce PatientHistory
		// repo.save(hist2);
		// //on charge ce PatientHistory
		// PatientHistory test2 = repo.findOne(2);
		// //on affiche ce PatientHistory
		// System.out.println("test2 "+test2);
		// //on ajoute une note à l'array
		// Note note3 = new Note("note2","titre","28/12/2001", "Martin Mystère");
		// //on set le note id
		// note3.setNoteId(notesPatient2.size()+1);
		// notesPatient2.add(note3);
		// //on set le note du PatientHistory
		// test2.setNotes(notesPatient2);
		// //on affiche le résultat
		// System.out.println("test2 ++"+test2);

		// ///////////////////:
		// PatientHistory p = new PatientHistory();
		// p.setPatId(3);
		// System.out.println(p);
		// if(p.getNotes()==null) {
		// 	List<Note> notestoadd = new ArrayList<>();
		// 	p.setNotes(notestoadd);
		// }
		// System.out.println("p2 "+p);
	
	}

}
