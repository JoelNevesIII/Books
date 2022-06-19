package com.example.livros.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.livros.Controller.ControllerAutor;
import com.example.livros.Model.ModelAutores;
import com.example.livros.R;
import com.example.livros.Tools.Globais;

public class ViewCadAutor extends AppCompatActivity {

    Context context;
    ControllerAutor controller;
    Button btnCadastrar;
    EditText txtAutor;
    ModelAutores objeto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cad_autor);

        context = ViewCadAutor.this;
        btnCadastrar = findViewById(R.id.btnCadAutor);
        txtAutor = findViewById(R.id.txtAutor);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller = new ControllerAutor(context);

                objeto = new ModelAutores();
                objeto.setAutor(txtAutor.getText().toString());

                if(controller.incluir(objeto))
                Globais.exibirMensagem(context, "Autor cadastrado");
                finish();
            }
        });
    }
}