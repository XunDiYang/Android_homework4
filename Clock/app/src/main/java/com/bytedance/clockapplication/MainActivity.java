package com.bytedance.clockapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bytedance.clockapplication.widget.Clock;

public class MainActivity extends AppCompatActivity {

    private View mRootView;
    private Clock mClockView;
    private ClockHandlerThread cHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootView = findViewById(R.id.root);
        mClockView = findViewById(R.id.clock);

        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClockView.setShowAnalog(!mClockView.isShowAnalog());
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        cHandler = new ClockHandlerThread("name", mClockView);
        cHandler.start();
    }

    @Override
    protected void onStop(){
        super.onStop();
        cHandler.interrupt();
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
    }

}
