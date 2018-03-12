package com.lj.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by ff on 2018/3/5.
 */

public class MyService extends Service {
    public static final String TAG = "MyService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
        Log.d(TAG, "MyService thread id is " + Thread.currentThread().getId());
        Log.d(TAG,"process id is" +android.os.Process.myPid());
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//        Notification notification1 = new Notification.Builder(this)
//                .setContentText("这是一个通知的内容").setContentTitle("这是一个通知的标题")
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentIntent(pendingIntent).build();
//        startForeground(1, notification1);
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind() executed");
        return super.onUnbind(intent);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() executed");
        return mBinder;
    }
    private MyAIDLService.Stub mBinder = new  MyAIDLService.Stub() {

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if(!TextUtils.isEmpty(str)){
                return str.toUpperCase();
            }
            return null;
        }
    };

    class MyBinder extends Binder {
        public void startDownload() {
            Log.d(TAG, "startDownload() executed");
            //执行具体的下载任务
        }
    }
}
