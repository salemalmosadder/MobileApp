package com.example.salemalmosadderd308.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// Created Entity Class Excursion for B1.

@Entity(tableName = "Excursions")
public class Excursion {
    @PrimaryKey(autoGenerate = true)
    private int excursionId;
    private int vacationId;
    private String excursionTitle;
    private String excursionStartDate;

    @Ignore
    public Excursion(){
        //no args
    }

    @Override
    public String toString() {
        return "Excursion{" +
                "excursionId=" + excursionId +
                ", excursionTitle'" + excursionTitle + '\'' +
                ", excursionStartDate='" + excursionStartDate + '\'' +
                '}';
    }

    public Excursion(int excursionId, int vacationId, String excursionTitle, String excursionStartDate){
        this.excursionId = excursionId;
        this.vacationId = vacationId;
        this.excursionTitle = excursionTitle;
        this.excursionStartDate = excursionStartDate;
    }

    public int getVacationId() {
        return vacationId;
    }

    public void setVacationId(int vacationId){
        this.vacationId = vacationId;
    }

    public String getExcursionTitle(){
        return excursionTitle;
    }

    public void setExcursionTitle(String excursionTitle) {
        this.excursionTitle = excursionTitle;
    }

    public String getExcursionStartDate() {
        return excursionStartDate;
    }

    public void setExcursionStartDate(String excursionStartDate) {
        this.excursionStartDate = excursionStartDate;
    }

    public int getExcursionId() {
        return excursionId;
    }

    public void setExcursionId(int excursionId) {
        this.excursionId = excursionId;
    }

}
