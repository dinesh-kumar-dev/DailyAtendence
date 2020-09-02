package com.dineshpro.dailyatendence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler h=new Handler();

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginFragment loginFragment=new LoginFragment();
                addLoginFragment(loginFragment);
            }
        },2000);
    }

    private void addLoginFragment(LoginFragment loginFragment) {
        FragmentManager fm=getSupportFragmentManager();

        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.fragment_container,loginFragment);
        ft.commit();
    }
}