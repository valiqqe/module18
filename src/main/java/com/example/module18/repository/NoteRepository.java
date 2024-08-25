package com.example.module18.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.module18.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
