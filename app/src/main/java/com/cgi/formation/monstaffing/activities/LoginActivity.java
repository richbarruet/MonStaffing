package com.cgi.formation.monstaffing.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cgi.formation.monstaffing.R;
import com.cgi.formation.monstaffing.managers.CacheManager;
import com.cgi.formation.monstaffing.managers.WebServiceManager;
import com.cgi.formation.monstaffing.models.Mission;
import com.cgi.formation.monstaffing.models.ResponseAuthent;
import com.cgi.formation.monstaffing.views.adapters.MissionAdapter;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private WebServiceManager webServiceManagerInstance = WebServiceManager.getInstance();

    private CacheManager cacheManagerInstance = CacheManager.getInstance();

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

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryLogin();
            }
        });
    }

    private void tryLogin(){
        AsyncTask asyncTask = new AsyncTask<Object,Void,ResponseAuthent>(){
            @Override
            protected ResponseAuthent doInBackground(Object... objects) {
                return webServiceManagerInstance.login((String)objects[0],(String)objects[1]);
            }

            @Override
            protected void onPostExecute(ResponseAuthent responseAuthent){
                setToken(responseAuthent);
            }
        };
        asyncTask.execute(editTextLogin.getText().toString(),editTextPassword.getText().toString());
    }

    private void setToken(ResponseAuthent responseAuthent){
        cacheManagerInstance.setResponseAuthent(responseAuthent);
        Intent intent = new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }
}
