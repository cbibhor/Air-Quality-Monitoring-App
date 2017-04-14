package com.bc10.aqmtest.airqyalitymonitoringtestapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class Receive_sms extends BroadcastReceiver {

    DataFromSms inst = DataFromSms.instance();

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle myBundle = intent.getExtras();
        SmsMessage messages = null;
        String strMessage = "";
        String strFrom = "";

        if (myBundle != null) {
            Object[] pdus = (Object[]) myBundle.get("pdus");

            for (int i = 0; i < pdus.length; i++) {
                messages = SmsMessage.createFromPdu((byte[]) pdus[i]);
                strFrom = messages.getOriginatingAddress();
                strMessage = messages.getMessageBody();
            }

            if (strFrom.equals("+918895603616"))
            {
                Toast.makeText(context, "Receiving SMS", Toast.LENGTH_SHORT).show();

                scan(strMessage, context);
            }

        }

    }



    public void scan(String msgs, Context context)
    {
        Calendar calObj = Calendar.getInstance();
        DateFormat datef = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        DateFormat timef = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String strdate = datef.format(calObj.getTime());
        String strtime = timef.format(calObj.getTime());

        Scanner scan = new Scanner(msgs);
        //String line0 = scan.nextLine();
        String line1 = scan.nextLine();
        String line2 = scan.nextLine();
        String line3 = scan.nextLine();
        String line4 = scan.nextLine();
        String line5 = scan.nextLine();
        String line6 = scan.nextLine();
        String line7 = scan.nextLine();
        String line8 = scan.nextLine();
        String line9 = scan.nextLine();
        String line10 = scan.nextLine();
        scan.close();

        inst.msgdata(strdate, strtime, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10);
    }
}
