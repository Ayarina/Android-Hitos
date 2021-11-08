package com.example.agenda.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.R;
import com.example.agenda.TinyDB;
import com.example.agenda.UserAdapter;
import com.example.agenda.Usuario;
import com.example.agenda.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    private UserAdapter userAdapter;
    private TinyDB tinyDB;
    private RecyclerView recyclerView;
    private ArrayList<Object> usuarios;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Inicializamos la base de datos
        tinyDB = new TinyDB(getContext());
        //Inicializamos el ArrayList (comprobando antes si esta vacío o no)
        if(tinyDB.getListObject("key", Usuario.class) != null)
            usuarios = tinyDB.getListObject("UsersData", Usuario.class);
        else
            usuarios = new ArrayList<>();
        //Inicializamos el adaptador
        userAdapter = new UserAdapter(usuarios);

        //Configuramos el RecyclerView con el UserAdapter como su controlador
        recyclerView = (RecyclerView) root.findViewById(R.id.contact_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(userAdapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}