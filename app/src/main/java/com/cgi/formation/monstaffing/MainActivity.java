package com.cgi.formation.monstaffing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cgi.formation.monstaffing.activities.ApplyOfferActivity;
import com.cgi.formation.monstaffing.activities.DisplayActivity;
import com.cgi.formation.monstaffing.activities.FiltreActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_applyToOffer:
                Intent intent = new Intent(this, ApplyOfferActivity.class);
                startActivity(intent);
                break;

            case R.id.button_filtre:
                Intent intent1 = new Intent(this, FiltreActivity.class);
                startActivity(intent1);
                break;

            case R.id.button_listMission:
                Intent intent2 = new Intent(this, DisplayActivity.class);
                startActivity(intent2);
                break;

            case R.id.button_login:
                Intent intent3 = new Intent(this, LoginActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
