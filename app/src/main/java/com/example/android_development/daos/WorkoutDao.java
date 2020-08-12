package com.example.android_development.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android_development.entities.Workout;
import com.example.android_development.entities.WorkoutData;

import java.util.List;

@Dao
public interface WorkoutDao {
    @Insert
    void insertWorkout(Workout workout);

    @Query("SELECT * FROM workouts")
    List<Workout> getAllWorkouts();

    @Query("SELECT DISTINCT dayOfWeek, COUNT(dayOfWeek) ,SUM(timeSpend) FROM workouts WHERE dayOfWeek LIKE :day")
    WorkoutData getWorkoutDataByDay(String day);

    @Query("UPDATE workouts SET timeSpend = :ts WHERE dayOfWeek LIKE :day")
    void updateWorkoutByDay(String day, int ts);
}
