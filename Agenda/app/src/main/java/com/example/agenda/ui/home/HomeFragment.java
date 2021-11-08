package com.example.agenda.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.agenda.R;
import com.example.agenda.TinyDB;
import com.example.agenda.Usuario;
import com.example.agenda.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    private TinyDB tinyDB;
    private EditText nombreInput, apellidosInput, numeroInput;
    private Button botonGuardar;
    private ArrayList<Object> usuarios;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Inicializamos la base de datos
        tinyDB = new TinyDB(getContext());
        //Inicializamos el ArrayList (comprobando antes si esta vacío o no)
        if(tinyDB.getListObject("key", Usuario.class) != null)
            usuarios = tinyDB.getListObject("UsersData", Usuario.class);
        else
            usuarios = new ArrayList<>();

        //asignamos las referencias
        nombreInput = root.findViewById(R.id.home_nombre);
        apellidosInput = root.findViewById(R.id.home_apellidos);
        numeroInput = root.findViewById(R.id.home_numero);
        botonGuardar = root.findViewById(R.id.main_boton);

        //darle que si le ha dado al vboton, que se añada a la BD
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario(nombreInput.getText().toString(), apellidosInput.getText().toString(),
                        numeroInput.getText().toString());
                usuarios.add(usuario);
                tinyDB.putListObject("UsersData", usuarios);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}