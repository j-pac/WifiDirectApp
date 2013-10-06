package com.example.Fragments;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.Fragments.DataSharing.DataSharingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: brutus
 * Date: 05-10-2013
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
public class DevicesListFragment extends ListFragment {

    private boolean mDualPane;
    private int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, searchForDevices()));

        View detailsFrame = getActivity().findViewById(R.id.devices);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            chooseCommunication();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCurCheckPosition = position;
        chooseCommunication();
    }


    private List<String> searchForDevices() {
        List<String> devices_list = new ArrayList<String>();
        devices_list.add("Android do Zé");
        devices_list.add("Android do Jaquim");
        return devices_list;
    }

    /**
     * This method fills the CommunicationFragment when a element is choosen from the list
     */
    private void chooseCommunication() {
        if (mDualPane) {
            //highlight the selected item if the screen its landscape
            getListView().setItemChecked(mCurCheckPosition, true);

            DataSharingFragment communication = (DataSharingFragment) getFragmentManager().findFragmentById(R.id.details);

            if (communication == null || communication.getShownIndex() != mCurCheckPosition) {
                // Create a new fragment to show this selection
                communication = DataSharingFragment.newInstance(mCurCheckPosition);

                // Replace the fragments
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (mCurCheckPosition == 0) {
                    ft.replace(R.id.details, communication);
                } else {
                    // ft.replace(android.R.id.a_item, communication);
                }
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();

            }
        } else {
            // Start another activity to contain the CommunicationFragment
            Intent i = new Intent();
            i.setClass(getActivity(), DataSharingFragment.class);
            i.putExtra("index", mCurCheckPosition);
            startActivity(i);
        }
    }
}