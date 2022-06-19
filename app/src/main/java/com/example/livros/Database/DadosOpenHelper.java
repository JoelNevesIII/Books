package com.example.livros.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.livros.Tools.Globais;

public class DadosOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 4; //versão do banco de dados
    private static final String NM_BANCO = "banco";
    private Context context;

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Tabela de usuarios
        try{
            StringBuilder sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tabelas.TB_LOGIN);
            sql.append(" ( ");
            sql.append(" idLogin INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" login VARCHAR(30) NOT NULL, ");
            sql.append(" senha VARCHAR(30) NOT NULL ");
            sql.append(" ) ");
            db.execSQL(sql.toString());
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
        }

        //Insert do usuario teste
        try{
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ");
            sql.append(Tabelas.TB_LOGIN);
            sql.append(" VALUES(1,'login','123')");
            db.execSQL(sql.toString());
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
        }

        //Tabela de Autores
        try{
            StringBuilder sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tabelas.TB_AUTORES);
            sql.append(" ( ");
            sql.append(" idAutor INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" autor VARCHAR(30) NOT NULL ");
            sql.append(" ) ");
            db.execSQL(sql.toString());
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
        }

        //Tabela de Livros
        try{
            StringBuilder sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tabelas.TB_LIVROS);
            sql.append(" ( ");
            sql.append(" idLivro INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" titulo VARCHAR(30) NOT NULL, ");
            sql.append(" categoria VARCHAR(30) NOT NULL, ");
            sql.append(" paginas INTEGER NOT NULL, ");
            sql.append(" idAutor Integer FOREIGNKEY REFERENCES autores(idAutor) ");
            sql.append(" ) ");
            db.execSQL(sql.toString());
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            //Tabela de autores Version 2
            if (VERSION <= 2){
                StringBuilder sql = new StringBuilder();
                sql.append(" CREATE TABLE IF NOT EXISTS ");
                sql.append(Tabelas.TB_AUTORES);
                sql.append(" ( ");
                sql.append(" idAutor INTEGER PRIMARY KEY AUTOINCREMENT, ");
                sql.append(" autor VARCHAR(30) NOT NULL ");
                sql.append(" ) ");
                db.execSQL(sql.toString());
            }
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
        }

        try{
            //Tabela de livros Version 4
            if (VERSION <= 4){
                StringBuilder sql = new StringBuilder();
                sql.append(" CREATE TABLE IF NOT EXISTS ");
                sql.append(Tabelas.TB_LIVROS);
                sql.append(" ( ");
                sql.append(" idLivro INTEGER PRIMARY KEY AUTOINCREMENT, ");
                sql.append(" titulo VARCHAR(30) NOT NULL, ");
                sql.append(" categoria VARCHAR(30) NOT NULL, ");
                sql.append(" paginas INTEGER NOT NULL, ");
                sql.append(" idAutor Integer FOREIGNKEY REFERENCES autores(idAutor) ");
                sql.append(" ) ");
                db.execSQL(sql.toString());
            }
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
        }


    }
}
