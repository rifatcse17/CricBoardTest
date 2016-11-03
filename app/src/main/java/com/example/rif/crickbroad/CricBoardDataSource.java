package com.example.rif.crickbroad;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CricBoardDataSource {
    private TeamDatabaseOpenHelper dbHelper;
    private SQLiteDatabase database;

    public CricBoardDataSource(Context context) {
        dbHelper = new TeamDatabaseOpenHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Team insert(Team team) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_TEAM_NAME,
                team.getName());

        long insertId = database.insert(
                dbHelper.TEAM_TABLE, null, values);
        team.setId(insertId);
        return team;
    }

    public Player insertPlayer(Player player){
        ContentValues values = new ContentValues();
        values.put(TeamDatabaseOpenHelper.COLUMN_PLAYER_NAME,
                player.getName());
        values.put(TeamDatabaseOpenHelper.COLUMN_FOREIGN_TEAM_ID,player.getFid());

        long playerInsertID = database.insert(
                TeamDatabaseOpenHelper.PLAYER_TABLE, null, values);
        player.setId(playerInsertID);
        return player;
    }

    public List<Team> getAllTeam() {
        List<Team> teams = new ArrayList<>();

        Cursor cursor = database.query(TeamDatabaseOpenHelper.TEAM_TABLE,
                new String[]{TeamDatabaseOpenHelper.COLUMN_TEAM_ID, TeamDatabaseOpenHelper.COLUMN_TEAM_NAME}, null, null,
                null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Team team = new Team();
                team.setName(cursor.getString(cursor.getColumnIndex(TeamDatabaseOpenHelper.COLUMN_TEAM_NAME)));
                team.setId(cursor.getLong(cursor.getColumnIndex(TeamDatabaseOpenHelper.COLUMN_TEAM_ID)));
                teams.add(team);
            }
        }
        return teams;
    }
    //"sku_table", columns, "owner=\'"+owner+"\'"
    public List<Player>getPlayer(long teamID){
        List<Player> players = new ArrayList<>();

        Cursor cursor = database.query(TeamDatabaseOpenHelper.PLAYER_TABLE,
                new String[] {TeamDatabaseOpenHelper.COLUMN_PLAYER_ID,TeamDatabaseOpenHelper.COLUMN_PLAYER_NAME},null,null,
                null,null,null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Player player = new Player();
                player.setName(cursor.getString(cursor.getColumnIndex(TeamDatabaseOpenHelper.COLUMN_PLAYER_NAME)));
                player.setId(cursor.getLong(cursor.getColumnIndex(TeamDatabaseOpenHelper.COLUMN_PLAYER_ID)));

                players.add(player);
            }
        }
        return players;
    }

}
