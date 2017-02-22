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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
public class ScorePrePointandroid extends Activity {
	 
	private Databasepointin data;
	 
	private ListView listview;
	 
     private String[] text  ;
	 private String[] text2  ;
	private String[] Code  ;
	 private int image[]={R.drawable.score} ;
	 
	 private ArrayList<String> text_sort = new ArrayList<String>();
	 private ArrayList<String> text_sort2 = new ArrayList<String>();

	 public void onCreate(Bundle savedInstanceState){

	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.main_searchs);
	  data=new Databasepointin(this);

		
	  TextView textView1sss = (TextView) findViewById(R.id.textView1_view);
	  textView1sss.setText("ประวัติการทำข้อสอบ");
	 
	  //edittext = (EditText) findViewById(R.id.EditText01);
	    listview = (ListView) findViewById(R.id.ListView01);
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
		 
				
			}
		});
	 }

	 class MyCustomAdapter extends BaseAdapter
	 {

	  String[] data_text;
	  String[] data_text2;
		 String[] data_Code;

	  MyCustomAdapter(){}

	  MyCustomAdapter(String[] text, String[] text2, String[] code){
		  
	   data_text = text;

	   data_text2 = text2;
		  data_Code=code;
	  }
	  
	  MyCustomAdapter(ArrayList<String> text, ArrayList<String> text2, ArrayList<String> code_s)
	  { 
	  
	   data_text = new String[text.size()];
	   data_text2 = new String[text2.size()];
		  data_Code = new String[code_s.size()];

	   for(int i=0;i<text.size();i++)
	   {
	    data_text[i] = text.get(i);
	    data_text2[i] = text2.get(i);
		   data_Code[i] = code_s.get(i);
	   }

	  }

	  public int getCount()
	  {
	   return data_text.length;
	  }

	  public String getItem(int position)
	  {
	   return null;
	  }

	  public long getItemId(int position)
	  {
	   return position;
	  }

	  public View getView(int position, View convertView, ViewGroup parent)
	  {

	   LayoutInflater inflater = getLayoutInflater();
	   View row;

	   row = inflater.inflate(R.layout.list_row_view, parent, false);
	   //mTypeface = Typeface.createFromAsset(getBaseContext().getAssets(),"fonts/TH_Mali_Grade6_Bold.ttf");
	   TextView textview1 = (TextView) row.findViewById(R.id.text_1);
	   TextView textview2 = (TextView) row.findViewById(R.id.text_2);
	   TextView textview3 = (TextView) row.findViewById(R.id.text_3);
		final   int position_s=position;
	   ImageView list_image = (ImageView) row.findViewById(R.id.list_image);
		  RelativeLayout  onclick_next = (RelativeLayout) row.findViewById(R.id.onclick_next);
		  onclick_next.setOnClickListener(new OnClickListener()
		  {
			  public void onClick(View v)
			  {
				  File_Confix_Data.Code=  Code[position_s];

				  Intent intent = new Intent(ScorePrePointandroid.this, DetailScoe.class);
				  startActivity(intent);
			  }
		  });
	   int number=position;
	   number++;
	   String strI = Integer.toString(number);
	   textview1.setText(strI+".");
	   
	   textview2.setText(data_text[position]);
	   //textview2.setSelected(true);
	   textview3.setText(data_text2[position]);
	  // textview3.setSelected(true);
	   list_image.setBackgroundResource(image[0]);




	   return (row);

	  }
	 }
 
		
		protected void onStart ( ){
		    super.onStart ( );
		    
		    ArrayList<HashMap<String, String>> selectAllData= data.SelectAllData();

			int count=0;

			for(int i=0;i<selectAllData.size();i++){
				if(File_Confix_Data.getGetStudentData.get(0).getCode().equals(selectAllData.get(i).get("col6"))){
					count++;

				}



			}
			text2=new String[count ];
			text=new String[count ];
			Code=new String[count ];
			int count_=0;
			for(int i=0;i<selectAllData.size();i++){
				if(File_Confix_Data.getGetStudentData.get(0).getCode().equals(selectAllData.get(i).get("col6"))){

					text2[count_]="ชื่อ :"+selectAllData.get(i).get("col1")+"  วิชา  :"+selectAllData.get(i).get("col2")+"  :"+selectAllData.get(i).get("col3")+ " เวลา  :"+selectAllData.get(i).get("col5");
					text[count_]="ถูกต้อง : "+selectAllData.get(i).get("col4")+"ข้อ "+" ผิด :"+selectAllData.get(i).get("col7")+"ข้อ "+"  ไม่แน่ใจ  :"+selectAllData.get(i).get("col8")+"ข้อ "
							 ;
					Code [count_]=selectAllData.get(i).get("Code");
					count_++;
				}
			}
	 	listview.setAdapter(new MyCustomAdapter(text,text2,Code));
		}

	
		@Override
		public void onBackPressed() {

			 ScorePrePointandroid.this.finish();

	    }
	}