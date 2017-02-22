package project.examination.com.examination;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class DetailScoe extends Activity {

	private Databasepointin data;


	 public void onCreate(Bundle savedInstanceState){

	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.listview_scoll);
		 data=new Databasepointin(this);

		 String[] SelectData= data.SelectDataCode(File_Confix_Data.Code);


	  TextView detail1 = (TextView) findViewById(R.id.textView1_view);
		 detail1.setText(SelectData[2]+" : "+SelectData[3]);
		 TextView detail2 = (TextView) findViewById(R.id.detailquit);
		 detail2.setText(SelectData[1]+"\n\n  รหัส "+SelectData[6]);

		 TextView detail3 = (TextView) findViewById(R.id.detail);

		 detail3.setText("ถูกต้อง :" +SelectData[4]+"  ผิด :"+SelectData[7] +"  ไม่ได้ทำ  : "+ SelectData[8]+"    \n\n"
		 +"" +SelectData[9]
				 +"\n\nวันที่ " +SelectData[5]);


	 }


	}