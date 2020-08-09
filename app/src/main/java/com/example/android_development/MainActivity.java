package com.example.android_development;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button tempbtn;

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
        Fragment fragment;

        if (view == findViewById(R.id.MondayFragButton)){
            fragment = new MondayFragment();
        }
        else if (view == findViewById(R.id.TuesdayFragButton)){
            fragment = new TuesdayFragment();
        }
        else if (view == findViewById(R.id.WednesdayFragButton)){
            fragment = new WednesdayFragment();
        }
        else if (view == findViewById(R.id.ThursdayFragButton)){
            fragment = new ThursdayFragment();
        }
        else if (view == findViewById(R.id.FridayFragButton)){
            fragment = new FridayFragment();
        }
        else {
            fragment = new MondayFragment();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, fragment);
        ft.commit();
    }


}