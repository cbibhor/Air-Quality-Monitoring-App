package com.bc10.aqmtest.airqyalitymonitoringtestapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class DataFromSms extends AppCompatActivity {

    private static DataFromSms inst;
    SQLiteDatabase mydb = null;

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


    public static DataFromSms instance(){
        return inst;
    }


    @Override
    protected void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //////////////////////////////////////msgdata receives extracted data///////////////////////////
    public void msgdata(String date, String time, String txt1,
                        String txt2, String txt3, String txt4,
                        String txt5, String txt6, String txt7,
                        String txt8, String txt9, String txt10)
    {
        set0 += "\n" + date + "\n";
        set00 += "\n" + time + "\n";
        set1 += "\n" + txt1 + "\n";
        set2 += "\n" + txt2 + "\n";
        set3 += "\n" + txt3 + "\n";
        set4 += "\n" + txt4 + "\n";
        set5 += "\n" + txt5 + "\n";
        set6 += "\n" + txt6 + "\n";
        set7 += "\n" + txt7 + "\n";
        set8 += "\n" + txt8 + "\n";
        set9 += "\n" + txt9 + "\n";
        set10 += "\n" + txt10 + "\n";

        data0 = (TextView)findViewById(R.id.data0);
        data00 = (TextView)findViewById(R.id.data00);
        data1 = (TextView)findViewById(R.id.data1);
        data2 = (TextView)findViewById(R.id.data2);
        data3 = (TextView)findViewById(R.id.data3);
        data4 = (TextView)findViewById(R.id.data4);
        data5 = (TextView)findViewById(R.id.data5);
        data6 = (TextView)findViewById(R.id.data6);
        data7 = (TextView)findViewById(R.id.data7);
        data8 = (TextView)findViewById(R.id.data8);
        data9 = (TextView)findViewById(R.id.data9);
        data10 = (TextView)findViewById(R.id.data10);

        data0.setText(set0);
        data00.setText(set00);
        data1.setText(set1);
        data2.setText(set2);
        data3.setText(set3);
        data4.setText(set4);
        data5.setText(set5);
        data6.setText("-");
        data7.setText("-");
        data8.setText(set8);
        data9.setText("-");
        data10.setText("-");

        insertdata(date,time,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10);
    }

    public void insertdata(String date0, String time0, String input1,
                           String input2, String input3, String input4,
                           String input5, String input6, String input7,
                           String input8, String input9, String input10){

        mydb = this.openOrCreateDatabase("Main_Database", MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS myTable (id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR, time VARCHAR, temp VARCHAR, dustDensity VARCHAR, humidity VARCHAR, pressure VARCHAR, altitude VARCHAR, methane VARCHAR, O3 VARCHAR, CO VARCHAR, H2S VARCHAR, NH3 VARCHAR);");

        mydb.execSQL("INSERT INTO myTable (date, time, temp, dustDensity, humidity, pressure, altitude, methane, O3, CO, H2S, NH3) VALUES ('" + date0 + "', '" + time0 + "', '" + input1 + "', '" + input2 + "', '" + input3 + "', '" + input4 + "', '" + input5 + "', '" + input6 + "', '" + input7 + "', '" + input8 + "', '" + input9 + "', '" + input10 + "');" );

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

}

