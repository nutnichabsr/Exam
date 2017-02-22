package project.examination.com.examination;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class MainActivity_Register extends AppCompatActivity {
    DatabasepoinRegister databaseregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseregister=new DatabasepoinRegister(this);
        ArrayList<HashMap<String, String>> Selec=databaseregister.SelectAllData();

                if(Selec.size()>0){

                    File_Confix_Data.student_code=Selec.get(0).get("col1");
                    Load_ListUser(Selec.get(0).get("col1"));

                }else{

                    LayoutInflater layoutInflaterAndroid = LayoutInflater.from(MainActivity_Register.this);
                    View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box, null);
                    AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder( MainActivity_Register.this);
                    alertDialogBuilderUserInput.setView(mView);

                    final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                    alertDialogBuilderUserInput
                            .setCancelable(false)
                            .setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogBox, int id) {
                                    // ToDo get user input here
                                    databaseregister.InsertData(null,userInputDialogEditText.getText().toString(),"","","","","","","","");
                                    Load_ListUser(userInputDialogEditText.getText().toString());

                                }
                            })

                            .setNegativeButton("ยกเลิก",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogBox, int id) {
                                            dialogBox.cancel();
                                        }
                                    });

                    AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                    alertDialogAndroid.show();
                }

    }

    private void Load_ListUser(String StudentCode) {





        JSONObject joS = new JSONObject();
        try {
            joS.put("StudentCode", StudentCode);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        RequestParams params = new RequestParams("request", joS);


        Log.d("Getpayment",""+params);


        AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
        client.setTimeout(12000);
        client.post(getApplicationContext(), "http://172.27.148.123/EXAMINATION-BACKEND-RESOURCE/SOURCECODE/api/getStudentData", params, new AsyncHttpResponseHandler(){
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  byte[] response) {

                String str = null;
                try {
                    str = new String(response, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Log.d("Getpayment",str);
                SharedPreferences settings = getSharedPreferences("DATABASEUSER", 0);
                SharedPreferences.Editor prefsEd = settings.edit();
                prefsEd.putString("databaseuser", str);
                prefsEd.commit();

                Load_intent() ;

            }




            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {


                Log.d("Getpayment","onFailure");

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity_Register.this);
                alertDialogBuilder.setMessage("รหัสนี้ไม่มีในระบบ  ");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        arg0.dismiss();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }

        });
    }
    private void Load_intent() {

        Gson gson = new Gson();

        SharedPreferences database_lis_user = getSharedPreferences("DATABASEUSER", 0);
        String str_list_user = database_lis_user.getString("databaseuser", "");


        GetStudentData userGetStudentData =   gson.fromJson(str_list_user, GetStudentData.class);
        File_Confix_Data.getGetStudentData= userGetStudentData.getOutput();



        Intent intent = new Intent(MainActivity_Register.this, MainActivity.class);
        startActivity(intent);
    }
}
