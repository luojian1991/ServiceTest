package com.lj.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Button btnStartService;
    private Button btnStopService;
    private Button btnBindService;
    private Button btnUnBindService;
    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected() executed");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG,"MainActivity thread id is " +Thread.currentThread().getId());
        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);
        btnBindService = findViewById(R.id.btn_bind_service
        );
        btnUnBindService = findViewById(R.id.btn_unbind_service);

        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnBindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                Intent startService = new Intent(this, MyService.class);
                startService(startService);
                break;
            case R.id.btn_stop_service:
                Log.d(TAG,"click Stop Service button");
                Intent stopService = new Intent(this, MyService.class);
                stopService(stopService);
                break;
            case R.id.btn_bind_service:
                Intent bindService = new Intent(this, MyService.class);
                bindService(bindService, connection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                Log.d(TAG,"click Unbind Service button");
                unbindService(connection);
                break;
        }
    }
}
