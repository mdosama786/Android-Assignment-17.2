package com.example.osama.assignment172;



import android.app.Service;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.TextClock;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Osama on 6/12/2017.
 */

public class MyService extends Service {
    TextClock tvClock;
    boolean isTimerRunning=false;
    private final IBinder ser=new MyServiceBinder();

    public class MyServiceBinder extends Binder {
        MyService getMyService(){
            return  MyService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return ser;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String startTimer() {
        isTimerRunning=true;
        SimpleDateFormat dateformat =
                new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);
        return (dateformat.format(new Date()));
    }
    public void stopTimer(){
        isTimerRunning=false;
    }

    public boolean isTimerRunning(){
        return isTimerRunning;
    }

}