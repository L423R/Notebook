package com.example.notebook.services;

import com.example.notebook.entities.Note;

import java.util.Date;
import java.util.List;

public interface NoteService {

    void saveNote(Note note);
    void deleteNote(Note note);
    List<Note> getAllNote();
    Note getNote(int id);
    void updateNote(Integer id, String message, boolean done, Date dateEnd);
    List<Note> findAllByOrderByDateStartAsc();
    List<Note> findAllByOrderByDateStartDesc();
    List<Note> findAllByOrderByDateEndAsc();
    List<Note> findAllByOrderByDateEndDesc();
    List<Note> findNotesByDoneOrderByDateEndAsc(boolean done);
}
