package com.devicebatterymodule.BatteryStatus;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BatteryStatusModule extends ReactContextBaseJavaModule {

    public float battery;
    private ReactContext mReactContext;

    public BatteryStatusModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    @Override
    public String getName() {
        return "BatteryStatus";
    }

    @ReactMethod
    public void getBatteryStatus(Callback callback) {
        Log.d("fnd", "BatteryStatusModuleÇalıştı");

        BatteryBroadcast broadcast = new BatteryBroadcast(mReactContext);
        Intent batteryLow = getCurrentActivity().registerReceiver(broadcast, new IntentFilter(Intent.ACTION_BATTERY_LOW));

        Intent batteryStatus = getCurrentActivity().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_OKAY));
        int batteryLevel = -1;
        int batteryScale = 1;
        if (batteryStatus != null) {
            batteryLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, batteryLevel);
            batteryScale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, batteryScale);
        }

        battery = batteryLevel / (float) batteryScale * 100;

        callback.invoke(battery);
    }

}
