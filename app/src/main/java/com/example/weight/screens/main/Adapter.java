package com.example.weight.screens.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;


import com.example.weight.App;
import com.example.weight.R;
import com.example.weight.model.Entity;
import com.example.weight.screens.details.NoteDetailsActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.NoteViewHolder> {

    private final SortedList<Entity> sortedList;

    public Adapter() {

        sortedList = new SortedList<>(Entity.class, new SortedList.Callback<Entity>() {
            @Override
            public int compare(Entity o1, Entity o2) {
                return (int) (o2.uid - o1.uid);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Entity oldItem, Entity newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Entity item1, Entity item2) {
                return item1.uid == item2.uid;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Entity> notes) {
        sortedList.replaceAll(notes);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView noteText_weight;
        TextView noteText_date;
        View delete;

        Entity note;

        boolean silentUpdate;

        public NoteViewHolder(@NonNull final View itemView) {
            super(itemView);

            noteText_weight = itemView.findViewById(R.id.note_text_weight);
            noteText_date = itemView.findViewById(R.id.note_text_date);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(view -> NoteDetailsActivity.start((Activity) itemView.getContext(), note));

            delete.setOnClickListener(view -> App.getInstance().getNoteDao().delete(note));
        }

        @SuppressLint("SetTextI18n")
        public void bind(Entity note) {
            this.note = note;

            noteText_weight.setText("" + note.current_weight);
            noteText_date.setText(note.current_date);

            silentUpdate = true;
            silentUpdate = false;
        }
    }
}