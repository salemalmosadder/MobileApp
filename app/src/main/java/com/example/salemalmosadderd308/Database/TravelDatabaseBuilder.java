package com.example.salemalmosadderd308.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.salemalmosadderd308.dao.ExcursionDao;
import com.example.salemalmosadderd308.dao.VacationDao;
import com.example.salemalmosadderd308.entities.Excursion;
import com.example.salemalmosadderd308.entities.Vacation;

@Database(entities = {Vacation.class, Excursion.class}, version=2, exportSchema = false)
public abstract class TravelDatabaseBuilder extends RoomDatabase {
    public abstract VacationDao VacationDao();
    public abstract ExcursionDao ExcursionDao();
    //instance
    private static volatile TravelDatabaseBuilder INSTANCE;

    static TravelDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE==null) {
            synchronized (TravelDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TravelDatabaseBuilder.class, "MyTravelDatabase.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
