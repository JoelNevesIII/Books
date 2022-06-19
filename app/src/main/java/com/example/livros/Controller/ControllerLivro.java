package com.example.livros.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.livros.Database.DadosOpenHelper;
import com.example.livros.Database.Tabelas;
import com.example.livros.Model.ModelAutores;
import com.example.livros.Model.ModelLivro;
import com.example.livros.Tools.Globais;

import java.util.ArrayList;

public class ControllerLivro {

    private SQLiteDatabase conexao;
    private Context context;

    public ControllerLivro(Context context){
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }

    public ModelLivro buscar(){
        ModelLivro objeto = null;

        try{

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_LIVROS);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToNext()){
                objeto = new ModelLivro();

                objeto.setIdLivro(resultado.getInt(resultado.getColumnIndexOrThrow("idLivro")));
                objeto.setTitulo(resultado.getString(resultado.getColumnIndexOrThrow("titulo")));
                objeto.setCategoria(resultado.getString(resultado.getColumnIndexOrThrow("categoria")));
                objeto.setPaginas(resultado.getInt(resultado.getColumnIndexOrThrow("paginas")));
                objeto.setIdAutor(resultado.getInt(resultado.getColumnIndexOrThrow("idAutor")));
            }

            return objeto;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return objeto;
        }
    }

    public ModelAutores buscarAutor(int id){
        ModelAutores objeto = null;

        try{

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_AUTORES);
            sql.append(" WHERE idAutor = "+ id);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToNext()){
                objeto = new ModelAutores();
                objeto.setIdAutor(resultado.getInt(resultado.getColumnIndexOrThrow("idAutor")));
                objeto.setAutor(resultado.getString(resultado.getColumnIndexOrThrow("autor")));

            }
            return objeto;
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return objeto;
        }
    }

    public boolean incluir (ModelLivro objeto){

        try {
            ContentValues valores = new ContentValues();

            valores.put("titulo", objeto.getTitulo());
            valores.put("categoria", objeto.getCategoria());
            valores.put("paginas", objeto.getPaginas());
            valores.put("idAutor", objeto.getIdAutor());

            conexao.insertOrThrow(Tabelas.TB_LIVROS, null, valores);

            return true;
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public ArrayList<ModelAutores> lista(){

        ArrayList<ModelAutores> listagem = new ArrayList<>();

        try{

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_AUTORES);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.moveToFirst()){

                ModelAutores objeto;
                do{
                    objeto = new ModelAutores();

                    objeto.setIdAutor(resultado.getInt(resultado.getColumnIndexOrThrow("idAutor")));
                    objeto.setAutor(resultado.getString(resultado.getColumnIndexOrThrow("autor")));

                    listagem.add(objeto);
                }while (resultado.moveToNext());
            }

            return listagem;
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return listagem;
        }
    }

    public ArrayList<ModelLivro> listaLivro(){

        ArrayList<ModelLivro> listagem = new ArrayList<>();

        try{

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_LIVROS);

            Cursor resultado = conexao.rawQuery(sql.toString(), null);

            if (resultado.moveToFirst()){

                ModelLivro objeto;
                do{
                    objeto = new ModelLivro();

                    objeto.setIdAutor(resultado.getInt(resultado.getColumnIndexOrThrow("idLivro")));
                    objeto.setTitulo(resultado.getString(resultado.getColumnIndexOrThrow("titulo")));
                    objeto.setIdAutor(resultado.getInt(resultado.getColumnIndexOrThrow("idAutor")));
                    listagem.add(objeto);
                }while (resultado.moveToNext());
            }

            return listagem;
        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return listagem;
        }
    }
}
