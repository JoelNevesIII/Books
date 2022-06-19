package com.example.livros.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.livros.Controller.ControllerLivro;
import com.example.livros.Model.ModelAutores;
import com.example.livros.Model.ModelLivro;
import com.example.livros.R;
import com.example.livros.Tools.Globais;
import com.example.livros.View.ViewCadLivro;

import java.util.ArrayList;

public class AdapterLivro extends ArrayAdapter<ModelLivro> {

    private final Context context;
    private final ArrayList<ModelLivro> elementos;
    ControllerLivro controller;

    public AdapterLivro(Context context, ArrayList<ModelLivro> elementos) {
        super(context, R.layout.item_lista_livro, elementos);
        this.context = context;
        this.elementos = elementos;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            ModelLivro objetoLivro = elementos.get(position);


            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista_livro, parent, false);

            TextView txtLivro = rowView.findViewById(R.id.txtListLivro);
            TextView txtAutor = rowView.findViewById(R.id.txtListLivroAutor);


            ControllerLivro controllerLivro = new ControllerLivro(context);
            ModelLivro livro = controllerLivro.buscar();
            ModelAutores autor = controllerLivro.buscarAutor(objetoLivro.getIdAutor());
            if (livro != null && autor != null) {
                txtAutor.setText("Autor: " + autor.getAutor());
                txtLivro.setText(objetoLivro.getTitulo());
            }

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tela = new Intent(context, ViewCadLivro.class);
                    tela.putExtra("id", objetoLivro.getIdLivro());
                    context.startActivity(tela);
                }
            });

            return rowView;

        } catch (Exception ex) {
            Log.e("ERRO_ADAPTER", ex.getMessage());
            return null;
        }

    }
}

