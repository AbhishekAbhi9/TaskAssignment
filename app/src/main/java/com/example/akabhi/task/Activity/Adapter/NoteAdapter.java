package com.example.akabhi.task.Activity.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.akabhi.task.Activity.DataBase.DataBase;
import com.example.akabhi.task.R;

import java.util.ArrayList;

import PojoClasses.Note_Pojo;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.View_Holder> {

    private ArrayList<Note_Pojo> note_pojos;
    private Context context;

    public NoteAdapter(FragmentActivity activity, ArrayList<Note_Pojo> note_pojos) {
        context = activity;
        this.note_pojos = note_pojos;
    }

    @Override
    public int getItemCount() {
        return note_pojos.size();
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.notedadapter, parent, false);
        return new View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder view_holder, int position) {
        view_holder.date.setText(note_pojos.get(position).getNotedDate());
        view_holder.task.setText(note_pojos.get(position).getNotedTask());
    }


    public class View_Holder extends RecyclerView.ViewHolder {

        private TextView date, task;

        public View_Holder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            task = itemView.findViewById(R.id.task);
        }
    }

}
