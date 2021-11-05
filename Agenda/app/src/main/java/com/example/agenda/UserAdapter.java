package com.example.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.agenda.TinyDB;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Comunicación entre fragments y el recyclerview

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<Object> usuarios;
    private TinyDB tinyDB;

    public UserAdapter(ArrayList<Object> usuarios){
        this.usuarios = usuarios;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //asignamos el user_layout al recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
        tinyDB = new TinyDB(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Manejo de cada user_layout individualmente, se podrá acceder a todos los campos definidos en
        //ViewHolder mediante el parametro holder. Con position se puede acceder a cada usuario
        //replace the contents of the view con el holder, que es basicamente la vista del user_layout
        //obtenemos el usuario de la BD
        Usuario usuario = (Usuario) usuarios.get(position);
        //Seteamos los datos del usuario añadido al crearse.
        holder.nombreUsuario.setText(usuario.getNombre());
        holder.apellidosUsuario.setText(usuario.getApellidos());
        holder.numeroUsuario.setText(usuario.getNumero());
    }

    @Override
    public int getItemCount() { //num de usuarios agregados
        return usuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Comunicacion directa con el user_layout
        private TextView nombreUsuario, apellidosUsuario, numeroUsuario;
        private Button boton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Le asignamos los elementos del userLayout
            nombreUsuario = itemView.findViewById(R.id.nombreUsuario);
            apellidosUsuario = itemView.findViewById(R.id.apellidosUsuario);
            numeroUsuario = itemView.findViewById(R.id.numeroUsuario);
            boton = itemView.findViewById(R.id.botonEliminar);
        }
    }
}
