package com.cgi.formation.monstaffing.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cgi.formation.monstaffing.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextLogin;
    EditText editTextPassword;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLogin = findViewById(R.id.edittext_login);
        editTextPassword = findViewById(R.id.edittext_password);
        buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String login = editTextLogin.getText().toString();
        String password = editTextPassword.getText().toString();

        // TODO: appel webservice
    }
}
