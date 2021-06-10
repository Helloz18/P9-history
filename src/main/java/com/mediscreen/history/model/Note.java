package com.mediscreen.history.model;

public class Note {
    
    private String noteTitle;
    private String noteContent;
    private String noteDate;
    private String doctorName;
    
    
    public Note() {
    }

    public Note(String noteTitle, String noteContent, String noteDate, String doctorName) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
        this.noteDate = noteDate;
        this.doctorName = doctorName;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return "Note [doctorName=" + doctorName + ", noteContent=" + noteContent + ", noteDate=" + noteDate
                + ", noteTitle=" + noteTitle + "]";
    }

    
}
