package com.example.rif.crickbroad;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class TeamActivity extends AppCompatActivity {

    Button btn_add_new,btn_tn_ok,btn_tn_cancel;
    String Team_Name;
    CricBoardDataSource dataSource;
    ListView listView;
    List<Team> teams;
    ArrayAdapter<Team> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        btn_add_new = (Button)findViewById(R.id.button_add_new);

        dataSource = new CricBoardDataSource(this);
        dataSource.open();

        teams = dataSource.getAllTeam();

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<Team>(this, android.R.layout.simple_list_item_1, teams);
        listView.setAdapter(adapter);


        ALF();
    }

    public void ALF(){
        btn_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog team_name = new Dialog(TeamActivity.this);
                team_name.setContentView(R.layout.inputname);
                team_name.show();
                final EditText editText = (EditText) team_name.findViewById(R.id.editText);
                btn_tn_ok = (Button)team_name.findViewById(R.id.button_tn_ok);
                btn_tn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Team_Name = editText.getText().toString();
                        Team team = new Team();
                        team.setName(Team_Name);
                        team = dataSource.insert(team);
                        Toast.makeText(TeamActivity.this, String.valueOf(team.getId()), Toast.LENGTH_SHORT).show();
                        team_name.dismiss();
                        Intent teams = new Intent(getApplicationContext(),TeamActivity.class);
                        startActivity(teams);
                    }
                });
                btn_tn_cancel = (Button) team_name.findViewById(R.id.button_tn_cancel);
                btn_tn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        team_name.dismiss();
                    }
                });
            }
        });

        // list view click listener

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Team team = adapter.getItem(position);
                Intent playerActivity = new Intent(getApplicationContext(),PlayerActivity.class);
                playerActivity.putExtra(TeamDatabaseOpenHelper.COLUMN_TEAM_ID, team.getId());
                startActivity(playerActivity);
                //Toast.makeText(TeamActivity.this, String.valueOf(team.getId()) + team.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
