package com.example.android_development;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.android_development.entities.Workout;
import com.example.android_development.entities.WorkoutData;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EntityTest {
    @Test
    public void workoutNotNull(){
        Workout w = new Workout();

        w.dayOfWeek = "Monday";
        w.timeSpend = 45201;

        Assert.assertNotNull(w);
    }
    @Test
    public void workoutDataNotNull(){
        WorkoutData wd = new WorkoutData();

        wd.dayOfWeek = "Monday";
        wd.timesCompleted = 21;
        wd.totalTimeSpend = 495201;

        Assert.assertNotNull(wd);
    }
}
