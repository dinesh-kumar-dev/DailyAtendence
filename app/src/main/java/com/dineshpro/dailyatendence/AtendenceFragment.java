package com.dineshpro.dailyatendence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dineshpro.dailyatendence.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AtendenceFragment extends Fragment {

    List<User> userList;
    DatabaseReference databaseUser;

    EditText editTextName,editTextContact,editTextDate,editTextTime;
    Button buttonSaveAtendance,buttonAtendenceBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.atendance_layout,container,false);

        databaseUser = FirebaseDatabase.getInstance().getReference("user");
        userList=new ArrayList<>();

            editTextName=(EditText)view.findViewById(R.id.editTextName);
            editTextContact=(EditText)view.findViewById(R.id.editTextContact);
            editTextDate=(EditText)view.findViewById(R.id.editTextDate);
            editTextTime=(EditText)view.findViewById(R.id.editTextTime);

            buttonSaveAtendance=(Button)view.findViewById(R.id.buttonSaveAtendence);
            buttonAtendenceBack=(Button)view.findViewById(R.id.buttonSaveAtendenceBack);




            buttonSaveAtendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveAtendence();
                }
            });


           buttonAtendenceBack.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   DashboardFragment dashboardFragment=new DashboardFragment();
                   bacDashboardFragment(dashboardFragment);
               }
           });
        return view;
    }
//go back dashborad fragment code
    private void bacDashboardFragment(DashboardFragment dashboardFragment) {

        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment_container,dashboardFragment);
        ft.commit();
    }


    //save atendence code here
    private void saveAtendence() {
        String name=editTextName.getText().toString().trim();
        String contact=editTextContact.getText().toString().trim();
        String date=editTextDate.getText().toString().trim();
        String time=editTextTime.getText().toString().trim();


        if (name.isEmpty())
        {
            editTextName.setError("you should enter a name");
            editTextName.requestFocus();
            return;
        }
        if (contact.isEmpty())
        {
            editTextContact.setError("you should enter a contact no");
            editTextContact.requestFocus();
            return;
        }
        if (date.isEmpty())
        {
            editTextDate.setError("you should enter a visiting date");
            editTextDate.requestFocus();
            return;
        }
        if (time.isEmpty())
        {
            editTextTime.setError("you should enter current time");
            editTextTime.requestFocus();
            return;
        }

        //code here save data in real time database
        String id = databaseUser.push().getKey();

        User user=new User(id,name,contact,date,time);

        databaseUser.child(id).setValue(user);
        Toast.makeText(getActivity(), "User Atendence added", Toast.LENGTH_SHORT).show();
        DashboardFragment dashboardFragment=new DashboardFragment();
        addDasboardFragment(dashboardFragment);
    }

    private void addDasboardFragment(DashboardFragment dashboardFragment) {

        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment_container,dashboardFragment);
        ft.commit();
    }
}
