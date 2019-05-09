package com.example.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.Map;


public class Helper {

    public static String saveNote(Note note, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        String id = String.valueOf(System.currentTimeMillis());
        editor.putString(id, note.getTitle());

        editor.commit();
        return id;

    }

    public static Arraylist<Note> getAllNotes(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        ArrayList<Note> notes = new ArrayList<>();

        Map<String, ?> key = sharedPreferences.getAll();

        for(Map.Entry<String, ?> entry : key.entrySet()){
            String savedData = (String) entry.getValue();

            if(savedData != null) {
                Note note = new Note(entry.getKey(), savedData);

                notes.add(note);
            }
        }
        return notes;
    }

    public static String removeNote(String id, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(id);

        editor.commit();
    }
}
