package com.devicebatterymodule.BatteryStatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import javax.annotation.Nullable;

public class BatteryBroadcast extends BroadcastReceiver {

    public boolean batteryLow;
    private ReactContext reactContext;

    public BatteryBroadcast(ReactContext _reactContext) {
        reactContext = _reactContext;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        batteryLow = true;
        WritableMap params = Arguments.createMap();
        params.putBoolean("battery", batteryLow);
        Log.i("fnd", "BatteryBroadcast");
        sendEvent(reactContext, params);
    }

    private void sendEvent(ReactContext reactContext, @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("batteryLow", params); 
    }
}
