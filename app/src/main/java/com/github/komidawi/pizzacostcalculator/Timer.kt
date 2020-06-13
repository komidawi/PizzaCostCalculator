package com.github.komidawi.pizzacostcalculator

import android.os.Handler
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class Timer(lifecycle: Lifecycle) : LifecycleObserver {

    private var secondsElapsed = 0
    private val handler: Handler = Handler()
    private lateinit var runnable: Runnable

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun startTimer() {
        runnable = Runnable {
            secondsElapsed++
            Log.i("TIMER", secondsElapsed.toString())
            handler.postDelayed(runnable, 1000)
        }

        handler.postDelayed(runnable, 1000)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun stopTimer() {
        handler.removeCallbacks(runnable)
    }
}