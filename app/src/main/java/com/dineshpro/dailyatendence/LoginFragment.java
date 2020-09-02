package com.dineshpro.dailyatendence;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    EditText editTextEmailId,editTextPassword;
    Button buttonLogin,buttonzSignUp;
    TextView textViewforgetPasword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login_layout,container,false);
        mAuth=FirebaseAuth.getInstance();
        editTextEmailId=(EditText)view.findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)view.findViewById(R.id.editTextPassword);
        buttonLogin=(Button) view.findViewById(R.id.buttonLogin);
        buttonzSignUp=(Button)view.findViewById(R.id.buttonSignUp);
        textViewforgetPasword=(TextView)view.findViewById(R.id.textViewForgetPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

                                               LoginUser();
                                           }
                                       }
        );
        buttonzSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        textViewforgetPasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Hi user :) this is not active(::)", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
//signup code here
    private void signUp() {
        RegisterFragment registerFragment=new RegisterFragment();
        addSignupFragment(registerFragment);

    }

    private void addSignupFragment(RegisterFragment registerFragment) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment_container,registerFragment);
        ft.commit();
    }

    //login code here
    private void LoginUser() {

        String email=editTextEmailId.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if (email.isEmpty())
        {
            editTextEmailId.setError("you shoul enter a email");
            editTextEmailId.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("you should enter a pasword ");
            editTextPassword.requestFocus();
            return;
        }

       mAuth.signInWithEmailAndPassword(email,password)
               .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getActivity(), "Hi user:)", Toast.LENGTH_SHORT).show();
                            DashboardFragment dashboardFragment=new DashboardFragment();
                            addDashboardFragment(dashboardFragment);
                        }
                        else
                            {
                                Toast.makeText(getActivity(), "So sorry user ):", Toast.LENGTH_SHORT).show();
                            }
                   }
               });
}

    private void addDashboardFragment(DashboardFragment dashboardFragment) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment_container,dashboardFragment);
        ft.commit();

    }
    }
