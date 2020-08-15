package com.example.android_development;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

    private String qotd;

    private int timeSpend;
    private String currentDay;

    private WorkoutData workoutData;

    private AppRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mRepository = new AppRepository(getApplication());

        if (checkInternetConnection()){
            qotd = getQuoteOfTheDay();
            if (!qotd.equals("")){
                setContentView(R.layout.activity_result);

                this.quoteOfTheDay = findViewById(R.id.quote_of_the_day);
                this.quoteOfTheDay.setText(getQuoteOfTheDay());
            }
            else{
                setContentView(R.layout.activity_result_no_internet);
            }
        }
        else {
            setContentView(R.layout.activity_result_no_internet);
        }

        Intent i= getIntent();
        this.timeSpend = i.getIntExtra("TimeElapsed", 0);
        this.currentDay = i.getStringExtra("CurrentDay");

        this.workoutData = this.mRepository.getWorkoutByDay(currentDay);
        //Log.i("Workout Data", workoutData.dayOfWeek);
        //Log.i("Workout Data", "" + this.workoutData.timesCompleted);
        //Log.i("Workout Data", "" + this.workoutData.totalTimeSpend);

        this.currentDayText = findViewById(R.id.current_day_text);
        this.timesCompletedText = findViewById(R.id.times_completed_text);
        this.timeSpendText = findViewById(R.id.time_spend_text);


        this.currentDayText.setText(
                String.format(
                        getString(R.string.workout_day),
                        this.currentDay));
        this.timesCompletedText.setText(
                String.format(
                        getString(R.string.nr_times_completed),
                        this.workoutData.timesCompleted));
        this.timeSpendText.setText(
                String.format(
                        getString(R.string.total_time),
                        this.workoutData.totalTimeSpend / 1000));


        getSupportActionBar().setTitle(
                String.format(
                    getString(R.string.cleared_in),
                    timeSpend));
    }

    public String getQuoteOfTheDay() {
        String QOTD = "";
        String result = mRepository.getQuote();

        if (result == null)
            return "";

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

    public boolean checkInternetConnection() {
        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        return connected;
    }
}