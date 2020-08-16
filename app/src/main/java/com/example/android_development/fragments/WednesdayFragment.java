package com.example.android_development.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_development.R;

public class WednesdayFragment extends Fragment {
    private SharedPreferences mPreferences;
    private String difficulty;

    private TextView exOneRep;
    private TextView exTwoRep;
    private TextView exThreeRep;
    private TextView exFourRep;
    private TextView exFiveRep;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wednesday, container, false);

        this.exOneRep = v.findViewById(R.id.ex_one_rep);
        this.exTwoRep = v.findViewById(R.id.ex_two_rep);
        this.exThreeRep = v.findViewById(R.id.ex_three_rep);
        this.exFourRep = v.findViewById(R.id.ex_four_rep);
        this.exFiveRep = v.findViewById(R.id.ex_five_rep);

        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.difficulty = checkSharedPreferences();

        this.exOneRep.setText(this.difficulty);
        this.exTwoRep.setText(this.difficulty);
        this.exThreeRep.setText(this.difficulty);
        this.exFourRep.setText(this.difficulty);
        this.exFiveRep.setText(this.difficulty);

        return v;
    }

    private String checkSharedPreferences(){
        return this.mPreferences.getString(
                getString(R.string.difficulty_option),
                getString(R.string._10_reps));
    }
}