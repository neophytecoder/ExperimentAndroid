package org.jkarsten.experiment.experimentandroid;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor mSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String result = "";
        for (int ii=0; ii<sensorEvent.values.length; ii++) {
            result += sensorEvent.values[ii] + ", ";
        }
        Log.d(SensorActivity.class.getSimpleName(), "onSensorChanged " + result + " " + sensorEvent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d(SensorActivity.class.getSimpleName(), "onAccuracyChanged " + i + " " + sensor);
    }
}
