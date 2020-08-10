package com.example.android_development;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    private int example;
    private EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i= getIntent();
        example = i.getIntExtra("TimeElapsed", 0);
        Log.i(null,"result: " + example);

        getSupportActionBar().setTitle("your time: " + example);

        editTextInput = findViewById(R.id.edit_text_example);
    }

    public void startService(View view){
        String input = editTextInput.getText().toString();

        Intent serviceIntent = new Intent(this, MyService.class);
        serviceIntent.putExtra("inputExtra", input);

        startService(serviceIntent);
    }

    public void stopService(View view){
        Intent serviceIntent = new Intent(this, MyService.class);

        stopService(serviceIntent);
    }
}