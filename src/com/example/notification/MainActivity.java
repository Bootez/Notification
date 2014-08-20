package com.example.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    private NotificationManager mNotificationManager;
    private Button notifyButton;
    static final int NOTIFICATION_ID = 0x123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notifyButton = (Button)findViewById(R.id.notification);
        notifyButton.setOnClickListener(new notifyButtonListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    class notifyButtonListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, OtherActivity.class);
            PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
            Notification notify = new Notification.Builder(MainActivity.this)
            .setAutoCancel(true)
            .setTicker("New Message Coming~")
            .setSmallIcon(R.drawable.ic_launcher)
            .setContentTitle("Message")
            .setContentText("Congratulations~")
            .setSound(Uri.parse("android.resource://com.example.notification/"
                    + R.raw.msg))
            .setWhen(System.currentTimeMillis())
            .setContentIntent(mPendingIntent)
            .build();
            
            mNotificationManager.notify(NOTIFICATION_ID, notify);
        }
    }

}
