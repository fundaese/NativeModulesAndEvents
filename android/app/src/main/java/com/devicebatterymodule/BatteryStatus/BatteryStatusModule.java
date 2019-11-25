package com.devicebatterymodule.BatteryStatus;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BatteryStatusModule extends ReactContextBaseJavaModule  {
    public BatteryStatusModule(ReactApplicationContext reactContext) {

        super(reactContext);
    }

    @Override
    public String getName() {
        return "BatteryStatus";
    }

    @ReactMethod
    public void getBatteryStatus(Callback callback) {

        Intent batteryStatus = getCurrentActivity().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int batteryLevel = -1;
        int batteryScale = 1;
        if (batteryStatus != null) {
            batteryLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, batteryLevel);
            batteryScale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, batteryScale);
        }
        callback.invoke(batteryLevel / (float) batteryScale * 100);
    }
}
