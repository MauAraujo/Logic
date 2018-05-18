package com.example.mau.logic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class TableOutput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_output);
        TextView textView = (TextView) findViewById(R.id.textView);

        for(int i = 0; i < TruthTableGUI.result.size(); i++) {
            textView.append(TruthTableGUI.result.get(i));
        }
    }

    @Override
    public void onBackPressed() {
        //Regresar a primera actividad si se presiona Back
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
