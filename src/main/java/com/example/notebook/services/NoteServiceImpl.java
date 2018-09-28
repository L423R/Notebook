package com.example.notebook.services;

import com.example.notebook.entities.Note;
import com.example.notebook.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository repository;

    @Autowired
    public void setRepository(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveNote(Note note) {
        repository.save(note);
    }

    @Override
    public void deleteNote(Note note) {
        repository.delete(note);
    }

    @Override
    public List<Note> getAllNote() {
        return repository.findAll();
    }

    @Override
    public Note getNote(int id) {
        return repository.getOne(id);
    }

    @Override
    public void updateNote(Integer id, String message, boolean done, Date dateEnd) {
        Note one = repository.getOne(id);
        one.setMessage(message);
        one.setDone(done);
        one.setDateEnd(dateEnd);
        repository.save(one);
    }

    @Override
    public List<Note> findAllByOrderByDateStartAsc() {
        return repository.findAllByOrderByDateStartAsc();
    }

    @Override
    public List<Note> findAllByOrderByDateStartDesc() {
        return repository.findAllByOrderByDateStartDesc();
    }

    @Override
    public List<Note> findAllByOrderByDateEndAsc() {
        return repository.findAllByOrderByDateEndAsc();
    }

    @Override
    public List<Note> findAllByOrderByDateEndDesc() {
        return repository.findAllByOrderByDateEndDesc();
    }

    @Override
    public List<Note> findNotesByDoneOrderByDateEndAsc(boolean done) {
        return repository.findNotesByDoneOrderByDateEndAsc(done);
    }
}
