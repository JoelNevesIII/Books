package com.example.livros.Controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.livros.Database.DadosOpenHelper;
import com.example.livros.Database.Tabelas;
import com.example.livros.Model.ModelLogin;
import com.example.livros.Tools.Globais;

public class ControllerLogin {

    private SQLiteDatabase conexao;
    private Context context;

    public ControllerLogin(Context context){
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }

    public ModelLogin buscar(String login, String senha) {
        ModelLogin objeto = null;

        try {

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_LOGIN);
            sql.append(" WHERE login = '"+login+"' AND senha = '"+senha+"'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if(resultado.moveToNext()){
                objeto = new ModelLogin();
                objeto.setIdLogin(resultado.getInt(resultado.getColumnIndexOrThrow("idLogin")));
                objeto.setLogin(resultado.getString(resultado.getColumnIndexOrThrow("login")));
                objeto.setSenha(resultado.getString(resultado.getColumnIndexOrThrow("senha")));
            }

            return objeto;

        } catch (Exception ex) {
            Globais.exibirMensagem(context, ex.getMessage());
            return objeto;
        }
    }

}
