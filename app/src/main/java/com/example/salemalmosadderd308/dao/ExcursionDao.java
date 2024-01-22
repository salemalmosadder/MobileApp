package com.example.salemalmosadderd308.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.salemalmosadderd308.entities.Excursion;

import java.util.List;

// Created Excursion dao for B1

@Dao
public interface ExcursionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Excursion excursion);

    @Update
    void update(Excursion excursion);

    @Delete
    void delete(Excursion excursion);

    @Query("SELECT* FROM EXCURSIONS ORDER BY EXCURSIONTITLE ASC")
    List<Excursion> getAllExcursions();
}
