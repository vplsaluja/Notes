package com.anvtech.notes.notes.repository;

import com.anvtech.notes.notes.entities.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes,Integer> {
}
