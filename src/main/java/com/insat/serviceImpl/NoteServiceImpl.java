package com.insat.serviceImpl;

import com.insat.model.Note;
import com.insat.repository.NoteRepository;
import com.insat.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    NoteRepository noteRepository;

    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note addNotes(Note note) {
        return      noteRepository.save(note);
    }

    @Override
    public void deleteNoteById(Long id) {
         noteRepository.deleteById(id);
    }

    @Override
    public Optional<Note> getById(Long id) {
        return noteRepository.findById(id);
    }




}
