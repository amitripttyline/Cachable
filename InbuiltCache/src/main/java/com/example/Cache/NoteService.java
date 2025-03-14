package com.example.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // Get all notes (cached)
    @Cacheable(value = "notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Get single note by ID (cached)
    @Cacheable(value = "note", key = "#id")
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    // Create a new note
    @CachePut(value = "note", key = "#note.id")
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    // Update a note
    @CachePut(value = "note", key = "#id")
    public Note updateNote(Long id, Note updatedNote) {
        if (noteRepository.existsById(id)) {
            updatedNote.setId(id);
            return noteRepository.save(updatedNote);
        }
        return null;
    }

    // Delete a note
    @CacheEvict(value = "note", key = "#id")
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    // Clear all cache
    @CacheEvict(value = "notes", allEntries = true)
    public void clearCache() {
        System.out.println("Cache cleared");
    }
}
