package com.myproject.mynotes.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.mynotes.Controller.NoteController;
import com.myproject.mynotes.View.MainActivity;
import com.myproject.mynotes.Model.Note;
import com.myproject.mynotes.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerNotesAdapter extends RecyclerView.Adapter<RecyclerNotesAdapter.ViewHolder> {
    Context context;
    List<Note> arrNotes = new ArrayList<>();
    NoteController noteController;

    public RecyclerNotesAdapter(Context context, List<Note> arrNotes) {
        this.context = context;
        this.arrNotes = arrNotes;
        this.noteController = new NoteController((MainActivity) context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(arrNotes.get(position).getTitle());
        holder.txtContent.setText(arrNotes.get(position).getContent());

        holder.llrow.setOnLongClickListener(view -> {
            deleteItem(position);

            return true;
        });
    }

    private void deleteItem(int position) {
        new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you Sure? You want to Delete.")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    noteController.onDeleteButtonClicked(new Note(arrNotes.get(position).getId(), arrNotes.get(position).getTitle(), arrNotes.get(position).getContent()));
                }).setNegativeButton("No", (dialogInterface, i) -> {

                }).show();
    }

    @Override
    public int getItemCount() {
        return arrNotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtContent;
        LinearLayout llrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);
            llrow = itemView.findViewById(R.id.llRow);
        }
    }
}
