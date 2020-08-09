package com.example.android_development;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Difficulty extends Fragment {
    private DifficultyFragmentListener listener;
    private Button btnBeginner;
    private Button btnIntermediate;
    private Button btnHard;

    public interface DifficultyFragmentListener{
        void onInputDifficulty(CharSequence input);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_difficulty, container, false);

        btnBeginner = v.findViewById(R.id.btn_beginner);
        btnBeginner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("start", "go do 20 reps");
                listener.onInputDifficulty("20 reps");

            }
        });
        btnIntermediate = v.findViewById(R.id.btn_intermediate);
        btnIntermediate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onInputDifficulty("30 reps");
            }
        });
        btnHard = v.findViewById(R.id.btn_hard);
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onInputDifficulty("1000 reps");
            }
        });

        return v;
    }

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
}