package com.mediscreen.history.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "histories")
public class PatientHistory {

    @Id
    private String historyId;
    private int patId;
    private Note note;
    
    public PatientHistory() {
    }

    public PatientHistory(int patId, Note note) {
        this.patId = patId;
        this.note = note;
    }

    
    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    public Note getNote() {
        return note;
    }
    public void setNote(Note note) {
        this.note = note;
    }

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

    @Override
    public String toString() {
        return "PatientHistory [historyId=" + historyId + ", note=" + note + ", patId=" + patId + "]";
    }


     
}
