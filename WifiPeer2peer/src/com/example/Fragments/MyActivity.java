package com.example.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.Fragments.DataSharing.DataSharingFragment;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


    public static class CommunicationActivity extends Activity {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                finish();
                return;
            }

            if(savedInstanceState == null) {
                DataSharingFragment cf = new DataSharingFragment();
                cf.setArguments(getIntent().getExtras());
                getFragmentManager().beginTransaction().add(android.R.id.content, cf).commit();
            }
        }
    }





}
