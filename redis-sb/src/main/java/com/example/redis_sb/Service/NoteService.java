package com.example.redis_sb.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.redis_sb.Entity.Note;
import com.example.redis_sb.Repositories.NoteRepo;

@Service
public class NoteService {
    private NoteRepo noteRepo;

    // constructor injection
    public NoteService(NoteRepo noteRepo){
        this.noteRepo = noteRepo;
    }

    //save
    @CachePut(value = "note", key = "#note.id")
    public Note createNote(Note note){
        note.setId(UUID.randomUUID().toString());
        return noteRepo.save(note);
    }

    //get

    public List<Note> getAll(){
        return noteRepo.findAll();
    }

    @Cacheable(value = "notes", key = "#noteId")
    public Note getById(String noteId){
        return noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note with given id is not found"));
    }

    //update
    public Note updateNote(String noteId, Note note){
        Note note1 = noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note with given id is not found"));
        note1.setTitle(note.getTitle());
        note1.setContent(note.getContent());
        note1.setLive(note.isLive());

        return noteRepo.save(note1);


    }

    //delete
    @CacheEvict(value= "notes", key = "#noteId")
    public void delete(String noteId){
        Note note1 = noteRepo.findById(noteId).orElseThrow(() -> new RuntimeException("Note with given id is not found"));
        noteRepo.delete(note1);

    }

}
