package com.example.se1417_day08_notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private NotificationManager manager;
    private int notiD = 6789;
    private int numberMsg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickToSend(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("New message");
        builder.setContentText("Notification Demo: Message has received");
        builder.setSmallIcon(R.drawable.ic_action_unread);
        builder.setNumber(++numberMsg);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("welcome", "HieuBD");

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent
                = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(notiD, builder.build());
    }

    public void clickToSendStyle(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("LV UP");
        builder.setContentText("Notification Demo: Message has received");
        builder.setSmallIcon(R.drawable.ic_action_unread);
        builder.setNumber(++numberMsg);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = {"HP+","Mana+","Luck+"};
        for (int i = 0; i < events.length; i++){
            inboxStyle.addLine(events[i]);
        }
        builder.setStyle(inboxStyle);

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("welcome", "HieuBD");

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent
                = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(notiD, builder.build());
    }
}