package com.bytedance.clockapplication;

import android.graphics.Paint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bytedance.clockapplication.widget.Clock;

public class ClockHandlerThread extends HandlerThread implements Handler.Callback {

    public  static  final int MSG_CLOCK = 100;

    private  Handler mClockHandler;
    private  Clock mClock;

    public  ClockHandlerThread(String name){
        super(name);
    }

    public  ClockHandlerThread(String name, Clock priority){
        super(name);
        this.mClock = priority;
    }

    @Override
    protected void onLooperPrepared(){
        mClockHandler = new Handler(getLooper(), this);
        mClockHandler.sendEmptyMessage(MSG_CLOCK);
    }

    @Override
    public boolean handleMessage(Message msg){
        switch ((msg.what)){
            case MSG_CLOCK:
                //mClockView.setShowAnalog(true);
                // We try only with the AttachInfo because there's no point in invalidating
                // if we are not attached to our window
                mClock.postInvalidate();
                mClockHandler.sendEmptyMessageDelayed(MSG_CLOCK, 1000);
                break;
        }
        return  true;
    }

    @Override
    public void run(){
        super.run();
        if(!isInterrupted()){
            return;
        }else{
            mClockHandler.sendEmptyMessage(MSG_CLOCK);
        }
    }
}
