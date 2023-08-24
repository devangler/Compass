package com.example.compassgithub

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var image: ImageView
    private var currentDegree = 0f
    private lateinit var mSensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image = findViewById(R.id.imageView)
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()

        mSensorManager.registerListener(
            this,
            mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onPause() {
        super.onPause()

        mSensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {

        val degree = event.values[0].toInt().toFloat()
        val ra = RotateAnimation(
            currentDegree,
            -degree,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        ra.duration = 210
        ra.fillAfter = true
        image.startAnimation(ra)
        currentDegree = -degree

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //not
    }
}