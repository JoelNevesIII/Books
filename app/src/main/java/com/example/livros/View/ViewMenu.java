package com.example.livros.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.livros.R;

public class ViewMenu extends AppCompatActivity {

    Context context;
    Button btnCadAutor;
    Button btnCadLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);

        context = ViewMenu.this;
        btnCadAutor = findViewById(R.id.btnCadAutor);
        btnCadLivro = findViewById(R.id.btnCadLivro);

        btnCadAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(context, ViewCadAutor.class);
                startActivity(tela);
            }
        });

        btnCadLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(context, ViewListLivro.class);
                startActivity(tela);
            }
        });
    }
}