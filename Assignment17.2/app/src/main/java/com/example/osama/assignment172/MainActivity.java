package com.example.osama.assignment172;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Osama on 6/12/2017.
 */

public class MainActivity extends AppCompatActivity {
    TextView text;
    Button button;
    MyService myService;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyServiceBinder binder = (MyService.MyServiceBinder) service;
            myService = binder.getMyService();
            if (myService.isTimerRunning) {
                updateUIText();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);// find id
        button = (Button) findViewById(R.id.startService_btn);

        Intent intent = new Intent(this, MyService.class);

        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

        button.setOnClickListener(new View.OnClickListener() {// on click lisner
            @Override
            public void onClick (View v){

                updateUIText();
            }
        });
    }

    private void updateUIText() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text.setText(myService.startTimer());
        }
    }

}