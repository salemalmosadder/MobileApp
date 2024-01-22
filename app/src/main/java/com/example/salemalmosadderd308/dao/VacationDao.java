package com.example.salemalmosadderd308.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.salemalmosadderd308.entities.Vacation;

import java.util.List;

// Created Vacation dao for B1
@Dao
public interface VacationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)            //B3 Connected The database using the DAO
    void insert(Vacation vacation);                            //B3 Implemented CRUD

    @Update
    void update(Vacation vacation);

    @Delete
    void delete(Vacation vacation);

    @Query("SELECT * FROM VACATIONS ORDER BY VACATIONID ASC")
    List<Vacation> getAllVacations();

    @Query("SELECT * FROM VACATIONS WHERE VACATIONID = VACATIONID ORDER BY VACATIONID ASC")
    List<Vacation> getAllVacationsById();

}
