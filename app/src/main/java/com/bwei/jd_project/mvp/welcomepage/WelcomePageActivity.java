package com.bwei.jd_project.mvp.welcomepage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bwei.jd_project.R;
import com.bwei.jd_project.mvp.home.view.activity.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomePageActivity extends AppCompatActivity {

    private MyHandler myHandler = new MyHandler();

    private int num = 3;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initViews();

        initDatas();

    }

    private void initDatas() {

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                myHandler.sendEmptyMessage(0);

            }
        },0,1000);

    }

    private void initViews() {

    }

    class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            num--;

            if (num==0){

                startActivity(new Intent(WelcomePageActivity.this, HomeActivity.class));
                finish();
            }

        }
    }
}
