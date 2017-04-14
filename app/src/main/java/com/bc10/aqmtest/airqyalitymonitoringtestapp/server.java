package com.bc10.aqmtest.airqyalitymonitoringtestapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class server extends AppCompatActivity {

    SQLiteDatabase mydb = null;
    private static server insta;
    private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();

    private static String url_all_products = "http://www.air-quality-iitbbs.co.nf/air.php";

    JSONArray products = null;
    private GoogleApiClient client;



    public static server instance(){
        return insta;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_layout);
        if (isNetworkAvailable())
            new LoadAllProducts().execute();
        else {
            Toast.makeText(this, "Network Unavailable", Toast.LENGTH_LONG).show();
            //Intent main_intent = new Intent(server.this, MainActivity.class);
            //startActivity(main_intent);
        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    @Override
    public void onStart() {
        super.onStart();
        insta =this;

        client.connect();
        Action viewAction = Action.newAction(Action.TYPE_VIEW, "Main Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://com.bc10.aqmtest.airqyalitymonitoringtestapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }



    @Override
    public void onStop() {
        super.onStop();
        Action viewAction = Action.newAction(Action.TYPE_VIEW, "Main Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://com.bc10.aqmtest.airqyalitymonitoringtestapp/http/host/path")
        );

        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }



    class LoadAllProducts extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(server.this);
            pDialog.setMessage("Loading data. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        /////////////////////////getting All products from url//////////////////////////////////////
        @Override
        protected String doInBackground(String... args) {

            HashMap<String, String> params = new HashMap<>();

            Log.d("request", "starting");

            JSONObject json;
            json = jParser.makeHttpRequest(url_all_products, "GET", params);

            Log.d("All Products: ", json.toString());

            try {
                createdb();
                products = json.getJSONArray("sensor_data");

                // looping through All Products
                for (int i = 0; i < products.length(); i++) {
                    JSONObject c = products.getJSONObject(i);

                    // Storing each json item in variable
                    int _id = c.getInt("serial_no");
                    String dateT = c.getString("DateTime");
                    String sensor1 = c.getString("Temp");
                    String sensor2 = c.getString("Dust");
                    String sensor3 = c.getString("RH");
                    String sensor4 = c.getString("Press");
                    String sensor5 = c.getString("Alt");
                    String sensor6 = c.getString("Meth");
                    String sensor7 = c.getString("O3");
                    String sensor8 = c.getString("CO");
                    String sensor9 = c.getString("H2S");
                    String sensor10 = c.getString("NH3");

                    String date = dateT.substring(0,10);
                    String time = dateT.substring(11);

                    insertdata(_id, date, time, sensor1, sensor2, sensor3,
                            sensor4, sensor5, sensor6, sensor7, sensor8,
                            sensor9, sensor10);

                }

                mydb.close();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        //////////After completing background task Dismiss the progress dialog/////////////////////
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            //pDialog.dismiss();
            Intent main_intent = new Intent(server.this, MainActivity.class);
            startActivity(main_intent);
        }
    }




    public void createdb(){
        mydb = this.openOrCreateDatabase("Main_Database", MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE IF NOT EXISTS myTable (id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR, time VARCHAR, temp VARCHAR, dustDensity VARCHAR, humidity VARCHAR, pressure VARCHAR, altitude VARCHAR, methane VARCHAR, O3 VARCHAR, CO VARCHAR, H2S VARCHAR, NH3 VARCHAR);");
    }


    public void insertdata(int _id, String date0, String time0, String input1,
                           String input2, String input3, String input4,
                           String input5, String input6, String input7,
                           String input8, String input9, String input10){
        Cursor cur = mydb.rawQuery("SELECT * FROM myTable", null);
        int colum = cur.getColumnIndex("id");
        cur.moveToLast();

        if(cur.getCount() <= 0) {
            mydb.execSQL("INSERT INTO myTable (date, time, temp, dustDensity, humidity, pressure, altitude, methane, O3, CO, H2S, NH3) VALUES ('" + date0 + "', '" + time0 + "', '" + input1 + "', '" + input2 + "', '" + input3 + "', '" + input4 + "', '" + input5 + "', '" + input6 + "', '" + input7 + "', '" + input8 + "', '" + input9 + "', '" + input10 + "');" );
        }

        if(cur.getCount() > 0) {
            int lastid = cur.getInt(colum);
            if (lastid < _id) {
                mydb.execSQL("INSERT INTO myTable (date, time, temp, dustDensity, humidity, pressure, altitude, methane, O3, CO, H2S, NH3) VALUES ('" + date0 + "', '" + time0 + "', '" + input1 + "', '" + input2 + "', '" + input3 + "', '" + input4 + "', '" + input5 + "', '" + input6 + "', '" + input7 + "', '" + input8 + "', '" + input9 + "', '" + input10 + "');" );
            }
        }
    }


}
