package com.myproject.mynotes.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, exportSchema = false, version = 1)
public abstract class NotesDBHelper extends RoomDatabase {

    private static final String DB_NAME = "notes_db";
    private static NotesDBHelper instance;

    public static synchronized NotesDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, NotesDBHelper.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract NoteDao noteDao();
}
