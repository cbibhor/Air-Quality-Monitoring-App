package com.bc10.aqmtest.airqyalitymonitoringtestapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class fragment_values extends android.support.v4.app.Fragment{

    SQLiteDatabase dbobj = null;

    String str0 = "";
    String str00 = "";
    String str1 = "";
    String str2 = "";
    String str3 = "";
    String str4 = "";
    String str5 = "";
    String str6 = "";
    String str7 = "";
    String str8 = "";
    String str9 = "";
    String str10 = "";

    TextView text0;
    TextView text00;
    TextView text1;
    TextView text2;
    TextView text3;
    TextView text4;
    TextView text5;
    TextView text6;
    TextView text7;
    TextView text8;
    TextView text9;
    TextView text10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_values, container, false);

        text0 = (TextView) view.findViewById(R.id.text0);
        text00 = (TextView) view.findViewById(R.id.text00);
        text1 = (TextView) view.findViewById(R.id.text1);
        text2 = (TextView) view.findViewById(R.id.text2);
        text3 = (TextView) view.findViewById(R.id.text3);
        text4 = (TextView) view.findViewById(R.id.text4);
        text5 = (TextView) view.findViewById(R.id.text5);
        text6 = (TextView) view.findViewById(R.id.text6);
        text7 = (TextView) view.findViewById(R.id.text7);
        text8 = (TextView) view.findViewById(R.id.text8);
        text9 = (TextView) view.findViewById(R.id.text9);
        text10 = (TextView) view.findViewById(R.id.text10);

        getdata();
        return view;
    }

    ///////////////////////////////////get data from database////////////////////////////////////////////
    public void getdata(){
        String path = "/data/data/com.bc10.aqmtest.airqyalitymonitoringtestapp/databases/Main_Database";
        dbobj = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        if(dbobj != null) {
            Cursor c = dbobj.rawQuery("SELECT * FROM myTable", null);
            int column0 = c.getColumnIndex("date");
            int column00 = c.getColumnIndex("time");
            int column1 = c.getColumnIndex("temp");
            int column2 = c.getColumnIndex("dustDensity");
            int column3 = c.getColumnIndex("humidity");
            int column4 = c.getColumnIndex("pressure");
            int column5 = c.getColumnIndex("altitude");
            int column6 = c.getColumnIndex("methane");
            int column7 = c.getColumnIndex("O3");
            int column8 = c.getColumnIndex("CO");
            int column9 = c.getColumnIndex("H2S");
            int column10 = c.getColumnIndex("NH3");
            c.moveToLast();

            str0 = "";
            str00 = "";
            str1 = "";
            str2 = "";
            str3 = "";
            str4 = "";
            str5 = "";
            str6 = "";
            str7 = "";
            str8 = "";
            str9 = "";
            str10 = "";


            if (c.getCount() > 0) {
                do {
                    str0 += "\n" + c.getString(column0) + "\n";
                    str00 += "\n" + c.getString(column00) + "\n";
                    str1 += "\n" + c.getString(column1) + "\n";
                    str2 += "\n" + c.getString(column2) + "\n";
                    str3 += "\n" + c.getString(column3) + "\n";
                    str4 += "\n" + c.getString(column4) + "\n";
                    str5 += "\n" + c.getString(column5) + "\n";
                    str6 += "\n" + c.getString(column6) + "\n";
                    str7 += "\n" + c.getString(column7) + "\n";
                    str8 += "\n" + c.getString(column8) + "\n";
                    str9 += "\n" + c.getString(column9) + "\n";
                    str10 += "\n" + c.getString(column10) + "\n";
                } while (c.moveToPrevious());


                text0.setText(str0);
                text00.setText(str00);
                text1.setText(str1);
                text2.setText(str2);
                text3.setText(str3);
                text4.setText(str4);
                text5.setText(str5);
                text6.setText("-");
                text7.setText("-");
                text8.setText(str8);
                text9.setText("-");
                text10.setText("-");
            }
            dbobj.close();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
}

