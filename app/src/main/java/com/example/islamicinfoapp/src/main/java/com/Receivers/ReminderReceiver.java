package com.example.islamicinfoapp.src.main.java.com.Receivers;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.islamicinfoapp.R;
import com.example.islamicinfoapp.src.main.java.com.model.Constants;
import com.example.islamicinfoapp.src.main.java.com.services.ReminderLifeService;
import com.example.islamicinfoapp.src.main.java.com.services.ReminderService;
import com.example.islamicinfoapp.src.main.java.com.services.ReminderService_old;
import com.example.islamicinfoapp.src.main.java.com.utilities.Utility;
import com.example.islamicinfoapp.src.main.java.com.view.DialogActivity;
import com.example.islamicinfoapp.src.main.java.com.view.MainActivity;

import java.util.List;

public class ReminderReceiver extends BroadcastReceiver {
    private NotificationManagerCompat mNotificationManagerCompat;
    String mNamazName,mNamazTime,mTitle,mDesc,mCityName,mDialogTitle,mCountryName;
    private static final String TAG = ReminderReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(Constants.PRAYER_TAG, TAG + " onReceive: ");
        mNamazName = intent.getStringExtra(context.getResources().getString(R.string.namazName));
        mNamazTime = intent.getStringExtra(context.getResources().getString(R.string.namazTime));
        mCityName = intent.getStringExtra(context.getResources().getString(R.string.cityname));
        mCountryName = intent.getStringExtra(context.getResources().getString(R.string.countryname));
        startServiceCall(context);
        Log.d(Constants.PRAYER_TAG, TAG + " onReceive: " + mCityName);
        switch(mNamazName){
            case Constants.FAJR:
                mTitle = context.getResources().getString(R.string.fajr_title_notif) + " " + mCityName + " " + mNamazTime;
                mDialogTitle = context.getResources().getString(R.string.fajr_title_notif) + " " + mCityName;
                mDesc = context.getResources().getString(R.string.fajr_desc);
                break;
            case Constants.SUNRISE:
                mTitle = context.getResources().getString(R.string.sunrise_title_notif) + " " + mCityName + " " + mNamazTime;
                mDialogTitle = context.getResources().getString(R.string.sunrise_title_notif) + " " + mCityName;
                mDesc = context.getResources().getString(R.string.sunrise_desc);
                break;
            case Constants.DHUHR:
                mTitle = context.getResources().getString(R.string.dhuhr_title_notif) + " " + mCityName + " " + mNamazTime;
                mDialogTitle = context.getResources().getString(R.string.dhuhr_title_notif) + " " + mCityName;
                mDesc = context.getResources().getString(R.string.dhuhr_desc);
                break;
            case Constants.ASR:
                mTitle = context.getResources().getString(R.string.asr_title_notif) + " " + mCityName + " " + mNamazTime;
                mDialogTitle = context.getResources().getString(R.string.asr_title_notif) + " " + mCityName;
                mDesc = context.getResources().getString(R.string.asr_desc);
                break;
            case Constants.MAGHRIB:
                mTitle = context.getResources().getString(R.string.maghrib_title_notif) + " " + mCityName + " " + mNamazTime;
                mDialogTitle = context.getResources().getString(R.string.maghrib_title_notif) + " " + mCityName;
                mDesc = context.getResources().getString(R.string.maghrib_desc);
                break;
            case Constants.ISHA:
                mTitle = context.getResources().getString(R.string.isha_title_notif) + " " + mCityName + " " + mNamazTime;
                mDialogTitle = context.getResources().getString(R.string.isha_title_notif) + " " + mCityName;
                mDesc = context.getResources().getString(R.string.isha_desc);
                break;
            default:
                break;
        }

        if ((checkRunningApp(context)).equals(context.getResources().getString(R.string.package_name))){
            Log.d(Constants.PRAYER_TAG, TAG + " onReceive: " + "if");
            createCustomDialog(context,mNamazTime,mDialogTitle,mDesc);
        }
        else{
            Log.d(Constants.PRAYER_TAG, TAG + "onReceive: " + "else");
            createNotification(context,mTitle,mDesc);
        }
    }

    private void startServiceCall(Context context) {
        Log.d(Constants.PRAYER_TAG,TAG +  "startServiceCall: ");
        //Intent serviceIntent = new Intent(context, ReminderService.class);
        Intent serviceIntent = new Intent(context, ReminderLifeService.class);
        serviceIntent.putExtra(context.getResources().getString(R.string.cityname),mCityName);
        serviceIntent.putExtra(context.getResources().getString(R.string.countryname),mCountryName);
        serviceIntent.putExtra(context.getResources().getString(R.string.namazName),mNamazName);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            context.startForegroundService(serviceIntent);
//        }
//        else {
//            context.startService(serviceIntent);
//        } .
        context.startService(serviceIntent);
    }

    private void createCustomDialog(Context context,
                                    String mNamazTime,String mTitle, String mDesc) {
       Log.d(Constants.PRAYER_TAG, TAG + " createCustomDialog: " + mNamazTime + mTitle + mDesc);
//        NotifDialogFragment dialogFragment = new NotifDialogFragment();
//        dialogFragment.show(context.getSupportFragmentManager(),"dialog");

       Intent intent = new Intent(context, DialogActivity.class);
       intent.putExtra(context.getResources().getString(R.string.dialogTitle),mTitle);
       intent.putExtra(context.getResources().getString(R.string.namazTime),mNamazTime);
       intent.putExtra(context.getResources().getString(R.string.dialogdesc),mDesc);
       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       context.startActivity(intent);

        //Navigation.findNavController((MainActivity)context,R.id.nav_host_fragment).navigate(R.id.notifDialogFragment);
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        final View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog,null,false);
//        TextView title = view.findViewById(R.id.dialog_title);
//        ImageView imageView = view.findViewById(R.id.dialog_image);
//        TextView time_text = view.findViewById(R.id.dialog_time_text);
//        TextView desc = view.findViewById(R.id.dialog_desc);
//        title.setText(mTitle);
//        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_quran));
//        time_text.setText(mNamazTime);
//        desc.setText(mDesc);
//        builder.setView(view);
//        builder.setNeutralButton(context.getResources().getString(R.string.close), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
    }

    private void createNotification(Context context, String mTitle, String mDesc) {
        mNotificationManagerCompat = NotificationManagerCompat.from(context);
        Intent startAppIntent = new Intent(context, MainActivity.class);
        startAppIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,startAppIntent,0);

        //Utility.playSound(context);

        Notification notification = new NotificationCompat.Builder(context,Constants.NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_quran)
                .setContentTitle(mTitle)
                .setContentText(mDesc)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(mDesc))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .build();
        mNotificationManagerCompat.notify(Constants.NOTIFICATION_ID,notification);
    }

    private String checkRunningApp(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(1);
        Log.d(Constants.PRAYER_TAG, TAG + " checkRunningApp: " + tasks.get(0).baseActivity.getPackageName());
        return tasks.get(0).baseActivity.getPackageName();

//        if (tasks.get(0).baseActivity.getPackageName().
//                equals(context.getResources().getString(R.string.package_name))){
//            Log.d("prayer", "checkRunningApp: " + "your app");
//            createNotification()
//        }
//        else{
//
//        }
    }
}
