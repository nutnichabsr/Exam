package project.examination.com.examination;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;


public class Select_Data_list extends Activity {
	DatabasepoinRegister databaseregist;
	ListView listview;
	 public void onCreate(Bundle savedInstanceState){

	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.main_searchs);
		 databaseregist=new DatabasepoinRegister(this);


	  TextView textView1sss = (TextView) findViewById(R.id.textView1_view);
	  textView1sss.setText(File_Confix_Data.getGetDepartment.get(File_Confix_Data.potition).getDepartment_name());
	 
	  //edittext = (EditText) findViewById(R.id.EditText01);
	    listview = (ListView) findViewById(R.id.ListView01);
		 listview.setAdapter(new  CustomListViewAdapter( ));
	 }



	
		@Override
		public void onBackPressed() {

			 Select_Data_list.this.finish();

	    }
	public class CustomListViewAdapter extends BaseAdapter {

		public int getCount() {
			return File_Confix_Data.getGetDepartment.get(File_Confix_Data.potition).getDepartment_branch().size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_listview_listview, null);
				holder = new ViewHolder();
				holder.titleTextView = (TextView) convertView.findViewById(R.id.title);
				holder.descTextView = (TextView) convertView.findViewById(R.id.artistsss);
				holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
				holder.relativeLayoutthumbnail = (RelativeLayout) convertView.findViewById(R.id.relativeLayoutthumbnail);
				holder.authorImageView = (ImageView) convertView.findViewById(R.id.list_image);
				holder.relativeLayoutthumbnail.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {


						final String catID = v.getTag(R.id.relativeLayoutthumbnail).toString();
						Integer.parseInt(catID);
						for(int j=0;j<File_Confix_Data.getGetDepartment.get(File_Confix_Data.potition).getDepartment_branch().size();j++){

							if( File_Confix_Data.getGetDepartment.get(File_Confix_Data.potition).getDepartment_branch().get(j).getBranch_id()==Integer.parseInt(catID)){
								// File_Confix_Data.potition=  j;
								File_Confix_Data.DepartmentBranchId=File_Confix_Data.getGetDepartment.get(File_Confix_Data.potition).getDepartment_branch().get(j).getBranch_id();
							}
						}


						LayoutInflater layoutInflaterAndroid = LayoutInflater.from(Select_Data_list.this);
						View mView = layoutInflaterAndroid.inflate(R.layout.user_input_dialog_box_data, null);
						AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder( Select_Data_list.this);
						alertDialogBuilderUserInput.setView(mView);



						final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
						userInputDialogEditText.setText(File_Confix_Data.student_code);

						final EditText userInputDialogEditText1 = (EditText) mView.findViewById(R.id.userInputDialog1);
						alertDialogBuilderUserInput
								.setCancelable(false)
								.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialogBox, int id) {
										// ToDo get user input here


										Load_GetExamination(userInputDialogEditText.getText().toString()
											,	userInputDialogEditText1.getText().toString());
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
				});

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.titleTextView.setText( File_Confix_Data.getGetDepartment.get(File_Confix_Data.potition).getDepartment_branch().get(position).getBranch_name()   );
			holder.descTextView.setText(  File_Confix_Data.getGetDepartment.get(File_Confix_Data.potition).getDepartment_name());
			holder.textView1.setText(" " );




			holder.relativeLayoutthumbnail.setTag(R.id.relativeLayoutthumbnail, File_Confix_Data.getGetDepartment.get(File_Confix_Data.potition).getDepartment_branch().get(position).getBranch_id());

			return convertView;
		}
		public class ViewHolder {
			TextView titleTextView;
			ImageView authorImageView;
			TextView textView1;
			TextView descTextView;
			RelativeLayout relativeLayoutthumbnail;

		}
	}
	private void Load_GetExamination(String StudentCode,String ExaminationLevel) {
		double total = Double.parseDouble(ExaminationLevel );

		String qqqq="";
		if(total>0||total<2){
			qqqq="1";
		}else if(total >=2||total<3){
			qqqq="2";
		} else if(total >=3||total<=4){
			qqqq="3";
		}
		JSONObject joS = new JSONObject();
		try {
			joS.put("StudentCode", StudentCode);
			joS.put("ExaminationLevel", qqqq);
			joS.put("DepartmentId", File_Confix_Data.DepartmentId);
			joS.put("DepartmentBranchId", File_Confix_Data.DepartmentBranchId);




		} catch (JSONException e) {
			e.printStackTrace();
		}



		RequestParams params = new RequestParams("request", joS);


		Log.d("Getpayment",""+params);


		AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
		client.setTimeout(12000);
		client.post(getApplicationContext(), "http://172.27.148.123/EXAMINATION-BACKEND-RESOURCE/SOURCECODE/api/GetExamination", params, new AsyncHttpResponseHandler(){
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
				SharedPreferences settings = getSharedPreferences("DATABASELIST", 0);
				SharedPreferences.Editor prefsEd = settings.edit();
				prefsEd.putString("databaselist", str);
				prefsEd.commit();
				Load_Intent_Qui()  ;
			}




			@Override
			public void onFailure(int statusCode, Header[] headers,
								  byte[] errorResponse, Throwable e) {


				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Select_Data_list.this);
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

	private void Load_Intent_Qui() {

		Gson gson = new Gson();

		SharedPreferences database_lis  = getSharedPreferences("DATABASELIST", 0);
		String str_list = database_lis.getString("databaselist", "");


		GetExamination data_ =   gson.fromJson(str_list, GetExamination.class);
		File_Confix_Data.getOutput_data_List= data_.getOutput();
 if(File_Confix_Data.getOutput_data_List.size()>0){
	 Intent intent = new Intent(Select_Data_list.this, Quiz_test_in.class);
	 startActivity(intent);
 }else{
	 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Select_Data_list.this);
	 alertDialogBuilder.setMessage("ยังไม่มีข้อมูลข้อสอบ  ");
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

	}
	}