package project.examination.com.examination;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import cz.msebera.android.httpclient.Header;


public class Quiz_test_in extends Activity {
	private MediaPlayer mediaPlayer ;
	boolean checkDTimeMediaPlayer=true;
	public static final int REQUEST_GALLERY = 1;
	 private TextView timeandroid,lang_test;
	private long startTime = 0L;
	private Handler customHandler = new Handler();
	private long timeInMilliseconds = 0L;
	private long timeSwapBuff = 0L;
	private long updatedTime = 0L;
	private String strDates ;
 
	
	private String[] text ;
	private String[] text1 ;
	private String[] text2 ;
	private String[] text3 ;
	private String[] text4 ;

 
	private String [] imagechio;
	

	private int nextQuestion=0;
	
	
	int [] myStringArrayAnswer  ;
	int [] myStringArrayAnswerCheckOn  ;
	boolean checkRadioGroup=true;
	boolean checkRadio =true;

	boolean checkRadioAng =true;
	private Databasepointin data;

	private int[] answer;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quizintest);
	//	department_branch
		  data=new Databasepointin(this);
		  
		text = new String[File_Confix_Data.getOutput_data_List.size() ];
		text1 = new String[File_Confix_Data.getOutput_data_List.size()];
		text2 = new String[File_Confix_Data.getOutput_data_List.size()];
		text3 = new String[File_Confix_Data.getOutput_data_List.size()];
		text4 = new String[File_Confix_Data.getOutput_data_List.size()];
		answer= new int[File_Confix_Data.getOutput_data_List.size()];
		imagechio=new String[File_Confix_Data.getOutput_data_List.size()];

		for(int i=0;i<File_Confix_Data.getOutput_data_List.size();i++){
			text[i]=File_Confix_Data.getOutput_data_List.get(i).getQuestion();
			imagechio[i]=File_Confix_Data.getOutput_data_List.get(i).getImage();

			text1[i]=File_Confix_Data.getOutput_data_List.get(i).getAnswer().get(0).getAnswer();
			text2[i]=File_Confix_Data.getOutput_data_List.get(i).getAnswer().get(1).getAnswer();
			text3[i]=File_Confix_Data.getOutput_data_List.get(i).getAnswer().get(2).getAnswer();
			text4[i]=File_Confix_Data.getOutput_data_List.get(i).getAnswer().get(3).getAnswer();

			for(int j=0;j<File_Confix_Data.getOutput_data_List.get(i).getAnswer().size();j++){
				int dddd=j+1;
				if(File_Confix_Data.getOutput_data_List.get(i).getAnswer().get(j).getIs_correct()==1){
					answer[i]=dddd;
				}
			}

		}

		 mediaPlayer = MediaPlayer.create(Quiz_test_in.this, R.raw.mp3);
		 
		setWitget();


		title.setText(File_Confix_Data.getOutput_data_List.get(0).getDepartment());
		lang_test.setText(File_Confix_Data.getOutput_data_List.size()+"ข้อ");
		//random

		myStringArrayAnswer = new int[text.length];
		myStringArrayAnswerCheckOn= new int[text.length];
		for(int g=0;g<myStringArrayAnswer.length;g++){
			myStringArrayAnswer[g]=0;
			myStringArrayAnswerCheckOn[g]=0;
		}
 
		setQuestionView();
 
		rda.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(checkRadioAng){
					myStringArrayAnswer [nextQuestion]=1;
					rda.setChecked(true);
					rdb.setChecked(false);
					rdc.setChecked(false);
					rdd.setChecked(false);

					checkRadioAng=false;
				}else{
					myStringArrayAnswer[nextQuestion]=0;
					rda.setChecked(false);
					checkRadioAng=true;
				}

			}
		});
		
		rdb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(checkRadioAng){
					myStringArrayAnswer [nextQuestion]=2;
					rda.setChecked(false);
					rdb.setChecked(true);
					rdc.setChecked(false);
					rdd.setChecked(false);

					checkRadioAng=false;
				}else{
					myStringArrayAnswer[nextQuestion]=0;
					rdb.setChecked(false);
					checkRadioAng=true;
				}
				
				 
			}
		});
		rdc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(checkRadioAng){
					myStringArrayAnswer [nextQuestion]=3;
					rda.setChecked(false);
					rdb.setChecked(false);
					rdc.setChecked(true);
					rdd.setChecked(false);

					checkRadioAng=false;
				}else{
					myStringArrayAnswer[nextQuestion]=0;
					rdc.setChecked(false);
					checkRadioAng=true;
				}
				
				 
			}
		});
		rdd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(checkRadioAng){
					myStringArrayAnswer [nextQuestion]=4;
					rda.setChecked(false);
					rdb.setChecked(false);
					rdc.setChecked(false);
					rdd.setChecked(true);

					checkRadioAng=false;
				}else{
					myStringArrayAnswer[nextQuestion]=0;
					rdd.setChecked(false);
					checkRadioAng=true;
				}
				
				 
			}
		});

		
		btnNEXT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
 
				if(nextQuestion<text.length-1){					
					//currentQ=quesList.get(qid);
					nextQuestion++;
					setQuestionView();
				}else{
					 
						Toast.makeText(Quiz_test_in.this,"ข้อสุดท้าย" , Toast.LENGTH_LONG).show();
					  
					
				}
			}
		});
		
	
		
		btnBACK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
	 
				if(nextQuestion>0){					
					//currentQ=quesList.get(qid);
					nextQuestion--;
					setQuestionView();
				}else{
					 
						Toast.makeText(Quiz_test_in.this,"ข้อแรก" , Toast.LENGTH_LONG).show();
				  
					
				}
			}
		});
		
		senddataTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			       AlertDialog.Builder dialog = new AlertDialog.Builder(Quiz_test_in.this);
			        dialog.setTitle("ส่งคำตอบ");
			        dialog.setCancelable(true);
			        dialog.setMessage("คุณต้องการส่งคำตอบ หรือ ไม่?");
			        dialog.setPositiveButton("ตกลง", new OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			            	Toast.makeText(Quiz_test_in.this,"ส่งคำตอบเรียบร้อย" , Toast.LENGTH_LONG).show();

			            	//Confixe_Settingdata.nameLoginCode;

			        		int checkTrueang[]=answer;
			        		int[]  ArrayAnswer = new int[checkTrueang.length];

			        		int Answer1=0;
			        		int Answer2=0;
			        		int Answer3=0;
			        		for(int f=0;f<checkTrueang.length;f++){
			        			if(myStringArrayAnswer[f]==checkTrueang[f]){
			        				ArrayAnswer[f]=1;
			        				Answer1++;
			        			}else if(myStringArrayAnswer[f]==0){
			        				ArrayAnswer[f]=2;
			        				Answer2++;
			        			}else{
			        				ArrayAnswer[f]=3;
			        				Answer3++;
			        			}
			        		}


			        		Confixe_SettingdataIn_test.ArrayAnsweranswer=ArrayAnswer;
			        		 Confixe_SettingdataIn_test.TimeApp=timeandroid.getText().toString();
			        		Calendar calendar = Calendar.getInstance();
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
							final String strDate = simpleDateFormat.format(calendar.getTime());
//							"id":11,
//									"code":"STD0001",
//									"email":null,
//									"title":"Master",
//									"firstname":"AAA",
//									"lastname":"BBB",
//									"level":1,
//									"room":1,
//									"number":1,
//									"password":"",
//									"access_type":"student",
//									"status":1





							data.InsertData(null,File_Confix_Data.getGetStudentData.get(0).getTitle()+" "+ File_Confix_Data.getGetStudentData.get(0).getFirstname()+" "
									+ File_Confix_Data.getGetStudentData.get(0).getLastname(),  File_Confix_Data.getOutput_data_List.get(0).getDepartment()
									,  File_Confix_Data.getOutput_data_List.get(0).getDepartment_branch(), ""+Answer1, strDate, File_Confix_Data.getGetStudentData.get(0).getCode(), " "+Answer3, " "+Answer2
									, "เวลาทำ "+Confixe_SettingdataIn_test.TimeApp+" นาที");

							Toast.makeText(Quiz_test_in.this,"ถูกต้อง :" +Answer1+" ผิด :"+Answer3 +" ไม่ได้ทำ  : "+ Answer2+"    "   , Toast.LENGTH_LONG).show();


							Load_GetExamination(Answer1,Answer3,Answer2);
			            }
			        });
			        
			        dialog.setNegativeButton("ยกเลิก", new OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			            	dialog.cancel();
			            	dialog.dismiss();	 
			            }
			        });
			        
			        dialog.show();   
			}
		});
		 
	 
		onChoi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(checkRadio){
					myStringArrayAnswerCheckOn[nextQuestion]=1;
					onChoi.setChecked(true);
					checkRadio=false;
				}else{
					myStringArrayAnswerCheckOn[nextQuestion]=0;
					onChoi.setChecked(false);
					checkRadio=true;
				}
				
				 
			}
		});
	}



	private void Load_GetExamination(int Answer1,int Answer3,int Answer2) {


//							http://examination.strokenu.com/api/sendExamination
//
//							request[StudentCode] = "";
//							request[ExaminationLevel] = ""; //id ของระดับความยากง่าย
//							request[DepartmentId] = ""; // id ของวิชา
//							request[DepartmentBranchId] = ""; // id ของแขนงวิชา
//							request[TotalCorrect] = ""; //จำนวนที่ทำถูก
//							request[TotalIncorrect] = ""; // จำนวนที่ทำผิด
//							request[TotalDidnot] = ""; //จำนวนที่ไม่ได้ทำ


		JSONObject joS = new JSONObject();
		try {
			joS.put("StudentCode", "STD0001");
			joS.put("ExaminationLevel", "1");
			joS.put("DepartmentId", File_Confix_Data.DepartmentId);
			joS.put("DepartmentBranchId", File_Confix_Data.DepartmentBranchId);

			joS.put("TotalCorrect",Answer1);
			joS.put("TotalIncorrect", Answer3);
			joS.put("TotalDidnot", Answer2);




		} catch (JSONException e) {
			e.printStackTrace();
		}



		RequestParams params = new RequestParams("request", joS);


		Log.d("Getpayment",""+params);


		AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
		client.setTimeout(12000);
		client.post(getApplicationContext(), "http://172.27.148.123/EXAMINATION-BACKEND-RESOURCE/SOURCECODE/api/sendExamination", params, new AsyncHttpResponseHandler(){
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
                Debug_Examination.out("Getpayment "+str);
				Intent i=new Intent(Quiz_test_in.this,DetailQuit.class);
				startActivity(i);
			}



			@Override
			public void onFailure(int statusCode, Header[] headers,
								  byte[] errorResponse, Throwable e) {


				Log.d("Getpayment","onFailure");



			}

		});
	}

	private void setQuestionView(){
		  
	        int jj=nextQuestion+1;
		textchoitype.setText("ข้อที่. "+jj+"  "+text[ nextQuestion ]);
		rda.setText(text1[ nextQuestion ]);
		rdb.setText(text2[ nextQuestion ]);
		rdc.setText(text3[ nextQuestion ]);
		rdd.setText(text4[ nextQuestion ]);

		
		textchoitype.setTextColor(Color.BLACK);
		rda.setTextColor(Color.BLACK);
		rdb.setTextColor(Color.BLACK);
		rdc.setTextColor(Color.BLACK);
		rdd.setTextColor(Color.BLACK);


		Glide.with(getApplicationContext()).load(imagechio[ nextQuestion ]).into(setimageviewchoi);



		if(myStringArrayAnswer[nextQuestion]==1) {
			//Toast.makeText(TestClass.this,nextQuestion+" "+myStringArrayAnswer[nextQuestion] , Toast.LENGTH_LONG).show();
			rda.setChecked(true);
			rdb.setChecked(false);
    		rdc.setChecked(false);
    		rdd.setChecked(false);

        } else if(myStringArrayAnswer[nextQuestion]==2) {
        	//Toast.makeText(TestClass.this,nextQuestion+" "+myStringArrayAnswer[nextQuestion] , Toast.LENGTH_LONG).show();
        	rdb.setChecked(true);
        	rda.setChecked(false);
    		 
    		rdc.setChecked(false);
    		rdd.setChecked(false);

        }else if(myStringArrayAnswer[nextQuestion]==3) {
        	//Toast.makeText(TestClass.this,nextQuestion+" "+myStringArrayAnswer[nextQuestion] , Toast.LENGTH_LONG).show();
        	rdc.setChecked(true);
        	rda.setChecked(false);
    		rdb.setChecked(false);
    		 
    		rdd.setChecked(false);

        }else if(myStringArrayAnswer[nextQuestion]==4) {
        	//Toast.makeText(TestClass.this,nextQuestion+" "+myStringArrayAnswer[nextQuestion] , Toast.LENGTH_LONG).show();
        	rdd.setChecked(true);
        	rda.setChecked(false);
    		rdb.setChecked(false);
    		rdc.setChecked(false);
    		 

        }else if(myStringArrayAnswer[nextQuestion]==5) {
        	//Toast.makeText(TestClass.this,nextQuestion+" "+myStringArrayAnswer[nextQuestion] , Toast.LENGTH_LONG).show();

        	rda.setChecked(false);
    		rdb.setChecked(false);
    		rdc.setChecked(false);
    		rdd.setChecked(false);
    	 
        }else if(myStringArrayAnswer[nextQuestion]==0)  { 
        	rda.setChecked(false);
    		rdb.setChecked(false);
    		rdc.setChecked(false);
    		rdd.setChecked(false);

        }else    { 
        	 
        }
		if(myStringArrayAnswerCheckOn[nextQuestion]==1 ){
			onChoi.setChecked(true);
		}else{
			onChoi.setChecked(false);
		}
		
	 
	}
	private void setWitget() {
		// TODO Auto-generated method stub
		 timeandroid= (TextView)findViewById(R.id.timeandroid);
		title=(Button)findViewById(R.id.button18);
		lang_test=(TextView)findViewById(R.id.lang_test);

		btnBACK=(Button)findViewById(R.id.btnBACK);
		btnNEXT=(Button)findViewById(R.id.btnNEXT);
		
		senddataTest=(Button)findViewById(R.id.senddataTest);
		
		
		textchoitype=(TextView)findViewById(R.id.textchoitype);
		
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);
		rdc=(RadioButton)findViewById(R.id.radio2);
		rdd=(RadioButton)findViewById(R.id.radio3);

		
		 
		
		//onChoi
		onChoi=(RadioButton)findViewById(R.id.ondata);
	
		setimageviewchoi=(ImageView)findViewById(R.id.setimageview);
		
		 
		Calendar calendars = Calendar.getInstance();
		SimpleDateFormat simpleDateFormats = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
		 strDates = simpleDateFormats.format(calendars.getTime());
		startTime = SystemClock.uptimeMillis();
		customHandler.postDelayed(updateTimerThread, 0);
	}
	

	private ImageView setimageviewchoi;
	private Button title,btnBACK,btnNEXT,senddataTest;
	private TextView textchoitype;
	private RadioButton rda, rdb, rdc, rdd  ,onChoi;

	public int[] randomIntArray(int count, int min, int max) {
		Random r = new Random();
		int[] data = new int[count];
		for(int i = 0 ; i < count ; i++) 
			data[i] = -1;
		for(int i = 0 ; i < count ; i++) {
			int n = -1;
	    	boolean st = true; 
			while(st) {
				st = false;
				n = r.nextInt((max - min) + 1) + min;
				for(int j = 0 ; j < data.length ; j++) 
					if(n == data[j]) 
						st = true;
			}
			data[i] = n;
		}
		return data;
	}
	private Runnable updateTimerThread = new Runnable() {
		public void run() {
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			updatedTime = timeSwapBuff + timeInMilliseconds;
			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			secs = secs % 60;
			int mex =mins/ 60;
			int milliseconds = (int) (updatedTime % 1000);
		 	timeandroid.setText(""+mex+":" + mins + ":"+ String.format("%02d", secs) + ":"+ String.format("%03d", milliseconds));
		
			if(mins==165 &&checkDTimeMediaPlayer){
				checkDTimeMediaPlayer=false;
 			 Paymedia();

			}
			customHandler.postDelayed(this, 0);
			
		}
	};
	
    private void Paymedia() {
		// TODO Auto-generated method stub
 		 mediaPlayer.start();

	}
	@Override
	public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Quiz_test_in.this);
        dialog.setTitle("คุณต้องการเลิกไหม");
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to exit?");
        dialog.setPositiveButton("ตกลง", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	
            	Toast.makeText(Quiz_test_in.this,"Quiz fial "+" No save data  " , Toast.LENGTH_LONG).show();
            	Quiz_test_in.this.finish();

            }
        });
        
        dialog.setNegativeButton("ยกเลิก", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	dialog.cancel();
            	dialog.dismiss();	 
            }
        });
        
        dialog.show();   
		
	}
}