package com.example.mau.logic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TableOutput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_output);
        TextView textView = (TextView) findViewById(R.id.textView);

        for (String[] columna: mainclass.todo) {
           for(int i = 0; i < columna.length; i++){
               //textView.append(columna[i]);
               System.out.println(columna[i]);
           }
        }
    }

    @Override
    public void onBackPressed() {
        //Regresar a primera actividad si se presiona Back
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
