package com.example.livros.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.livros.Adapter.AdapterLivro;
import com.example.livros.Controller.ControllerLivro;
import com.example.livros.Model.ModelLivro;
import com.example.livros.R;
import com.example.livros.Tools.Globais;

import java.util.ArrayList;

public class ViewListLivro extends AppCompatActivity {

    Context context;
    Button btnNovo;
    ControllerLivro controller;
    ArrayList<ModelLivro> listagem;
    ListView ltvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_livro);

        context = ViewListLivro.this;
        btnNovo = findViewById(R.id.btnNovoLivro);
        ltvLista = findViewById(R.id.ltvLivros);

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(context, ViewCadLivro.class);
                startActivity(tela);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista(){
        try {

            controller = new ControllerLivro(context);
            listagem = controller.listaLivro();

            AdapterLivro adapter = new AdapterLivro(context, listagem);
            ltvLista.setAdapter(adapter);

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }
}