package com.example.android_lab_1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class TableActivity extends AppCompatActivity {
    ArrayList<String> entries = new ArrayList<String>();
    ArrayList<String> selectedEntries = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView entriesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Collections.addAll(entries);
        entriesList = findViewById(R.id.entriesList);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, entries);
        entriesList.setAdapter(adapter);

        entriesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                String entrie = adapter.getItem(position);
                if(entriesList.isItemChecked(position))
                    selectedEntries.add(entrie);
                else
                    selectedEntries.remove(entrie);
            }
        });
    }

    public void addEntrie(View view){

        EditText entrieET = findViewById(R.id.entrie);
        String entrie  = entrieET.getText().toString();
        if(!entrie.isEmpty()){
            adapter.add(entrie);
            entrieET.setText("");
            adapter.notifyDataSetChanged();
        }
    }
    public void removeEntrie(View view){

        if(selectedEntries.size() == 0 && entries.size() != 0) {
            int numOfLastEntrie = entries.size() - 1;
            adapter.remove(entries.get(numOfLastEntrie));
        }

        for(int i=0; i < selectedEntries.size();i++){
            adapter.remove(selectedEntries.get(i));
        }
        entriesList.clearChoices();
        selectedEntries.clear();

        adapter.notifyDataSetChanged();
    }
}
