package com.dineshpro.dailyatendence;

import android.os.Bundle;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class RegisterFragment extends Fragment {
    private FirebaseAuth mAuth;


    EditText editTextEmailAddress,editTextPasswd;
    Button buttonsignUpUser,buttonSignUpBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.register_layout,container,false);

        mAuth = FirebaseAuth.getInstance();
        editTextEmailAddress=(EditText)view.findViewById(R.id.editTextEmailAddress);

        editTextPasswd=(EditText)view.findViewById(R.id.editTextPasswd);

        buttonsignUpUser=(Button)view.findViewById(R.id.buttonSignUpUser);
        buttonSignUpBack=(Button)view.findViewById(R.id.buttonSignUpback);



        buttonsignUpUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpUser();
            }
        });

buttonSignUpBack.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        LoginFragment loginFragment=new LoginFragment();
        addLogingFragment(loginFragment);
    }
});

        return view;
    }

    private void addLogingFragment(LoginFragment loginFragment) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,loginFragment);
        ft.commit();
    }


    private void signUpUser() {

        String email=editTextEmailAddress.getText().toString().trim();
        String password=editTextPasswd.getText().toString().trim();
        if (email.isEmpty())
        {
            editTextEmailAddress.setError("you should enter a email");
            editTextEmailAddress.requestFocus();
            return;
        }
        if (password.isEmpty())
        {
            editTextPasswd.setError("you should enter a password");
            editTextPasswd.requestFocus();
            return;
        }

        //return true

       mAuth.createUserWithEmailAndPassword(email,password)
               .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {

                            Toast.makeText(getActivity(), "user created successfully", Toast.LENGTH_SHORT).show();
                            DashboardFragment dashboardFragment=new DashboardFragment();
                            addDashboardFragmnet(dashboardFragment);
                        }
                        else
                            {
                                Toast.makeText(getActivity(), "user not created", Toast.LENGTH_SHORT).show();
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            }
                   }
               });
    }

    private void addDashboardFragmnet(DashboardFragment dashboardFragment) {

        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.fragment_container,dashboardFragment);
        ft.commit();
    }
}
