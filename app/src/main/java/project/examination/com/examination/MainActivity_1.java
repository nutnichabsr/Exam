package project.examination.com.examination;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectcatcagory);


        Button buttonEnter1 = (Button) findViewById(R.id.buttonEnter1);
        buttonEnter1.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {


                Intent intent = new Intent(MainActivity_1.this, Quiz_test_in.class);
                startActivity(intent);

            }
        });

    }
}
