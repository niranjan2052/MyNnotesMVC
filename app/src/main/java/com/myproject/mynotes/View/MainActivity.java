package com.myproject.mynotes.View;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myproject.mynotes.Controller.NoteController;
import com.myproject.mynotes.Model.Note;
import com.myproject.mynotes.R;
import com.myproject.mynotes.View.Adapter.RecyclerNotesAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView edtTxtNotes;
    Button btnCreateNote;
    FloatingActionButton fabActionBtn;
    RecyclerView recyclerView;
    LinearLayout llNoNotes;

    NoteController noteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        showNotes();

        fabActionBtn.setOnClickListener(view -> {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.add_note_lay);

            EditText editTitle, edtContent;
            Button btnAddNote;

            editTitle = dialog.findViewById(R.id.edtTitle);
            edtContent = dialog.findViewById(R.id.edtContent);
            btnAddNote = dialog.findViewById(R.id.btnAddNote);

            btnAddNote.setOnClickListener(view1 -> {
                String title = editTitle.getText().toString();
                String content = edtContent.getText().toString();

                if (!content.isEmpty() && !title.isEmpty()) {
                    noteController.onAddButtonClicked(new Note(title, content));
                    dialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter Title and Content", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        });

        btnCreateNote.setOnClickListener(view -> {
            fabActionBtn.performClick();
        });

    }

    public void showNotes() {
        ArrayList<Note> arrNotes = noteController.getAllNotes();

        if (!arrNotes.isEmpty()) {
            llNoNotes.setVisibility(RecyclerView.GONE);
            recyclerView.setVisibility(RecyclerView.VISIBLE);
            recyclerView.setAdapter(new RecyclerNotesAdapter(this, arrNotes));

        } else {
            llNoNotes.setVisibility(RecyclerView.VISIBLE);
            recyclerView.setVisibility(RecyclerView.GONE);
        }
    }

    private void initViews() {
        edtTxtNotes = findViewById(R.id.edtTxtNotes);
        btnCreateNote = findViewById(R.id.btnCreateNote);
        fabActionBtn = findViewById(R.id.fabActionBtn);
        recyclerView = findViewById(R.id.recyclerView);
        llNoNotes = findViewById(R.id.llNoNotes);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        noteController = new NoteController(this);
    }
}