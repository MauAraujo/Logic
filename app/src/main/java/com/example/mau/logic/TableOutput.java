package com.example.mau.logic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;

public class TableOutput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_output);
        TextView textView = findViewById(R.id.textView);

        textView.setMovementMethod(new ScrollingMovementMethod());

        int i = 0;
        for (ArrayList<String> tablaf : mainclass.todo) {
            textView.append("\n" + mainclass.formulas.get(i) + " \n");
            for (String x : tablaf) {
                textView.append(x);
            }
            i++;
        }

        textView.append("\n" + mainclass.complete_formula + "\n");
    }

    @Override
    public void onBackPressed() {
        //Regresar a primera actividad si se presiona Back
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
