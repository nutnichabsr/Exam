package project.examination.com.examination;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Adil Soomro
 *
 */
public class Tab1_HomeActivity extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabpang1);  


      
         


        		  Button test = (Button) findViewById(R.id.button222);
        		  test.setOnClickListener(new OnClickListener() {
        		            public void onClick(View v) {
        		                // TODO Auto-generated method stub  TestClass Quiz_test_in

        		            	Intent i=new Intent(Tab1_HomeActivity.this,Quiz_test_in.class);
        		            	startActivity(i);
        		            }
        		        });
    }
}