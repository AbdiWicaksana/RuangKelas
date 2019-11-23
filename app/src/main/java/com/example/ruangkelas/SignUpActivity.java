package com.example.ruangkelas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends Activity {

    List<User> listUser;
    /*
    EditText UsernameRegis;
    EditText EmailRegis;
    EditText PassRegis;
    EditText ConfPassRegis;
    Button register;
    */
    private EditText mName,mEmail,mPasswd,mConfPass;
    private Button mRegisterBtn;
    private String Name,Password,Email;
    public static final String PREFERENCE= "preference";
    public static final String PREF_NAME = "register_usernameText";
    public static final String PREF_PASSWD = "register_passwordText";
    public static final String PREF_EMAIL = "register_emailText";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        mName = (EditText)findViewById(R.id.RegistUser);
        mEmail = (EditText)findViewById(R.id.RegistEmail);
        mPasswd = (EditText)findViewById(R.id.RegistPass);
        mConfPass = (EditText)findViewById(R.id.RegistConfPass);
        mRegisterBtn = (Button)findViewById(R.id.RegistButton);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validUserData()){
                    SharedPreferences mSharedPreference = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor mEditor = mSharedPreference.edit();
                    mEditor.putString(PREF_NAME,Name);
                    mEditor.putString(PREF_PASSWD,Password);
                    mEditor.putString(PREF_EMAIL,Email);
                    mEditor.apply();
                    finish();
                }
            }
        });

        /*
        UsernameRegis = findViewById(R.id.RegistUser);
        EmailRegis = findViewById(R.id.RegistEmail);
        PassRegis = findViewById(R.id.RegistPass);
        ConfPassRegis = findViewById(R.id.RegistConfPass);
        register = findViewById(R.id.RegistButton);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });
        */
    }

    private boolean validUserData() {
        Name = mName.getText().toString().trim();
        Password = mPasswd.getText().toString().trim();
        return !(Name.isEmpty() || Password.isEmpty());
    }

    /*
    public void addUserToList (User user){
        listUser.add(user);

    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(UsernameRegis)) {
            Toast t = Toast.makeText(this, "You must enter Username to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmail(EmailRegis) == false) {
            EmailRegis.setError("Enter valid email!");
        }

        if (isEmpty(PassRegis)) {
            Toast t = Toast.makeText(this, "You must enter Password to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(ConfPassRegis)) {
            ConfPassRegis.setError("Confirm Password is required!");
        }
        else {
            Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
    */
}