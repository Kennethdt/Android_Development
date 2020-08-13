package com.example.android_development;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.android_development.database.AppRepository;
import com.example.android_development.entities.Workout;

import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements Difficulty.DifficultyFragmentListener {
    private Difficulty difficulty;
    private MondayFragment mondayFragment;
    private TuesdayFragment tuesdayFragment;
    private WednesdayFragment wednesdayFragment;
    private ThursdayFragment thursdayFragment;
    private FridayFragment fridayFragment;

    private Button chrono;
    private Chronometer chronometer;
    private boolean isStart;

    private Calendar calendar;
    private String currentDay;

    private AppRepository mRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mRepository = new AppRepository(getApplication());

        this.chrono = findViewById(R.id.startWorkout);

        this.chrono.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startStopChronometer(view);
            }
        });

        this.calendar = Calendar.getInstance();
        this.currentDay =
                calendar.getDisplayName(
                        Calendar.DAY_OF_WEEK,
                        Calendar.LONG,
                        Locale.getDefault());

        /*
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_android);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        */

        this.mondayFragment = new MondayFragment();
        this.tuesdayFragment = new TuesdayFragment();
        this.wednesdayFragment = new WednesdayFragment();
        this.thursdayFragment = new ThursdayFragment();
        this.fridayFragment = new FridayFragment();
        this.difficulty = new Difficulty();

        setDay();


        this.chronometer = findViewById(R.id.chronometer);

        this.chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometerChanged) {
                chronometer = chronometerChanged;
            }
        });
    }

    public void startStopChronometer(View view){
        if(this.isStart){
            this.chronometer.stop();
            long time = SystemClock.elapsedRealtime() - chronometer.getBase();
            workoutCleared((int)time);
            changeActivity((int)time);
            this.isStart = false;
            stopService(view);
            ((Button)view).setText("Start");
        }else{
            this.chronometer.setBase(SystemClock.elapsedRealtime());
            this.chronometer.start();
            this.isStart = true;
            startService(view);
            ((Button)view).setText("Stop");
        }
    }

    public void changeActivity(int timeElapsed){
        Intent i = new Intent(MainActivity.this, ResultActivity.class);

        i.putExtra("TimeElapsed", timeElapsed );
        i.putExtra("CurrentDay", this.currentDay );

        startActivity(i);
    }

    public void startService(View view){
        Intent serviceIntent = new Intent(this, MyService.class);
        serviceIntent.putExtra("inputExtra", this.currentDay + " workout");

        startService(serviceIntent);
    }

    public void stopService(View view){
        Intent serviceIntent = new Intent(this, MyService.class);

        stopService(serviceIntent);
    }

    public void workoutCleared(int timeSpend){
        Workout workout = new Workout();

        workout.dayOfWeek = currentDay;
        workout.timeSpend = timeSpend;

        this.mRepository.insertWorkout(workout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.schedule:
                //i = new Intent(MainActivity.this, MainActivity.class);
                //startActivity(i);
                break;
            case R.id.overview:
                changeActivity(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(View view){
        Fragment currentFragment;

        if (view == findViewById(R.id.MondayFragButton)){
            currentFragment = mondayFragment;
        }
        else if (view == findViewById(R.id.TuesdayFragButton)){
            currentFragment = tuesdayFragment;
        }
        else if (view == findViewById(R.id.WednesdayFragButton)){
            currentFragment = wednesdayFragment;
        }
        else if (view == findViewById(R.id.ThursdayFragButton)){
            currentFragment = thursdayFragment;
        }
        else if (view == findViewById(R.id.FridayFragButton)){
            currentFragment = fridayFragment;
        }
        else if (view == findViewById(R.id.DifficultyFragButton)) {
            currentFragment = difficulty;
        }
        else {
            currentFragment = mondayFragment;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_place, currentFragment)
                .commit();
    }

    @Override
    public void onInputDifficulty(CharSequence input) {
        Log.i("progress", "doing my " + input);
        mondayFragment.updateTextView(input);
    }

    public void setDay(){
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.TUESDAY:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_place, tuesdayFragment)
                        .commit();
                break;

            case Calendar.WEDNESDAY:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_place, wednesdayFragment)
                        .commit();
                break;

            case Calendar.THURSDAY:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_place, thursdayFragment)
                        .commit();
                break;

            case Calendar.FRIDAY:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_place, fridayFragment)
                        .commit();
                break;
            default:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_place, mondayFragment)
                        .commit();
                break;

        }
    }
}