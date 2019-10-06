package com.example.cruddeusuarios.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.cruddeusuarios.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case R.id.action_alteracao_usuario:
                //navegar para activity(new Intent(desta classe, para outra Activity)
                startActivity(new Intent(this, AlteracaoUsuarioActivity.class));
                return true;
            case R.id.action_cadastro_de_usuario:
                startActivity(new Intent(this, CadastroDeUsuarioActivity.class));
                return true;
            case R.id.action_excluir_usuario:
                startActivity(new Intent(this, ExcluirUsuarioActivity.class));
                return true;
            case R.id.action_lista_usuarios:
                startActivity(new Intent(this, LisaUsuariosActivity.class));
                return true;
            case R.id.action_login:
                startActivity(new Intent(this, LoginActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
