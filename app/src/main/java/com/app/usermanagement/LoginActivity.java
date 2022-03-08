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

import com.app.usermanagement.ModelResponse.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email , password ;
    Button login ;
    TextView registerLink ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        registerLink = findViewById(R.id.registerLink);

        registerLink.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                userLogin();
//                startActivity(new Intent(LoginActivity.this , HomeActivity.class));
                break;
            case R.id.registerLink:
                switchOnRegister();
                break;
        }

    }

    private void userLogin() {
        String userEmail  = email.getText().toString();
        String userPassword  = password.getText().toString();


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

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().login(userEmail , userPassword);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(response.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void switchOnRegister() {

        Intent i = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(i);
    }
}