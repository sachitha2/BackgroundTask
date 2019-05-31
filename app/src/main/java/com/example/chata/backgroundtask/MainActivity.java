package com.example.chata.backgroundtask;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button startT;
    private Handler mainHandler = new Handler();
    private volatile boolean stopThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startT = findViewById(R.id.startThread);
    }
    public void  startThread(View view){
        stopThread = false;
//            ExampleThread thread = new ExampleThread(10);
//            thread.start();
    ExampleRunable runable =  new ExampleRunable(10);
//    runable.run();
    new Thread(runable).start();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //work
//            }
//        }).start();
    }
    public  void stopThread(View view){
        stopThread = true;
    }
    class ExampleThread extends  Thread{
        int secounds;
        ExampleThread(int secounds){
            this.secounds = secounds;
        }
        @Override
        public void run() {
            for(int i = 0;i<=secounds;i++){
                Log.d(TAG, "startThread: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
class  ExampleRunable implements Runnable{
        int secounds;

        ExampleRunable(int secounds){
            this.secounds = secounds;
        }

    @Override
    public void run() {
        for(int i = 0;i<=secounds;i++){
            if (stopThread){
                return;
            }
            if (i == 5){
//                Handler threadHandler = new Handler(Looper.getMainLooper());
//                threadHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        startT.setText("50%");
//                    }
//                });

                /*
                startT.post(new Runnable() {
                    @Override
                    public void run() {
                        startT.setText("50%");
                    }
                });

                */
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startT.setText("50%");
                    }
                });
            }
            Log.d(TAG, "startThread: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
}
