package com.example.livros.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.livros.Controller.ControllerLogin;
import com.example.livros.R;
import com.example.livros.Tools.Globais;

public class ViewLogin extends AppCompatActivity {

    Context context;
    EditText login;
    EditText senha;
    Button btnLogin;
    ControllerLogin controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = ViewLogin.this;

        login = findViewById(R.id.txtLogin);
        senha = findViewById(R.id.txtSenha);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller = new ControllerLogin(context);
                String txtlogin = login.getText().toString();
                String txtsenha = senha.getText().toString();

                if(controller.buscar(txtlogin, txtsenha) != null){
                    Intent tela = new Intent(context, ViewMenu.class);
                    startActivity(tela);
                    finish();
                    Globais.exibirMensagem(context, "Logado!");
                }else{
                    Globais.exibirMensagem(context, "Senha ou usuario incorreto");
                }

            }
        });
    }
}