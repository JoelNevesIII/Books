package com.example.livros.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.livros.Database.DadosOpenHelper;
import com.example.livros.Database.Tabelas;
import com.example.livros.Model.ModelAutores;
import com.example.livros.Tools.Globais;

public class ControllerAutor {
    private SQLiteDatabase conexao;
    private Context context;

    public ControllerAutor(Context context){
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }
    public boolean incluir (ModelAutores objeto){

        try {
            ContentValues valores = new ContentValues();

            valores.put("autor", objeto.getAutor());

            conexao.insertOrThrow(Tabelas.TB_AUTORES, null, valores);

            return true;
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

}
