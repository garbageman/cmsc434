package course.labs.alarmslab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by josephmasison on 4/17/16.
 */
public class TwitterActivity extends Activity {

    private Button mPost;
    private View feed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter);

        //set up buttons
        mPost = (Button) findViewById(R.id.postButton);
        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch Facebook activity
                Intent intent = new Intent(getApplicationContext(), AlarmCreateActivity.class);
                startActivity(intent);
            }
        });

    }
}
