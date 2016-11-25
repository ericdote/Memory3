package com.example.eric.memory.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eric.memory.R;
import com.example.eric.memory.model.Partida;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializamos el boton y le aplicamos un Listener para cuando sea pulsado
        Button btnCallActivity = (Button) findViewById(R.id.btnEmpezar);
        btnCallActivity.setOnClickListener(this);
    }

    /**
     * Cuando reciba un onClick con id btnEmpezar crea un intent para lanzar la activity Joc
     * Antes de lanzar la activity mira que RadioButton ha sido seleccionado (dificultad) para enviar mas o menos cartas
     * Al lanzar la activity finaliza el esta.
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnEmpezar) {
            Intent intent = new Intent(this, Joc.class);
            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
            boolean salir = false;

            switch (rg.getCheckedRadioButtonId()) {
                case R.id.rbFacil: {
                    intent.putExtra("dificultad", 8);
                    salir = true;
                    break;
                }
                case R.id.rbMedio: {
                    intent.putExtra("dificultad", 10);
                    salir = true;
                    break;
                }
                case R.id.rbDificil: {
                    intent.putExtra("dificultad", 12);
                    salir = true;
                    break;
                }
                default: {
                    Toast.makeText(this, "Marca una dificultat", Toast.LENGTH_SHORT).show();

                    break;
                }
            }
            startActivity(intent);
            if (salir) {
                finish();
            }
        }
    }
}
