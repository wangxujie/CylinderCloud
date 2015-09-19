package com.example.cylindercloud.websocket;

import android.content.Context;

import com.example.cylindercloud.Constants;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketOptions;

/**
 * Created by wxj on 2015-7-26.
 */
public class WebSocketManager {
    private static WebSocketManager manager = null;
    private final String WebSocket = Constants.WEBSOCKET;
    private WebSocketConnection mConnection;
    private WebSocketConnectListener connectListener;
    private Context context;

    private WebSocketManager(Context contxt) {
        this.context = contxt;
        mConnection = new WebSocketConnection();
    }

    public static WebSocketManager getManager(Context contxt) {
        if (manager == null)
            synchronized (WebSocketManager.class) {
                if (manager == null)
                    manager = new WebSocketManager(contxt);
            }
        return manager;
    }

    public void connect() {
        if (!isConnect()) {
            connectListener = new WebSocketConnectListener(context);
//            WebSocketOptions options = new WebSocketOptions();
//            options.
            try {
                mConnection.connect(WebSocket, connectListener);
            } catch (WebSocketException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnect() {
        if (mConnection == null) return false;
        return mConnection.isConnected();
    }

    public void disConnect() {
        if (mConnection == null) return;
        mConnection.disconnect();
        mConnection = null;
        manager = null;
    }

    public void sendTextMessage(String sendText) {
        if (mConnection == null) {
            return;
        }
        connect();
        mConnection.sendTextMessage(sendText);
    }
}