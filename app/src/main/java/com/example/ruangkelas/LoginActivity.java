package com.example.ruangkelas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class LoginActivity extends Activity {

    /*
    List<User> listUser;
    private Button login;
    private EditText username, password;
    private int counter = 5;
    private TextView Info;
    */

    private EditText mUsername,mUserpasswd;
    private Button mLogin;
    private TextView mRegister;
    private String Name,Password;
    private SharedPreferences mSharedPreferences;
    public static final String PREFERENCE= "preference";
    public static final String PREF_NAME = "register_usernameText";
    public static final String PREF_PASSWD = "register_passwordText";
    private long backPressedTime;
    private Toast backToast;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        mUsername = (EditText)findViewById(R.id.loginusername);
        mUserpasswd = (EditText)findViewById(R.id.loginpass);
        mLogin = (Button)findViewById(R.id.btnlogin);
        mRegister = (TextView)findViewById(R.id.daftar);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validUserData()){
                    if(mSharedPreferences.contains(PREF_NAME) && mSharedPreferences.contains(PREF_PASSWD)){
                        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                        mEditor.apply();
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"Username atau Password Salah",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        /*
        login=(Button)findViewById(R.id.btnlogin);
        username=(EditText)findViewById(R.id.loginusername);
        password=(EditText)findViewById(R.id.loginpass);
        Info = (TextView)findViewById(R.id.tvInfo);

        Info.setText("No of attempts remaining: 5");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });
        */
    }

    private boolean validUserData() {
        Name = mUsername.getText().toString().trim();
        Password = mUserpasswd.getText().toString().trim();
        return !(Name.isEmpty() || Password.isEmpty());
    }

    /*
    public void Daftar(View view) {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void validate(String userName, String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }else{
            counter--;

            Info.setText("No of attempts remaining: " + String.valueOf(counter));

            if(counter == 0){
                login.setEnabled(false);
            }
        }
    }
    */

    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}