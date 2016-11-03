package com.example.rif.crickbroad;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    TextView textView;
    Button buttonAddPlayers;
    Button btn_add_new, btn_pn_ok, btn_pn_cancel;
    String Player_Name;
    long teamID;
    CricBoardDataSource dataSource;
    ListView listView;
    List<Player> players;
    ArrayAdapter<Player> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        textView = (TextView)findViewById(R.id.textView_id);
        buttonAddPlayers = (Button)findViewById(R.id.button_add_p);
        dataSource = new CricBoardDataSource(this);
        dataSource.open();
        teamID = getIntent().getLongExtra(TeamDatabaseOpenHelper.COLUMN_TEAM_ID,-1);
        String Tid = Long.toString(teamID);
        textView.setText(Tid);
        //players = dataSource.getPlayer();
        //listView = (ListView) findViewById(R.id.listViewPlayers);
        //adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1,players);
        //listView.setAdapter(adapter);
        actionListenerFunction();
        ListViewFunction(teamID);
    }

    private void ListViewFunction(long teamID) {
        players = dataSource.getPlayer(teamID);
        listView = (ListView) findViewById(R.id.listViewPlayers);
        adapter = new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1,players);
        listView.setAdapter(adapter);
    }

    private void actionListenerFunction() {
        buttonAddPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog player_name = new Dialog(PlayerActivity.this);
                player_name.setContentView(R.layout.inputname);
                player_name.show();
                final EditText editText = (EditText) player_name.findViewById(R.id.editText);
                btn_pn_ok = (Button)player_name.findViewById(R.id.button_tn_ok);
                btn_pn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Player_Name = editText.getText().toString();
                        Player player = new Player();
                        player.setName(Player_Name);
                        player.setFid(teamID);
                        player = dataSource.insertPlayer(player);
                        //Toast.makeText(getApplicationContext(),String.valueOf(player.getFid()),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),Player_Name,Toast.LENGTH_LONG).show();
                        player_name.dismiss();
                    }
                });
                btn_pn_cancel = (Button) player_name.findViewById(R.id.button_tn_cancel);
                btn_pn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player_name.dismiss();
                    }
                });
            }
        });
    }
}
