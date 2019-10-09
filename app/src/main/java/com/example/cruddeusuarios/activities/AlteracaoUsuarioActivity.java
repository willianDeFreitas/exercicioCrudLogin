package com.example.cruddeusuarios.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cruddeusuarios.R;
import com.example.cruddeusuarios.dto.DtoUser;
import com.example.cruddeusuarios.helpers.UsuarioAdapter;
import com.example.cruddeusuarios.services.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlteracaoUsuarioActivity extends AppCompatActivity {

    public static final String TAG = "AlteracaoUsuarioActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alteracao_usuario);
        buscaDadosParaPreenchimento();
    }

    private void preencheFormulario(List<DtoUser> lista){
        RecyclerView mRecyclerView = findViewById(R.id.rv_todos_usuarios);
        UsuarioAdapter mAdapter = new UsuarioAdapter(this, lista);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void alterar(View view) {
        String nome = ((EditText)findViewById(R.id.et_cadastro_usuario_nome)).getText().toString();
        String telefone = ((EditText)findViewById(R.id.et_cadastro_usuario_telefone)).getText().toString();
        String email = ((EditText)findViewById(R.id.et_cadastro_usuario_email)).getText().toString();
        String senha = ((EditText)findViewById(R.id.et_cadastro_usuario_password)).getText().toString();

        DtoUser dtoUser =  new DtoUser(email, nome, senha, telefone);

        RetrofitService.getServico(this).cadastraUsuario(dtoUser).enqueue(new Callback<DtoUser>() {
            @Override
            public void onResponse(Call<DtoUser> call, Response<DtoUser> response) {
                Toast.makeText(AlteracaoUsuarioActivity.this, "Usuário cadastrado com ID: " + response.body().getId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DtoUser> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }



    private void buscaDadosParaPreenchimento(){
        //# Rercuperando token salvo na activity de login
        SharedPreferences sp = getSharedPreferences("dados", 0);
        String token = sp.getString("token",null);
        //#

        RetrofitService.getServico(this).buscaUsuario("Bearer "+token).enqueue(new Callback<List<DtoUser>>() {
            @Override
            public void onResponse(Call<List<DtoUser>> call, Response<List<DtoUser>> response) {
                List<DtoUser> lista = response.body();
                preencheFormulario(lista);
            }

            @Override
            public void onFailure(Call<List<DtoUser>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
