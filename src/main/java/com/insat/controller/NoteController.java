package com.insat.controller;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.insat.model.Note;
import com.insat.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteService noteService;


    @GetMapping("/notes")
    public List getAll(){
        return noteService.getNotes();
    }


    @GetMapping("/notes/{id}")
    public Optional<Note> getOneWithId(@PathVariable("id")  Long id){
        System.out.println(id);
        return noteService.getById(id);
    }

    @PostMapping("/notes/add")
    public Note add(@Valid @RequestBody Note note){
        return  noteService.addNotes(note);
    }

    @DeleteMapping("/notes/delete/{id}")
    public void deletebyid( @PathVariable Long id){
        noteService.deleteNoteById(id);
    }

    @PutMapping("/notes/update")
    public ResponseEntity<Note> update( @RequestBody Note newNote){
        Optional<Note> oldNote = getOneWithId(newNote.getId());
        if(oldNote.isPresent()){
            return new ResponseEntity<Note>(add(newNote),HttpStatus.OK);
        }else{
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }
    }
}
