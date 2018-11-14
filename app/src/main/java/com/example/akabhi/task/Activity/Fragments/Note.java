package com.example.akabhi.task.Activity.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.akabhi.task.Activity.Adapter.NoteAdapter;
import com.example.akabhi.task.Activity.DataBase.DataBase;
import com.example.akabhi.task.R;

import java.util.ArrayList;

import PojoClasses.Note_Pojo;

public class Note extends Fragment {

    private NoteAdapter noteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, null);
        RecyclerView recyclerView = view.findViewById(R.id.noted);

        DataBase dataBase = new DataBase(getActivity());
        Cursor cursor = dataBase.Select_Function_Task();
        ArrayList<Note_Pojo> note_pojos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Note_Pojo note = new Note_Pojo();
            note.NotedTask = cursor.getString(1);
            note.NotedDate = cursor.getString(4);
            note_pojos.add(note);
        }

        noteAdapter = new NoteAdapter(getActivity(), note_pojos);
        recyclerView.setAdapter(noteAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }
}
