package com.example.eric.memory.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eric.memory.R;
import com.example.eric.memory.model.Partida;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializamos el boton y le aplicamos un Listener para cuando sea pulsado
        Button btnCallActivity = (Button) findViewById(R.id.btnEmpezar);
        btnCallActivity.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
            Intent intent = new Intent(this, Joc.class);
            startActivity(intent);
    }
}

