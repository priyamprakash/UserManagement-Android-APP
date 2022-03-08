package com.app.usermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                Toast.makeText(this, "register", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loginLink:
                switchOnLogin();
                break;
        }
    }

    private void switchOnLogin() {
        Intent i = new Intent(MainActivity.this , LoginActivity.class);
        startActivity(i);
    }


}
