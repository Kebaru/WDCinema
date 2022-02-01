package com.example.wdcinema_homeversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.validator.routines.EmailValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterScreen extends AppCompatActivity {

    Button btnregister;
    EditText fname, lname, email, password,passcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        fname = (EditText) findViewById(R.id.firstname_register);
        lname = (EditText) findViewById(R.id.lastname_register);
        email = (EditText) findViewById(R.id.email_register);
        password = (EditText) findViewById(R.id.pass_register);
        passcheck = (EditText) findViewById(R.id.passconfirmation_register);
        btnregister=findViewById(R.id.btnregister_register);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fname_str = fname.getText().toString();
                String lname_str = lname.getText().toString();
                String email_str = email.getText().toString();
                String pass_str = password.getText().toString();
                String passcheck_str = passcheck.getText().toString();

                if (TextUtils.isEmpty(fname_str)) {
                    fname.setError("Поле не должно быть пустым");
                    return;
                } else if (TextUtils.isEmpty(lname_str)) {
                    lname.setError("Поле не должно быть пустым");
                    return;
                } else if (TextUtils.isEmpty(email_str)) {
                    email.setError("Поле не должно быть пустым");
                    return;
                } else if (TextUtils.isEmpty(pass_str)) {
                    password.setError("Поле не должно быть пустым");
                    return;
                } else if (TextUtils.isEmpty(passcheck_str)) {
                    passcheck.setError("Поле не должно быть пустым");
                    return;
                } else if (!passcheck_str.equals(pass_str)) {
                    passcheck.setError("Пароли не совпадают");
                    return;
                } else{
                    registerUser();
                }
            }
        });
    };


    public void Return(View view) {
        Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
        startActivity(intent);
        finish();
    }

    public void registerUser(){
        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setEmail(email.getText().toString());
        registerRequest.setFirstName(fname.getText().toString());
        registerRequest.setLastName(lname.getText().toString());
        registerRequest.setPassword(password.getText().toString());

        Call<RegisterResponse> registerResponseCall = ApiClient.getRegister().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    String message = "OK";
                    Toast.makeText(RegisterScreen.this,message,Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    String message ="NOT OK";
                    Toast.makeText(RegisterScreen.this,response.errorBody().toString(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    String message = "Registered";
                    Toast.makeText(RegisterScreen.this,message,Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterScreen.this,LoginScreen.class));
            }
        });
    }

        /*else if(isValidEmail(email_str)==true){
            Toast.makeText(this,"ura",Toast.LENGTH_SHORT).show();
        }*/

        /*String emailvalidator = "myName@example.com";
        boolean valid = EmailValidator.getInstance().isValid(emailvalidator);*/


    public final boolean isValidEmail(CharSequence target) {
        String email_str = email.getText().toString();
        if (TextUtils.isEmpty(email_str)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email_str).matches();
        }
    }
}
