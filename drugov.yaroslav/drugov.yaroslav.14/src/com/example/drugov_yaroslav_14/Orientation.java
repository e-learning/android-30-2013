package com.example.drugov_yaroslav_14;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created with IntelliJ IDEA.
 * User: SEMI12
 * Date: 17.12.13
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class Orientation implements SensorEventListener {

    public float ax;
    public float ay;
    public float az;

    public Orientation(Context context) {
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        ax = sensorEvent.values[0];
        ay = sensorEvent.values[1];
        az = sensorEvent.values[2];
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
