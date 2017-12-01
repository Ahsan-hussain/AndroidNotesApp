package com.example.developer.notesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class notesEditorActivity extends AppCompatActivity {

    int notesId;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor);
    editText=(EditText)findViewById(R.id.editText);

        Intent intent=getIntent();
        notesId=intent.getIntExtra("notesId",-1);
        if(notesId !=-1)
        {
            editText.setText(MainActivity.notes.get(notesId));

        }
        else
        {

            MainActivity.notes.add("");
            notesId=MainActivity.notes.size()-1;
            MainActivity.listAdapter.notifyDataSetChanged();

        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                MainActivity.notes.set(notesId,String.valueOf (charSequence));
                MainActivity.listAdapter.notifyDataSetChanged();

                SharedPreferences sp=getApplicationContext().getSharedPreferences("com.example.developer.notesapp",MODE_PRIVATE);
                HashSet<String> set=new HashSet<String>(MainActivity.notes);
                sp.edit().putStringSet("notes",set).apply();




            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }
}
