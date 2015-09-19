package com.example.cylindercloud.websocket;

import android.content.Context;
import android.util.Log;
;

import com.example.cylindercloud.utils.LogUtils;

import de.tavendo.autobahn.WebSocketConnectionHandler;

/**
 * Created by wxj on 2015-7-26.
 */
public class WebSocketConnectListener extends WebSocketConnectionHandler {
    private Context context;

    public WebSocketConnectListener(Context context) {
        this.context = context;
    }

    @Override
    public void onOpen() {
        super.onOpen();
        LogUtils.d("%s", "WebSocketConnectListener connect");
    }

    @Override
    public void onClose(int code, String reason) {
        super.onClose(code, reason);
        LogUtils.d("%s", "WebSocketConnectListener lost");
    }

    @Override
    public void onTextMessage(String payload) {
        super.onTextMessage(payload);
        LogUtils.d("%s","payload = "+payload);
    }

}
