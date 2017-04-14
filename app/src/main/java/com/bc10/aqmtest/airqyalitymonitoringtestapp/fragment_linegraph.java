package com.bc10.aqmtest.airqyalitymonitoringtestapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class fragment_linegraph extends android.support.v4.app.Fragment{

    LineChart chart1;
    LineChart chart2;
    LineChart chart3;
    LineChart chart4;
    LineChart chart5;
    LineChart chart6;
    LineChart chart7;
    LineChart chart8;
    LineChart chart9;
    LineChart chart10;

    SQLiteDatabase dbobj = null;

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

    Float float1;
    Float float2;
    Float float3;
    Float float4;
    Float float5;
    Float float6;
    Float float7;
    Float float8;
    Float float9;
    Float float10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_linegraph, container, false);

        chart1 = (LineChart)view.findViewById(R.id.chart1);
        chart2 = (LineChart)view.findViewById(R.id.chart2);
        chart3 = (LineChart)view.findViewById(R.id.chart3);
        chart4 = (LineChart)view.findViewById(R.id.chart4);
        chart5 = (LineChart)view.findViewById(R.id.chart5);
        chart6 = (LineChart)view.findViewById(R.id.chart6);
        chart7 = (LineChart)view.findViewById(R.id.chart7);
        chart8 = (LineChart)view.findViewById(R.id.chart8);
        chart9 = (LineChart)view.findViewById(R.id.chart9);
        chart10 = (LineChart)view.findViewById(R.id.chart10);

        displaygraph();
        return view;
    }

    public void displaygraph(){
        String path = "/data/data/com.bc10.aqmtest.airqyalitymonitoringtestapp/databases/Main_Database";
        dbobj = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        if(dbobj != null) {

            Cursor c = dbobj.rawQuery("SELECT * FROM myTable", null);

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

            c.moveToFirst();

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
            int i = 0;

            ArrayList<Entry> entry1 = new ArrayList<>();
            ArrayList<Entry> entry2 = new ArrayList<>();
            ArrayList<Entry> entry3 = new ArrayList<>();
            ArrayList<Entry> entry4 = new ArrayList<>();
            ArrayList<Entry> entry5 = new ArrayList<>();
            ArrayList<Entry> entry6 = new ArrayList<>();
            ArrayList<Entry> entry7 = new ArrayList<>();
            ArrayList<Entry> entry8 = new ArrayList<>();
            ArrayList<Entry> entry9 = new ArrayList<>();
            ArrayList<Entry> entry10 = new ArrayList<>();

            ArrayList<String> xlabels = new ArrayList<String>();

            if (c.getCount() > 0) {
                do {
                    str1 = c.getString(column1);
                    str2 = c.getString(column2);
                    str3 = c.getString(column3);
                    str4 = c.getString(column4);
                    str5 = c.getString(column5);
                    str6 = c.getString(column6);
                    str7 = c.getString(column7);
                    str8 = c.getString(column8);
                    str9 = c.getString(column9);
                    str10 = c.getString(column10);

                    float1 = Float.parseFloat(str1);
                    float2 = Float.parseFloat(str2);
                    float3 = Float.parseFloat(str3);
                    float4 = Float.parseFloat(str4);
                    float5 = Float.parseFloat(str5);
                    float6 = Float.parseFloat(str6);
                    float7 = Float.parseFloat(str7);
                    float8 = Float.parseFloat(str8);
                    float9 = Float.parseFloat(str9);
                    float10 = Float.parseFloat(str10);

                    entry1.add(new Entry(float1, i));
                    entry2.add(new Entry(float2, i));
                    entry3.add(new Entry(float3, i));
                    entry4.add(new Entry(float4, i));
                    entry5.add(new Entry(float5, i));
                    entry6.add(new Entry(float6, i));
                    entry7.add(new Entry(float7, i));
                    entry8.add(new Entry(float8, i));
                    entry9.add(new Entry(float9, i));
                    entry10.add(new Entry(float10, i));

                    xlabels.add(Integer.toString(i));
                    i++;
                } while (c.moveToNext());
            }
            dbobj.close();

            LineDataSet dataset1 = new LineDataSet(entry1, "Y-labels");
            LineDataSet dataset2 = new LineDataSet(entry2, "Y-labels");
            LineDataSet dataset3 = new LineDataSet(entry3, "Y-labels");
            LineDataSet dataset4 = new LineDataSet(entry4, "Y-labels");
            LineDataSet dataset5 = new LineDataSet(entry5, "Y-labels");
            LineDataSet dataset6 = new LineDataSet(entry6, "Y-labels");
            LineDataSet dataset7 = new LineDataSet(entry7, "Y-labels");
            LineDataSet dataset8 = new LineDataSet(entry8, "Y-labels");
            LineDataSet dataset9 = new LineDataSet(entry9, "Y-labels");
            LineDataSet dataset10 = new LineDataSet(entry10, "Y-labels");

            dataset1.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset2.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset3.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset4.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset5.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset6.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset7.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset8.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset9.setColors(ColorTemplate.COLORFUL_COLORS);
            dataset10.setColors(ColorTemplate.COLORFUL_COLORS);

            dataset1.setDrawFilled(true);
            dataset2.setDrawFilled(true);
            dataset3.setDrawFilled(true);
            dataset4.setDrawFilled(true);
            dataset5.setDrawFilled(true);
            dataset6.setDrawFilled(true);
            dataset7.setDrawFilled(true);
            dataset8.setDrawFilled(true);
            dataset9.setDrawFilled(true);
            dataset10.setDrawFilled(true);

            LineData data1 = new LineData(xlabels, dataset1);
            LineData data2 = new LineData(xlabels, dataset2);
            LineData data3 = new LineData(xlabels, dataset3);
            LineData data4 = new LineData(xlabels, dataset4);
            LineData data5 = new LineData(xlabels, dataset5);
            LineData data6 = new LineData(xlabels, dataset6);
            LineData data7 = new LineData(xlabels, dataset7);
            LineData data8 = new LineData(xlabels, dataset8);
            LineData data9 = new LineData(xlabels, dataset9);
            LineData data10 = new LineData(xlabels, dataset10);

            chart1.setData(data1);
            chart2.setData(data2);
            chart3.setData(data3);
            chart4.setData(data4);
            chart5.setData(data5);
            chart6.setData(data6);
            chart7.setData(data7);
            chart8.setData(data8);
            chart9.setData(data9);
            chart10.setData(data10);

            chart1.animateY(2000);
            chart2.animateY(2000);
            chart3.animateY(2000);
            chart4.animateY(2000);
            chart5.animateY(2000);
            chart6.animateY(2000);
            chart7.animateY(2000);
            chart8.animateY(2000);
            chart9.animateY(2000);
            chart10.animateY(2000);

            chart1.setDescription("");
            chart2.setDescription("");
            chart3.setDescription("");
            chart4.setDescription("");
            chart5.setDescription("");
            chart6.setDescription("");
            chart7.setDescription("");
            chart8.setDescription("");
            chart9.setDescription("");
            chart10.setDescription("");

        }
    }
}

