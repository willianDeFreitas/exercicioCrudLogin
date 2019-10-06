package com.example.cruddeusuarios.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.cruddeusuarios.R;
import com.example.cruddeusuarios.dto.DtoUser;
import com.example.cruddeusuarios.helpers.UsuarioAdapter;
import com.example.cruddeusuarios.services.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LisaUsuariosActivity extends AppCompatActivity {

    public static final String TAG = "ListaUsuariosActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisa_usuarios);
        buscaDados();
    }

    private void preencheRecyclerview(List<DtoUser> lista){
        RecyclerView mRecyclerView = findViewById(R.id.rv_todos_usuarios);
        UsuarioAdapter mAdapter = new UsuarioAdapter(this, lista);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void buscaDados(){
        //# Rercuperando token salvo na activity de login
        SharedPreferences sp = getSharedPreferences("dados", 0);
        String token = sp.getString("token",null);
        //#

        RetrofitService.getServico(this).todosUsuarios("Bearer" + token).enqueue(new Callback<List<DtoUser>>() {
            @Override
            public void onResponse(Call<List<DtoUser>> call, Response<List<DtoUser>> response) {
                List<DtoUser> lista = response.body();
                preencheRecyclerview(lista);
            }

            @Override
            public void onFailure(Call<List<DtoUser>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
