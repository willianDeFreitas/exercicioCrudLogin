package com.example.cruddeusuarios.services;

import com.example.cruddeusuarios.activities.CadastroDeUsuarioActivity;
import com.example.cruddeusuarios.dto.DtoLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceDeServicos {
    //@POST("/users")
   // Call<DtoUser> cadastraUsuario(@Body DtoUser dtoUser);

    @POST("/auth/login")
    Call<DtoLogin> login(@Body DtoLogin dtoLogin);
}
