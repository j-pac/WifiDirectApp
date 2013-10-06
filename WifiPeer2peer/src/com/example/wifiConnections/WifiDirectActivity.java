package com.example.wifiConnections;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: brutus
 * Date: 05-10-2013
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
public class WifiDirectActivity extends Activity {

    private static final String TAG = "WifiDirectActivity";
    private WifiP2pManager mManager;
    private boolean isWifiP2pEnabled;

    private Channel mChannel;
    private BroadcastReceiver mReceiver;

    private IntentFilter mIntentFilter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mManager = (WifiP2pManager) getSystemService(WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WifiDirectReceiver(mManager, mChannel, this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);

        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(WifiDirectActivity.this, "Discovering devices...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(WifiDirectActivity.this, "Discovery failed: " + reason, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) {
        this.isWifiP2pEnabled = isWifiP2pEnabled;
    }


    private void connectToPeer(WifiP2pDevice device) {
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        mManager.connect(mChannel, config, new ActionListener() {
            @Override
            public void onSuccess() {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(WifiDirectActivity.this, "Connection failed: " + reason + ". Please, try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}