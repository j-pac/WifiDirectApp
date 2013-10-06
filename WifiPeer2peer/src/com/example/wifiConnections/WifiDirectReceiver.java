package com.example.wifiConnections;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;



/**
 * Created with IntelliJ IDEA.
 * User: brutus
 * Date: 05-10-2013
 * Time: 23:11
 * To change this template use File | Settings | File Templates.
 */
public class WifiDirectReceiver extends BroadcastReceiver {

    private WifiP2pManager wManager;
    private Channel wChannel;
    private WifiDirectActivity wActivity;

    private PeerListListener wPeerListListener;


    public WifiDirectReceiver(WifiP2pManager wManager, Channel wChannel, WifiDirectActivity wActivity) {
        super();
        this.wManager = wManager;
        this.wChannel = wChannel;
        this.wActivity = wActivity;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equals(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if(state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {

            } else {

            }
        } else if(action.equals(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)) {
            if(wManager != null) {
                wManager.requestPeers(wChannel, wPeerListListener);
            }

        } else if(action.equals(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)) {

        } else if(action.equals(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)) {

        }

    }
}
