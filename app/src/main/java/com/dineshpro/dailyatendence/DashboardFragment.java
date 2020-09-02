package com.dineshpro.dailyatendence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardFragment extends Fragment {

    LinearLayout giveAtendenceLayout,viewAtendenceLyout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dashboard_layout,container,false);

        giveAtendenceLayout=(LinearLayout)view.findViewById(R.id.giveAtendence_layout);
        viewAtendenceLyout=(LinearLayout)view.findViewById(R.id.viewAdendence_layout);


        giveAtendenceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AtendenceFragment atendenceFragment=new AtendenceFragment();
                addAtendenceFragment(atendenceFragment);
            }
        });

        viewAtendenceLyout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewAtendenceFragment viewAtendenceFragment=new ViewAtendenceFragment();

                addViewAtendenceFragment(viewAtendenceFragment);
            }
        });

        return view;
    }

    private void addViewAtendenceFragment(ViewAtendenceFragment viewAtendenceFragment) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment_container,viewAtendenceFragment);
        ft.commit();

    }

    private void addAtendenceFragment(AtendenceFragment atendenceFragment) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment_container,atendenceFragment);
        ft.commit();
    }
}
