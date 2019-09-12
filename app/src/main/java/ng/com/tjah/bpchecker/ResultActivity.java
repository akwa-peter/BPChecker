package ng.com.tjah.bpchecker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    String data, data1, data2, data3, data4;
    TextView result;
    Button backBtn;

    String okay, notOkay, bad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.text_view_result);
        backBtn = findViewById(R.id.back);
        Intent inten = getIntent();
        Bundle mBundle = inten.getExtras();

        if (mBundle != null) {
            data = mBundle.getString("ans1");
            data1 = mBundle.getString("ans2");
            data2 = mBundle.getString("ans3");
            data3 = mBundle.getString("ans4");
            data4 = mBundle.getString("ans5");
           // Toast.makeText(this, data + data1 + data2 + data3+ data4, Toast.LENGTH_LONG).show();
            if(data.equals("Always") && data1.equals("Yes") && data2.equals("Yes") && data3.equals("Yes") && data4.equals("Yes")){

                result.setText("You need to see a Doctor immediately. You could have a High BP " +
                        "crisis that can lead to a heart attack or stroke");

            }else if(data.equals("Occasionally") && data1.equals("No") && data2.equals("Sometimes") && data3.equals("No") && data4.equals("Sometimes")){

                result.setText("There are Chances that your BP could be High. Please visit a doctor for further test");
            } else if(data1.equals("Yes") && data3.equals("Yes")){

                result.setText("You need to see a Doctor immediately. You could have a High BP" +
                        "crisis that could lead to a heart attack or stroke");
            }

            else {

                result.setText("Chances of having High BP is low. Please visit a doctor for confirmation");
            }
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }
}
