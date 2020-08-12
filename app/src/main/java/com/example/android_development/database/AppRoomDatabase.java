package com.example.android_development.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android_development.daos.WorkoutDao;
import com.example.android_development.entities.Workout;

@Database(entities = {Workout.class}, version = 1)
public abstract class AppRoomDatabase extends RoomDatabase {
    public abstract WorkoutDao workoutDao();

    private static volatile AppRoomDatabase mINSTANCE;

    static AppRoomDatabase getDatabase(final Context context){
        if (mINSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (mINSTANCE == null) {
                    mINSTANCE =
                            Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppRoomDatabase.class,
                                    "app_room_database")
                                    .allowMainThreadQueries()
                                    .fallbackToDestructiveMigration()
                                    .build();
                }
            }
        }
        return mINSTANCE;
    }
}
