package com.example.android_development.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android_development.R;

public class Difficulty extends Fragment {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    //private DifficultyFragmentListener listener;
    private Button btnBeginner;
    private Button btnIntermediate;
    private Button btnHard;
    /*
    public interface DifficultyFragmentListener{
        void onInputDifficulty(CharSequence input);
    }
    */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_difficulty, container, false);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        mEditor = mPreferences.edit();

        btnBeginner = v.findViewById(R.id.btn_beginner);
        btnBeginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString(
                        getString(R.string.difficulty_option),
                        getString(R.string._10_reps));
                mEditor.apply();
            }
        });
        btnIntermediate = v.findViewById(R.id.btn_intermediate);
        btnIntermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString(
                        getString(R.string.difficulty_option),
                        getString(R.string._30_reps));
                mEditor.apply();

            }
        });
        btnHard = v.findViewById(R.id.btn_hard);
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString(
                        getString(R.string.difficulty_option),
                        getString(R.string._1000_reps));
                mEditor.commit();
            }
        });

        return v;
    }
/*
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof DifficultyFragmentListener){
            listener = (DifficultyFragmentListener)context;
        }
        else {
            throw new RuntimeException(context.toString()
            + " must implement DifficultyFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    */
}