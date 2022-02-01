package com.example.wdcinema_homeversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity {

    EditText email,password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        email = (EditText) findViewById(R.id.email_login);
        password = (EditText) findViewById(R.id.pass_login);
        btnlogin = findViewById(R.id.btnlogin_login);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    public void RegisterActivity(View view) {
        Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
        startActivity(intent);
        finish();
    }

    public void Login(View v) {

        EditText email = (EditText) findViewById(R.id.email_login);
        String email_str = email.getText().toString();
        EditText password = (EditText) findViewById(R.id.pass_login);
        String pass_str = password.getText().toString();

        if (TextUtils.isEmpty(email_str)) {
            email.setError("Поле не должно быть пустым");
            return;
        } else if (TextUtils.isEmpty(pass_str)) {
            password.setError("Поле не должно быть пустым");
            return;
        }
    }

    public void loginUser(){
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setEmail(email.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        Call<LoginResponse>loginResponseCall = ApiClient.getLogin().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(LoginScreen.this,"Success!", Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    String message = "Wrong";
                    Toast.makeText(LoginScreen.this,response.errorBody().toString(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}