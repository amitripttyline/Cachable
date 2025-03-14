package com.example.redis_sb.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.redis_sb.Entity.Note;

public interface NoteRepo extends JpaRepository<Note, String> {

}
