package com.example.cruddeusuarios.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cruddeusuarios.R;
import com.example.cruddeusuarios.dto.DtoUser;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioHolder> {
    //UsuarioAdapter.UsuarioHolder siginifica que será criada uma classe interna UsuarioHolder dentro de UsuarioAdapter
    private LayoutInflater mInflater;//objeto que "infla" o layout do item de lista do recyclerview
    private Context context;//activity que está exibindo o recyclerview
    private List<DtoUser> lista;//fonte dos dados da lista a ser exibida

    public UsuarioAdapter(Context context, List<DtoUser> lista) {
        this.context = context;
        this.lista = lista;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public  UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View mItemView = mInflater.inflate(R.layout.recyclerview_layout_item_todos_usuarios, parent, false);
        return new UsuarioHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {
        String nome = lista.get(position).getName();
        holder.nome.setText(nome);
    }

    @Override
    public int getItemCount(){
        return lista.size();
    }

    public class UsuarioHolder extends RecyclerView.ViewHolder {
        final UsuarioAdapter usuarioAdapter;
        public final TextView nome;

        public UsuarioHolder(@NonNull View itemView, UsuarioAdapter usuarioAdapter) {
            super(itemView);
            this.usuarioAdapter = usuarioAdapter;
            nome = itemView.findViewById(R.id.tv_recyclerview_nome_usuario);
        }

    }
}
