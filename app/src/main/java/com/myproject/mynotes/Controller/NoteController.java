package com.myproject.mynotes.Controller;

import com.myproject.mynotes.Model.Note;
import com.myproject.mynotes.Model.NotesDBHelper;
import com.myproject.mynotes.View.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class NoteController {
    NotesDBHelper notesDBHelper;
    MainActivity view;

    public NoteController(MainActivity view) {
        this.view = view;
        notesDBHelper = NotesDBHelper.getInstance(view);
    }

    public void onViewLoaded() {
        view.showNotes();
    }

    public ArrayList<Note> getAllNotes() {
        return (ArrayList<Note>) notesDBHelper.noteDao().getAllNotes();
    }

    public void onAddButtonClicked(Note note) {
        notesDBHelper.noteDao().insertNote(note);
        onViewLoaded();
    }

    public void onDeleteButtonClicked(Note note) {
        notesDBHelper.noteDao().deleteNote(note);
        onViewLoaded();
    }

}
