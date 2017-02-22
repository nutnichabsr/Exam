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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;


public class List_Examinnation_All extends Activity {

	ListView listview;
	 public void onCreate(Bundle savedInstanceState){

	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.main_searchs);

	  TextView textView1sss = (TextView) findViewById(R.id.textView1_view);
	  textView1sss.setText("คลังข้อสอบ");
	 
	  //edittext = (EditText) findViewById(R.id.EditText01);
	    listview = (ListView) findViewById(R.id.ListView01);
		 listview.setAdapter(new  CustomListViewAdapter( ));
	 }



	
		@Override
		public void onBackPressed() {

			 List_Examinnation_All.this.finish();

	    }
	public class CustomListViewAdapter extends BaseAdapter {

		public int getCount() {
			return File_Confix_Data.getGetDepartment.size();
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
						for(int j=0;j<File_Confix_Data.getGetDepartment.size();j++){

							if( File_Confix_Data.getGetDepartment.get(j).getDepartment_id()==Integer.parseInt(catID)){
								File_Confix_Data.potition=  j;
							}
						}


						Intent intent = new Intent(List_Examinnation_All.this , List_Examinnation_Detail.class);
						startActivity(intent);
					}
				});

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.titleTextView.setText( File_Confix_Data.getGetDepartment.get( position).getDepartment_name());
			holder.descTextView.setText(  " ");
			holder.textView1.setText("เลือก  ..." );





			holder.relativeLayoutthumbnail.setTag(R.id.relativeLayoutthumbnail, File_Confix_Data.getGetDepartment.get( position).getDepartment_id());
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
	}