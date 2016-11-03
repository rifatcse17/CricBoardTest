package com.example.rif.crickbroad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class newMatchActivity extends AppCompatActivity {

    Button button1,button2,button3,button4,button5,button6,buttonWide,buttonNoBall,buttonOut,button0,buttonEndInnings;
    TextView textViewRun,textViewOvr,textViewwkt;
    int run;
    int bowl;
    int wkt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_match);
        run = 0;
        bowl = 0;
        wkt = 0;
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button6 = (Button)findViewById(R.id.button6);
        buttonOut = (Button)findViewById(R.id.buttonOut);
        buttonWide = (Button)findViewById(R.id.button_wide);
        buttonNoBall = (Button)findViewById(R.id.button_noball);
        buttonEndInnings = (Button)findViewById(R.id.buttonEi);

        textViewRun = (TextView)findViewById(R.id.textViewR);
        textViewOvr = (TextView)findViewById(R.id.textViewo);
        textViewwkt = (TextView)findViewById(R.id.textViewwkt);

        ActionListenerFunction();
        ScoringFunction();

    }

    private void ScoringFunction() {
        textViewRun.setText(String.valueOf(run));
        int overShow = bowl/6;
        textViewOvr.setText(String.valueOf(overShow));
        textViewwkt.setText(String.valueOf(wkt));
    }

    private void ActionListenerFunction() {
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bowl++;
                ScoringFunction();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run++;
                bowl++;
                ScoringFunction();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run += 2;
                bowl++;
                ScoringFunction();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run += 3;
                bowl++;
                ScoringFunction();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run += 4;
                bowl++;
                ScoringFunction();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run += 6;
                bowl++;
                ScoringFunction();
            }
        });
        buttonNoBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run++;
                ScoringFunction();
            }
        });
        buttonWide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run++;
                ScoringFunction();
            }
        });
        buttonOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wkt++;
                ScoringFunction();
            }
        });
        buttonEndInnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newInnings = new Intent(getApplicationContext(),NewInningsActivity.class);
                newInnings.putExtra("RUN", run);
                newInnings.putExtra("OVR", bowl);
                newInnings.putExtra("WKT", wkt);
                startActivity(newInnings);
            }
        });
    }
}
