package com.mediscreen.history.model;

import java.util.List;


public class PatientHistory {

    private int patId;
    private List<Note> notes;
    
    public PatientHistory() {
    }

    public PatientHistory(int patId, List<Note> notes) {
        this.patId = patId;
        this.notes = notes;
    }
    public List<Note> getNotes() {
        return notes;
    }
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

    @Override
    public String toString() {
        return "PatientHistory [notes=" + notes + ", patientId=" + patId + "]";
    }

    
   

    
}
