package com.example.notebook.repositories;

import com.example.notebook.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Integer> {
    List<Note> findAllByOrderByDateStartAsc();
    List<Note> findAllByOrderByDateStartDesc();
    List<Note> findAllByOrderByDateEndAsc();
    List<Note> findAllByOrderByDateEndDesc();
    List<Note> findNotesByDoneOrderByDateEndAsc(Boolean done);
    /*List<Note> findAllByDoneFalseOrderByDateEndAsc();*/

}
