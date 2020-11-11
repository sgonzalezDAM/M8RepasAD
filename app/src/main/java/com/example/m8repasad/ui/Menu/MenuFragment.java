package com.example.m8repasad.ui.Menu;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.m8repasad.FragmentInteractionListener;
import com.example.m8repasad.Incidencia;
import com.example.m8repasad.R;
import com.example.m8repasad.db.IncidenciaDBHelper;
import com.example.m8repasad.singletonIncidencias;
import com.example.m8repasad.ui.AddIncidence.AddIncidenceFragment;
import com.example.m8repasad.ui.Help.HelpFragment;
import com.example.m8repasad.ui.ListIncidence.ListIncidenceFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    private MenuViewModel mViewModel;

    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    private Button AddIncidenceBtn, ListIncidencesBtn, RemoveIncidencesBtn, HelpBtn, LoadDB;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        View root = inflater.inflate(R.layout.menu_fragment, container, false);

        AddIncidenceBtn = (Button) root.findViewById(R.id.AddIncidenceBtn);
        ListIncidencesBtn = (Button) root.findViewById(R.id.ListIncidencesBtn);
        RemoveIncidencesBtn = (Button) root.findViewById(R.id.RemoveIncidencesBtn);
        HelpBtn = (Button) root.findViewById(R.id.HelpBtn);
        LoadDB = (Button) root.findViewById(R.id.LoadBtn);

        dbHelper = new IncidenciaDBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        AddIncidenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentAddIncidence = new AddIncidenceFragment();
                FragmentInteractionListener fragmentInteractionListener = (FragmentInteractionListener)getActivity();
                fragmentInteractionListener.changeFragment(fragmentAddIncidence);
            }
        });

        ListIncidencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentListIncidence = new ListIncidenceFragment();
                FragmentInteractionListener fragmentInteractionListener = (FragmentInteractionListener)getActivity();
                fragmentInteractionListener.changeFragment(fragmentListIncidence);
            }
        });

        RemoveIncidencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Fragment fragment = new RemoveIncidenceFragment();
                FragmentInteractionListener fragmentInteractionListener = (FragmentInteractionListener)getActivity();
                fragmentInteractionListener.changeFragment(fragment);
                */
                singletonIncidencias.getNewInstance().removeEntries();
                Toast.makeText(getContext(), "Toast cleared", Toast.LENGTH_SHORT).show();
            }
        });

        HelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HelpFragment();
                FragmentInteractionListener fragmentInteractionListener = (FragmentInteractionListener)getActivity();
                fragmentInteractionListener.changeFragment(fragment);
            }
        });

        LoadDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Incidencia> incidencias;

                incidencias = dbHelper.getAllIncidencies();
                singletonIncidencias.getNewInstance().addArrayList(incidencias);

            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);
        // TODO: Use the ViewModel
    }

}