package com.example.Fragments.DataSharing;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: brutus
 * Date: 05-10-2013
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class DataSharingFragment extends Fragment {

    public static DataSharingFragment newInstance(int index) {
        DataSharingFragment cf = new DataSharingFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        cf.setArguments(args);

        return cf;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (container == null) {
            return null;
        }

        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        scroller.addView(text);
        text.setText("Choose something: ");

        return scroller;
    }
}