package com.example.android_development;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MondayFragment extends Fragment {
    private TextView dlrep;
    private TextView lpdrep;
    private TextView drrep;
    private TextView hsmrrep;
    private TextView oacrrep;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_monday, container, false);

        dlrep = v.findViewById(R.id.dlrep);
        lpdrep = v.findViewById(R.id.lpdrep);
        drrep = v.findViewById(R.id.drrep);
        hsmrrep = v.findViewById(R.id.hsmrrep);
        oacrrep = v.findViewById(R.id.oacrrep);

        return v;
    }

    public void updateTextView(CharSequence NOR){
        Log.i("done", "finished my " + NOR);
        Log.i("exercise", dlrep.getText().toString());
        dlrep.setText(NOR);
        Log.i("exercise", dlrep.getText().toString());
        lpdrep.setText(NOR.toString());
        drrep.setText(NOR.toString());
        hsmrrep.setText(NOR.toString());
        oacrrep.setText(NOR.toString());
    }
}