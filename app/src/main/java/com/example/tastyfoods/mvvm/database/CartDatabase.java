package com.example.tastyfoods.mvvm.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tastyfoods.mvvm.model.CartDetail;
@Database(entities = {CartDetail.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "cart.db";
    private  static CartDatabase Instance;
    public static synchronized CartDatabase getInstance(Context context){
        if(Instance == null){
            Instance = Room.databaseBuilder(context.getApplicationContext(),CartDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return Instance;
    }

    public abstract CartDAO cartDAO();
}