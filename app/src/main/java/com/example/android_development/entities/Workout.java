package com.example.android_development.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workouts")
public class Workout {
    public Workout () {}

    @PrimaryKey(autoGenerate = true)
    public int workoutId;

    public String dayOfWeek;

    public int timeSpend;
}
