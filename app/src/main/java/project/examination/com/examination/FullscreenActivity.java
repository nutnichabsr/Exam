package project.examination.com.examination;//package com.ServiceDesk.ServiceDesk;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;


public class FullscreenActivity extends AppCompatActivity {
 boolean check_load=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);


    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();


        Load_data();
    }

    private void Load_data() {
        // TODO Auto-generated method stub

        if (isOnline()) {

            Load_getDepartment();

        } else {
            intent_activity();

        }

    }

    private void intent_activity() {


        Gson gson = new Gson();




        SharedPreferences database_lis_getDepartment = getSharedPreferences("DATABASEDEPARTMENT", 0);
        String str_list_getDepartment = database_lis_getDepartment.getString("getDepartment", "");



        GetDepartment user_getDepartment =   gson.fromJson(str_list_getDepartment, GetDepartment.class);
        File_Confix_Data.getGetDepartment= user_getDepartment.getOutput();








           int run=0;
        if(check_load){
            run=1000;
        }else{
            run=2000;
        }

        final int finalRun = run;
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(finalRun);
                } catch (InterruptedException e) { }

                Intent intent = new Intent(FullscreenActivity.this, MainActivity_Register.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }

//http://examination.strokenu.com/api/getDepartment


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }




    private void Load_getDepartment() {
        AsyncHttpClient client = new AsyncHttpClient();
        //http://172.27.148.123/EXAMINATION-BACKEND-RESOURCE/SOURCECODE
        client.get("http://172.27.148.123/EXAMINATION-BACKEND-RESOURCE/SOURCECODE/api/getDepartment", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                String str = null;
                try {
                    str = new String(response, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                SharedPreferences settings = getSharedPreferences("DATABASEDEPARTMENT", 0);
                SharedPreferences.Editor prefsEd = settings.edit();
                prefsEd.putString("getDepartment", str);
                prefsEd.commit();

                intent_activity();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried http://projectandroid.top/Travel
            }
        });
    }
}
