package org.example.springdemo.service;

import org.example.springdemo.entity.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

private final List<Note> notes = new ArrayList<>();
private long nextId = 1;

@Override
public List<Note> listAll(){
    return new ArrayList<>(notes);
}
@Override
    public  Note add(Note note) {
        note.setId(nextId++);
        notes.add(note);
        return note;
    }
    @Override
    public void deleteById(long id){
        boolean removed = notes.removeIf(note -> note.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("Note with id " + id + " does not exist.");
        }
    }
    @Override
    public void update(Note note){
        long id = note.getId();
        Note existingNote = getById(id);
        if (existingNote != null) {
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
        } else {
            throw new IllegalArgumentException("Note with id " + id + " does not exist.");
        }
    }

    @Override
    public Note getById(long id){
    return notes.stream()
            .filter(note -> note.getId() == id)
            .findFirst()
            .orElseThrow(() ->new IllegalArgumentException("Note with id " + id + " does not exist" ));
    }
}
