package com.insat.service;

import com.insat.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
     List<Note> getNotes();

      Note addNotes(Note note);

      void deleteNoteById(Long id);

    Optional<Note> getById(Long id);

}
