package com.example.rif.crickbroad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Button btn_new,btn_prv,btn_teams,ButtonNewMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_new = (Button)findViewById(R.id.button_new_m);
        btn_prv = (Button)findViewById(R.id.button_p_m);
        btn_teams = (Button)findViewById(R.id.button_teams);
        ButtonNewMatch = (Button)findViewById(R.id.button_new_m);

        actionListenerFunction();


    }

    public void actionListenerFunction(){
        btn_teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teams = new Intent(getApplicationContext(),TeamActivity.class);
                startActivity(teams);
            }
        });

        ButtonNewMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NewMatchActivity = new Intent(getApplicationContext(),newMatchActivity.class);
                startActivity(NewMatchActivity);
            }
        });
        btn_prv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Feature Not Available", Toast.LENGTH_LONG).show();
            }
        });
    }

}
