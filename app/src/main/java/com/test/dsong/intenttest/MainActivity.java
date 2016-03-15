package com.test.dsong.intenttest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView tvTitle;
    private TextView tvContent;
    private Button btnSend;
    private String title;
    private String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle=(TextView)findViewById(R.id.etTitle);
        tvContent=(TextView)findViewById(R.id.etContent);
        btnSend=(Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }

    public void send(){
        title=tvTitle.getText().toString();//标题
        content=tvContent.getText().toString();//内容
        //得到NotificationManager
        NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);

        //实例化一个通知，指定图标、摘要、时间
        Notification n=new Notification.Builder(this)
                .setAutoCancel(true)
                .setTicker("有新消息")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title+"（通知栏）")
                .setContentText(content+"（通知栏）")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pi)
                .build();

        //发出通知
        nm.notify(1,n);

    }

}
