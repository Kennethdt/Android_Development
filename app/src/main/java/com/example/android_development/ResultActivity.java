package com.example.android_development;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android_development.database.AppRepository;
import com.example.android_development.entities.Workout;
import com.example.android_development.entities.WorkoutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ResultActivity extends AppCompatActivity {
    private TextView currentDayText;
    private TextView timesCompletedText;
    private TextView timeSpendText;
    private TextView quoteOfTheDay;

    private int timeSpend;
    private String currentDay;

    private WorkoutData workoutData;

    private AppRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.mRepository = new AppRepository(getApplication());

        Intent i= getIntent();
        this.timeSpend = i.getIntExtra("TimeElapsed", 0);
        this.currentDay = i.getStringExtra("CurrentDay");

        this.workoutData = this.mRepository.getWorkoutByDay(currentDay);
        Log.i("Workout Data", workoutData.dayOfWeek);
        Log.i("Workout Data", "" + this.workoutData.timesCompleted);
        Log.i("Workout Data", "" + this.workoutData.totalTimeSpend);

        this.currentDayText = findViewById(R.id.current_day_text);
        this.timesCompletedText = findViewById(R.id.times_completed_text);
        this.timeSpendText = findViewById(R.id.time_spend_text);
        this.quoteOfTheDay = findViewById(R.id.quote_of_the_day);

        this.currentDayText.setText("Your " + this.currentDay + " Workout:");
        this.timesCompletedText.setText("Has been Completed " + this.workoutData.timesCompleted + " times");
        this.timeSpendText.setText("For a total of " + this.workoutData.totalTimeSpend/1000 + " seconds");
        this.quoteOfTheDay.setText(getQuoteOfTheDay());

        getSupportActionBar().setTitle("your time: " + timeSpend);
    }

    public String getQuoteOfTheDay() {
        String QOTD = "";
        String result = mRepository.getQuote();


        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject contents = jsonObject.getJSONObject("contents");
            JSONArray quotes = contents.getJSONArray("quotes");
            QOTD = quotes.getJSONObject(0).getString("quote");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return QOTD;
    }
}