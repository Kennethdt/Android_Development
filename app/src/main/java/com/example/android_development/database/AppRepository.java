package com.example.android_development.database;

import android.app.Application;
import android.os.AsyncTask;

import com.example.android_development.daos.WorkoutDao;
import com.example.android_development.entities.Workout;
import com.example.android_development.entities.WorkoutData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AppRepository {
    private WorkoutDao mWorkoutDao;

    public AppRepository(Application application){
        AppRoomDatabase db = AppRoomDatabase.getDatabase(application);
        mWorkoutDao = db.workoutDao();
    }

    public void insertWorkout(Workout workout) {
        new insertWorkoutAsync(this.mWorkoutDao).execute(workout);
    }

    public List<Workout> getAllWorkouts(){
        List<Workout> workoutList = null;

        try {
            workoutList = new getAllWorkoutsAsync(this.mWorkoutDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return workoutList;
    }

    public WorkoutData getWorkoutByDay(String day){
        WorkoutData workoutData = null;

        try {
            workoutData = new getWorkoutDataByDayAsync(this.mWorkoutDao).execute(day).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return workoutData;
    }

    void updateWorkoutByDay(String day, int ts){
        new updateWorkoutByDay(this.mWorkoutDao, ts).execute(day);
    }


    //Async Methods

    //insert Workout
    private static class insertWorkoutAsync extends AsyncTask<Workout, Void, Void> {
        private WorkoutDao workoutDao;

        insertWorkoutAsync(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected Void doInBackground(Workout... workouts) {
            this.workoutDao.insertWorkout(workouts[0]);
            return null;
        }
    }

    //get all Workouts
    private static class getAllWorkoutsAsync extends AsyncTask<Void, Void, List<Workout>> {
        private WorkoutDao workoutDao;

        getAllWorkoutsAsync(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected List<Workout> doInBackground(Void... voids) {
            return this.workoutDao.getAllWorkouts();
        }
    }

    //get WorkoutData by Day
    private static class getWorkoutDataByDayAsync extends AsyncTask<String, Void, WorkoutData> {
        private WorkoutDao workoutDao;

        getWorkoutDataByDayAsync(WorkoutDao workoutDao) {
            this.workoutDao = workoutDao;
        }

        @Override
        protected WorkoutData doInBackground(String... strings) {
            return this.workoutDao.getWorkoutDataByDay(strings[0]);
        }
    }

    //update Workout by day
    private static class updateWorkoutByDay extends AsyncTask<String, Void, Void> {
        private WorkoutDao workoutDao;
        private int ts;

        updateWorkoutByDay(WorkoutDao workoutDao, int ts) {
            this.workoutDao = workoutDao;
            this.ts = ts;
        }

        @Override
        protected Void doInBackground(String... strings) {
            this.workoutDao.updateWorkoutByDay(strings[0], ts);
            return null;
        }
    }


}
