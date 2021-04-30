package com.anvtech.notes.notes.controller;

import com.anvtech.notes.notes.entities.Notes;
import com.anvtech.notes.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NotesController {

    @Autowired
    NotesRepository notesRepository;

    @GetMapping("/get-notes")
    public List<Notes> getNotes() {
        return notesRepository.findAll();
    }

    @GetMapping("/get-notes/{id}")
    public Notes getNoteById(@PathVariable Integer id) {
        Optional<Notes> note = notesRepository.findById(id);
        if (note.isPresent())
            return note.get();
        return null;
    }

    @PostMapping("/create-note")
    public int createNote(@RequestBody Notes notes) {
        Notes createdNote = notesRepository.save(notes);
        return createdNote.getId();
    }

    @PutMapping("/create-note")
    public int updateNote(@RequestBody Notes notes) {
        Optional<Notes> byId = notesRepository.findById(notes.getId());
        if (byId.isPresent()) {
            Notes noteToUpdate = byId.get();
            noteToUpdate.setDescription(notes.getDescription());
            noteToUpdate.setTitle(notes.getTitle());
            Notes savedNote = notesRepository.save(noteToUpdate);
            return savedNote.getId();
        }
        return -1;
    }

    @GetMapping("/delete/{id}")
    public void deleteNote(@PathVariable Integer id) {
        Optional<Notes> note = notesRepository.findById(id);
        if (note.isPresent())
            notesRepository.deleteById(id);
    }
}
