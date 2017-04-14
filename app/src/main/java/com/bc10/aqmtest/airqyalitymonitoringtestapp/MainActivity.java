package com.bc10.aqmtest.airqyalitymonitoringtestapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import java.io.File;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    private static MainActivity inst;
    //server insta = server.instance();
    SQLiteDatabase dbobj;

    TextView data0;
    TextView data00;
    TextView data1;
    TextView data2;
    TextView data3;
    TextView data4;
    TextView data5;
    TextView data6;
    TextView data7;
    TextView data8;
    TextView data9;
    TextView data10;
    TextView des1;

    String set0 = "";
    String set00 = "";
    String set1 = "";
    String set2 = "";
    String set3 = "";
    String set4 = "";
    String set5 = "";
    String set6 = "";
    String set7 = "";
    String set8 = "";
    String set9 = "";
    String set10 = "";

    Float fval1;
    Float fval2;
    Float fval3;
    Float fval4;
    Float fval5;
    Float fval6;
    Float fval7;
    Float fval8;
    Double fval9;
    Float fval10;

    LinearLayout box1;
    LinearLayout box2;
    LinearLayout box3;
    LinearLayout box4;
    LinearLayout box5;
    LinearLayout box6;
    LinearLayout box7;
    LinearLayout box8;
    LinearLayout box9;
    LinearLayout box10;



    //MainActivity instance
    public static MainActivity instance()
    {
        return inst;
    }



    //onStart
    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }



    //onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //insta.finish();
        getdataforgraph();
    }


    ////////////////////////////////////////////Navigation Drawer Menu//////////////////////////////
    public void initNavigationDrawer(){
        NavigationView navigationview = (NavigationView)findViewById(R.id.navigation_view);
        final Intent in = new Intent(this, MyDatabase.class);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.home:
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.knowdata:
                        sendsms();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.viewdb:
                        //startActivity(in);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////



    /////////////////////////////////////Data for main UI card//////////////////////////////////////
    public void getdataforgraph(){

        setContentView(R.layout.start_ui);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Air Quality Monitoring");
        initNavigationDrawer();

        data0 = (TextView) findViewById(R.id.text_date);
        data00 = (TextView) findViewById(R.id.text_time);
        data1 = (TextView) findViewById(R.id.tempval);
        data2 = (TextView) findViewById(R.id.avval);
        data3 = (TextView) findViewById(R.id.humidval);
        data4 = (TextView) findViewById(R.id.pm10val);
        data5 = (TextView) findViewById(R.id.pm25val);
        data6 = (TextView) findViewById(R.id.no2val);
        data7 = (TextView) findViewById(R.id.o3val);
        data8 = (TextView) findViewById(R.id.coval);
        data9 = (TextView) findViewById(R.id.so2val);
        data10 = (TextView) findViewById(R.id.nh3val);

        box1 = (LinearLayout) findViewById(R.id.box1);
        box2 = (LinearLayout) findViewById(R.id.box2);
        box3 = (LinearLayout) findViewById(R.id.box3);
        box4 = (LinearLayout) findViewById(R.id.box4);
        box5 = (LinearLayout) findViewById(R.id.box5);
        box6 = (LinearLayout) findViewById(R.id.box6);
        box7 = (LinearLayout) findViewById(R.id.box7);
        box8 = (LinearLayout) findViewById(R.id.box8);
        box9 = (LinearLayout) findViewById(R.id.box9);
        box10 = (LinearLayout) findViewById(R.id.box10);

        File dbfile = new File("/data/data/com.bc10.aqmtest.airqyalitymonitoringtestapp/databases/Main_Database");
        if(dbfile.exists()) {
            String path = "/data/data/com.bc10.aqmtest.airqyalitymonitoringtestapp/databases/Main_Database";
            dbobj = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if (dbobj != null) {

                /*setContentView(R.layout.start_ui);
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Air Quality Monitoring");
                initNavigationDrawer();*/

                Cursor csr = dbobj.rawQuery("SELECT * FROM myTable", null);
                int column0 = csr.getColumnIndex("date");
                int column00 = csr.getColumnIndex("time");
                int column1 = csr.getColumnIndex("temp");
                int column2 = csr.getColumnIndex("dustDensity");
                int column3 = csr.getColumnIndex("humidity");
                int column4 = csr.getColumnIndex("pressure");
                int column5 = csr.getColumnIndex("altitude");
                int column6 = csr.getColumnIndex("methane");
                int column7 = csr.getColumnIndex("O3");
                int column8 = csr.getColumnIndex("CO");
                int column9 = csr.getColumnIndex("H2S");
                int column10 = csr.getColumnIndex("NH3");
                csr.moveToLast();

                String lastrow = "";
                set0 = csr.getString(column0);
                set00 = csr.getString(column00);
                lastrow = csr.getString(column1) + "\n" + csr.getString(column2) + "\n" + csr.getString(column3) + "\n"
                        + csr.getString(column4) + "\n" + csr.getString(column5) + "\n" + csr.getString(column6) + "\n"
                        + csr.getString(column7) + "\n" + csr.getString(column8) + "\n" + csr.getString(column9) + "\n"
                        + csr.getString(column10);

                Scanner scan = new Scanner(lastrow);
                set1 = scan.nextLine();
                set2 = scan.nextLine();
                set3 = scan.nextLine();
                set4 = scan.nextLine();
                set5 = scan.nextLine();
                set6 = scan.nextLine();
                set7 = scan.nextLine();
                set8 = scan.nextLine();
                set9 = scan.nextLine();
                set10 = scan.nextLine();

                //fval1 = Float.parseFloat(set1);
                //fval2 = Float.parseFloat(set2);
                //fval3 = Float.parseFloat(set3);
                //fval4 = Float.parseFloat(set4);
                //fval5 = Float.parseFloat(set5);
                //fval6 = Float.parseFloat(set6);
                //fval7 = Float.parseFloat(set7);
                //fval8 = Float.parseFloat(set8);
                //fval9 = Double.parseDouble(set9);
                //fval10 = Float.parseFloat(set10);



                data0.setText(set0);
                data00.setText(set00);
                data1.setText(set1);
                data2.setText(set2);
                data3.setText(set3);
                data4.setText(set4);
                data5.setText(set5);
                data6.setText("--");
                data7.setText("--");
                data8.setText(set8);
                data9.setText("--");
                data10.setText("--");

                /*box1 = (LinearLayout) findViewById(R.id.box1);
                box2 = (LinearLayout) findViewById(R.id.box2);
                box3 = (LinearLayout) findViewById(R.id.box3);
                box4 = (LinearLayout) findViewById(R.id.box4);
                box5 = (LinearLayout) findViewById(R.id.box5);
                box6 = (LinearLayout) findViewById(R.id.box6);
                box7 = (LinearLayout) findViewById(R.id.box7);
                box8 = (LinearLayout) findViewById(R.id.box8);
                box9 = (LinearLayout) findViewById(R.id.box9);
                box10 = (LinearLayout) findViewById(R.id.box10);*/

                box1.setBackgroundResource(R.drawable.bar_orange);
                box2.setBackgroundResource(R.drawable.bar_purple);
                box3.setBackgroundResource(R.drawable.bar_blue);
                box4.setBackgroundResource(R.drawable.bar_red);
                box5.setBackgroundResource(R.drawable.bar_green);
                box6.setBackgroundResource(R.drawable.bar_yellow);
                box7.setBackgroundResource(R.drawable.bar_orange);
                box8.setBackgroundResource(R.drawable.bar_green);
                box9.setBackgroundResource(R.drawable.bar_red);
                box10.setBackgroundResource(R.drawable.bar_yellow);

                /*if(fval4 < 90){ box4.setBackgroundResource(R.drawable.bar_green);}
                else if(fval4 > 110){ box4.setBackgroundResource(R.drawable.bar_red);}
                else { box4.setBackgroundResource(R.drawable.bar_yellow);}

                if(fval5 < 100){ box5.setBackgroundResource(R.drawable.bar_green);}
                else if(fval5 > 120){ box5.setBackgroundResource(R.drawable.bar_red);}
                else { box5.setBackgroundResource(R.drawable.bar_yellow);}

                if(fval6 > 2 && fval6<=100){ box6.setBackgroundResource(R.drawable.bar_yellow);}
                else if(fval6 > 100){ box6.setBackgroundResource(R.drawable.bar_red);}
                else { box6.setBackgroundResource(R.drawable.bar_green);}

                if(fval7>0.05 && fval7 <= 0.2){ box7.setBackgroundResource(R.drawable.bar_yellow);}
                else if(fval7 > 0.2){ box7.setBackgroundResource(R.drawable.bar_red);}
                else { box7.setBackgroundResource(R.drawable.bar_green);}

                if(fval8>0.2 && fval8 < 9){ box8.setBackgroundResource(R.drawable.bar_yellow);}
                else if(fval8 >= 9){ box8.setBackgroundResource(R.drawable.bar_red);}
                else { box8.setBackgroundResource(R.drawable.bar_green);}

                if(fval9>0.001 && fval9 <=5){ box9.setBackgroundResource(R.drawable.bar_yellow);}
                else if(fval9 > 5){ box9.setBackgroundResource(R.drawable.bar_red);}
                else { box9.setBackgroundResource(R.drawable.bar_green);}

                if(fval10>15 && fval10 <=25){ box10.setBackgroundResource(R.drawable.bar_yellow);}
                else if(fval10 > 25){ box10.setBackgroundResource(R.drawable.bar_red);}
                else { box10.setBackgroundResource(R.drawable.bar_green);}  */

                dbobj.close();
            }
        }
        else {
            //setContentView(R.layout.alternate_start);
            data0.setText("--");
            data00.setText("--");
            data1.setText("--");
            data2.setText("--");
            data3.setText("--");
            data4.setText("--");
            data5.setText("--");
            data6.setText("--");
            data7.setText("--");
            data8.setText("--");
            data9.setText("--");
            data10.setText("--");

            box1.setBackgroundResource(R.drawable.bar_orange);
            box2.setBackgroundResource(R.drawable.bar_purple);
            box3.setBackgroundResource(R.drawable.bar_blue);
            box4.setBackgroundResource(R.drawable.bar_red);
            box5.setBackgroundResource(R.drawable.bar_green);
            box6.setBackgroundResource(R.drawable.bar_yellow);
            box7.setBackgroundResource(R.drawable.bar_orange);
            box8.setBackgroundResource(R.drawable.bar_green);
            box9.setBackgroundResource(R.drawable.bar_red);
            box10.setBackgroundResource(R.drawable.bar_yellow);
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////Send SMS/////////////////////////////////////////////
    public void sendsms(){
        String phoneNumber = "+918895606316";
        String smsBody = "Phone To Device:Return Data";
        Toast.makeText(this, "Sending SMS", Toast.LENGTH_SHORT).show();

        // Get the default instance of SmsManager
        SmsManager smsManager = SmsManager.getDefault();
        // Send a text based SMS
        smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
        Toast.makeText(this, "Waiting for Data via SMS", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Service Unavailable", Toast.LENGTH_LONG).show();
        Intent smsintent = new Intent(MainActivity.this, DataFromSms.class);
        startActivity(smsintent);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////Know your data button////////////////////////////////////
    public void card2clicked(View view){
        sendsms();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////



    ///////////////////////////////////////Database clicked//////////////////////////////////////////
    public void card3clicked(View view){

        File dbfile = new File("/data/data/com.bc10.aqmtest.airqyalitymonitoringtestapp/databases/Main_Database");
        if(dbfile.exists()) {
            String path = "/data/data/com.bc10.aqmtest.airqyalitymonitoringtestapp/databases/Main_Database";
            dbobj = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            if (dbobj != null) {
                dbobj.close();
                Intent i = new Intent(this, MyDatabase.class);
                startActivity(i);
            }
            dbobj.close();
        }
        else
        Toast.makeText(this, "No Database Found", Toast.LENGTH_LONG).show();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////


}

