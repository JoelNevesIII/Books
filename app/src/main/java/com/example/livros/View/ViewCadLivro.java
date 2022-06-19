package com.example.livros.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.livros.Controller.ControllerLivro;
import com.example.livros.Model.ModelAutores;
import com.example.livros.Model.ModelLivro;
import com.example.livros.R;
import com.example.livros.Tools.Globais;

import java.util.ArrayList;

public class ViewCadLivro extends AppCompatActivity {

    Context context;
    ControllerLivro controller;
    EditText txtTitulo;
    EditText txtCategoria;
    EditText txtpaginas;
    Spinner spinAutor;
    Button btnCadLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cad_livro);

        context = ViewCadLivro.this;
        controller = new ControllerLivro(context);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtpaginas = findViewById(R.id.txtPaginas);
        spinAutor = findViewById(R.id.SpinAutor);
        btnCadLivro = findViewById(R.id.btnSalvarLivro);



        ArrayList<ModelAutores> lista = controller.lista();
        ArrayAdapter<ModelAutores> adapterAutores = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, lista);
        spinAutor.setAdapter(adapterAutores);

        btnCadLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModelLivro objeto = new ModelLivro();

                objeto.setTitulo(txtTitulo.getText().toString());
                objeto.setCategoria(txtCategoria.getText().toString());
                objeto.setPaginas(Integer.parseInt(txtpaginas.getText().toString()));
                objeto.setIdAutor(Integer.parseInt(String.valueOf(spinAutor.getSelectedItemId() + 1)));

                controller.incluir(objeto);

                Globais.exibirMensagem(context,"Livro Cadastrado");
                finish();
            }
        });

    }
}