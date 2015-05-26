package com.androiddevbook.onyourbike.chapter5.model;

import android.util.Log;

import com.androiddevbook.onyourbike.chapter5.activities.TimerActivity;

/**
 * Created by Administrator on 2015/5/26.
 */
public class TimerState {
    private static String CLASS_NAME;
    private long startedAt;
    private long lastStopped;
    private boolean timerRunning;
    public TimerState() {
        CLASS_NAME=getClass().getName();
    }
    public void reset(){
        // So 0:00:00 is displayed
        timerRunning = false;
        startedAt = System.currentTimeMillis();
        lastStopped = 0;
    }
    public void  start(){
        timerRunning = true;
        startedAt = System.currentTimeMillis();
    }

    public void stop(){
        timerRunning = false;
        lastStopped = System.currentTimeMillis();
    }

    public boolean isRunning(){
        return timerRunning;
    }

    /**
     * Change the counter text view to display the current formatted time
     */
    public String display() {
        String display;
        long diff;
        long seconds;
        long minutes;
        long hours;

        Log.d(CLASS_NAME, "setTimeDisplay");

        diff = elapsedTime();

        // no negative time
        if (diff < 0) {
            diff = 0;
        }

        seconds = diff / 1000;
        minutes = seconds / 60;
        hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;

        display = String.format("%d", hours) + ":"
                + String.format("%02d", minutes) + ":"
                + String.format("%02d", seconds);

        Log.i(CLASS_NAME, "Time is " + display);

        return display;
    }

    public long elapsedTime(){
        long timeNow;

        if(isRunning()){
            timeNow=System.currentTimeMillis();
        }else {
            timeNow=lastStopped;
        }

        return timeNow-startedAt;
    }
}
