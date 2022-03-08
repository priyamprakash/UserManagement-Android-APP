package com.app.usermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.usermanagement.ModelResponse.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener   {
    TextView loginLink;
    EditText name , email , password;
    Button register ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginLink = findViewById(R.id.loginLink);
        name  = findViewById(R.id.et_name);
        email  = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        register = findViewById(R.id.btn_register);

        loginLink.setOnClickListener(this);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                registerUser();
                break;
            case R.id.loginLink:
                switchOnLogin();
                break;
        }
    }

    private void registerUser() {
        String userName  = name.getText().toString();
        String userEmail  = email.getText().toString();
        String userPassword  = password.getText().toString();

        if(userName.isEmpty()){
            name.requestFocus();
            name.setError("Please enter your name");
            return;

        }
        if(userEmail.isEmpty()){
            email.requestFocus();
            email.setError("Please enter your email");
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.requestFocus();
            email.setError("Please enter correct email");
            return;
        }


        if(userPassword.isEmpty()){
            password.requestFocus();
            password.setError("Please enter your password");
            return;
        }

        if(userPassword.length()<6){
            password.requestFocus();
            email.setError("Minimum password length should be 6");
            return;
        }


        Call<RegisterResponse> call = RetrofitClient.getInstance().getApi().register(userName , userEmail , userPassword);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                RegisterResponse registerResponse = response.body();
                if(response.isSuccessful()){

                    Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    Toast.makeText(MainActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });











    }

    private void switchOnLogin() {
        Intent i = new Intent(MainActivity.this , LoginActivity.class);
        startActivity(i);
    }


}
