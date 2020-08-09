package com.example.android_development;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements Difficulty.DifficultyFragmentListener {
    Button tempbtn;
    private Difficulty difficulty;
    private MondayFragment mondayFragment;
    private TuesdayFragment tuesdayFragment;
    private WednesdayFragment wednesdayFragment;
    private ThursdayFragment thursdayFragment;
    private FridayFragment fridayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempbtn = findViewById(R.id.tempbtn);

        tempbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);

                i.putExtra("Value1", "This value one for ActivityTwo ");
                i.putExtra("Value2", "This value two ActivityTwo");

                startActivity(i);
            }
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_android);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mondayFragment = new MondayFragment();
        tuesdayFragment = new TuesdayFragment();
        wednesdayFragment = new WednesdayFragment();
        thursdayFragment = new ThursdayFragment();
        fridayFragment = new FridayFragment();
        difficulty = new Difficulty();

        setDay();
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
                i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.overview:
                i = new Intent(MainActivity.this, MainActivity2.class);

                i.putExtra("Value1", "This value one for ActivityTwo");
                i.putExtra("Value2", "This value two ActivityTwo");

                startActivity(i);
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
        Calendar calendar = Calendar.getInstance();
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