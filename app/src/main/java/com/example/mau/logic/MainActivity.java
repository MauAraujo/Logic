package com.example.mau.logic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void readInput(View view){
        String input;
        ArrayList<String> tmps = new ArrayList<String>();

        //Leer formula dada y pasar al generador de tablas
        EditText textArea = findViewById(R.id.editText);
        input = textArea.getText().toString();

        mainclass.read(input);

        //Mostrar mensaje
        Context context = getApplicationContext();
        String text = "Elaborando tabla de verdad...";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //Pasar a segunda actividad
        Intent intent = new Intent(this, TableOutput.class);
        startActivity(intent);
    }
}


