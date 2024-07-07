package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity implements MainActivity1 {
    public static final String FILENAME = "credentials";
    Fragment loginFrag, signupFrag;
    FragmentManager manager, manager2;
    TextInputEditText etUsernameS, etPasswordS, etCPasswordS;
    Button btnSignup, btnCancelS;
    View loginFragView, signupFragView, v;
    TextInputEditText etUsernameL, etPasswordL;
    Button btnLogin, btnCancelL;
    TextView tvLogin, tvSignup;
    LinearLayout portrait, landscape;
    Fragment listFrag, contentFrag;
    TextView tvContent;
    ImageView ivCall, ivLocation, ivLink;
    Tasks tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction()
                        .hide(loginFrag)
                        .show(signupFrag)
                        .commit();
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction()
                        .show(loginFrag)
                        .hide(signupFrag)
                        .commit();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsernameS.getText().toString().trim();
                String password = etPasswordS.getText().toString();
                String cPassword = etCPasswordS.getText().toString();

                if(username.isEmpty() || password.isEmpty() || cPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Something is missing", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password.equals(cPassword))
                    {
                        SharedPreferences sPref = getSharedPreferences(FILENAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sPref.edit();

                        editor.putString("key_username", username);
                        editor.putString("key_password", password);

                        editor.apply();

                        manager.beginTransaction()
                                .show(loginFrag)
                                .hide(signupFrag)
                                .commit();

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Password mis-matched", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnCancelS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsernameL.getText().toString().trim();
                String password = etPasswordL.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Something is missing", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SharedPreferences sPref = getSharedPreferences(FILENAME, MODE_PRIVATE);
                    String fUsername = sPref.getString("key_username", "");
                    String fPassword = sPref.getString("key_password", "");

                    if(fUsername.equals(username) && fPassword.equals(password))
                    {
                        SharedPreferences.Editor editor = sPref.edit();
                        editor.putBoolean("isLogin", true);
                        editor.apply();



                        startActivity(new Intent(MainActivity.this, Dashboard.class));
                        finish();
                    }
                }
            }
        });

        if(portrait != null)
        {
            manager.beginTransaction()
                    .show(listFrag)
                    .hide(contentFrag)
                    .commit();

        }
        else
        {
            manager.beginTransaction()
                    .show(listFrag)
                    .show(contentFrag)
                    .commit();
        }
    }
    private void init()
    {
        manager = getSupportFragmentManager();
        loginFrag = manager.findFragmentById(R.id.loginFrag);
        signupFrag = manager.findFragmentById(R.id.signupFrag);
        loginFragView = loginFrag.getView();
        signupFragView = signupFrag.getView();

        assert signupFragView != null;
        tvLogin = signupFragView.findViewById(R.id.tvLogin);

        tvSignup = loginFragView.findViewById(R.id.tvSignup);
        etUsernameS = signupFragView.findViewById(R.id.etUsername);
        etPasswordS = signupFragView.findViewById(R.id.etPassword);
        etCPasswordS = signupFragView.findViewById(R.id.etCPassword);
        btnSignup = signupFragView.findViewById(R.id.btnSignup);
        btnCancelS = signupFragView.findViewById(R.id.btnCancel);


        etUsernameL = loginFragView.findViewById(R.id.etUsername);
        etPasswordL = loginFragView.findViewById(R.id.etPassword);
        btnLogin = loginFragView.findViewById(R.id.btnLogin);
        btnCancelL = loginFragView.findViewById(R.id.btnCancel);

        manager.beginTransaction()
                .hide(signupFrag)
                .commit();

        portrait = findViewById(R.id.portrait);
        landscape = findViewById(R.id.landscape);
        manager2 = getSupportFragmentManager();


        listFrag = manager2.findFragmentById(R.id.Fraglist);
        contentFrag = manager2.findFragmentById(R.id.FragDetail);
        assert contentFrag != null;
        v = contentFrag.requireView();

        tvContent = v.findViewById(R.id.tvContent_CF);
        ivCall = v.findViewById(R.id.ivCall);
        ivLink = v.findViewById(R.id.ivLink);
        ivLocation = v.findViewById(R.id.ivLocation);
        hideIcons();
    }
    private void hideIcons()
    {
        ivCall.setVisibility(View.GONE);
        ivLink.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);
    }
    private void showIcons()
    {
        ivCall.setVisibility(View.VISIBLE);
        ivLink.setVisibility(View.VISIBLE);
        ivLocation.setVisibility(View.VISIBLE);
    }

    @Override
    public void onNameClick(int index) {
        tasks = MyApplication.tasks.get(index);
        tvContent.setText(tasks.getTitle());
        showIcons();
        if(portrait!=null)
        {
            manager2.beginTransaction()
                    .show(contentFrag)
                    .hide(listFrag)
                    .addToBackStack(null)
                    .commit();
        }
    }
}