package com.app.usermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                startActivity(new Intent(LoginActivity.this , HomeActivity.class));
                break;
            case R.id.registerLink:
                switchOnRegister();
        }

    }

    private void switchOnRegister() {

        Intent i = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(i);
    }
}