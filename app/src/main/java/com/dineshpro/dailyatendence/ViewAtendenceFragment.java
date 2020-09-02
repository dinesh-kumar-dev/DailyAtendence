package com.dineshpro.dailyatendence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dineshpro.dailyatendence.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAtendenceFragment extends Fragment {
    DatabaseReference databaseReferenceUser;
    RecyclerView recyclerViewUser;

    Button buttonViewAllUserBack;

List<User> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.viewall_atendence_layout,container,false);
        recyclerViewUser=(RecyclerView)view.findViewById(R.id.recyclerview);
        buttonViewAllUserBack=(Button)view.findViewById(R.id.buttonAlluserViewBack);

        userList =new ArrayList<>();
        databaseReferenceUser= FirebaseDatabase.getInstance().getReference("user");
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerViewUser.setLayoutManager(mLayoutManager);








        //back button coading
        buttonViewAllUserBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashboardFragment dashboardFragment=new DashboardFragment();
                addDasbordFragment(dashboardFragment);
            }
        });
        return view;
    }

    private void addDasbordFragment(DashboardFragment dashboardFragment) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment_container,dashboardFragment);
        ft.commit();
    }

    @Override
    public void onStart() {
            databaseReferenceUser.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    userList.clear();

                    for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                      User user=postSnapshot.getValue(User.class);
                     userList.add(user);

                    }
                    UserAdapter userAdapter=new UserAdapter(getContext(),userList);
                    recyclerViewUser.setAdapter(userAdapter);
                    userAdapter.notifyDataSetChanged();

                    recyclerViewUser.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(getContext(),"RecyclerView not connected",Toast.LENGTH_LONG).show();

                }
            });
        super.onStart();
    }
}
