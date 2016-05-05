package com.example.charles.censorninjamobile2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.SearchService;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TweetViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import java.io.File;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class CensorNinja extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "Y0bwzJniOVfTCbMN31y35osGW";
    private static final String TWITTER_SECRET = "TksfQJrSiziRcqm1wDnjfSVS57OqLT1WIIOs3bAVlWYZT2MeDk";

    private TwitterLoginButton loginButton;
    private View loginButtonView;
    private CensorNinja self1 = this;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_censor_ninja);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new TwitterCore(authConfig), new TweetComposer());

        ListView listView = (ListView) findViewById(R.id.listView);

        //LOGIN BUTTON
        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButtonView = findViewById(R.id.twitter_login_button);

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                //String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_SHORT).show();
                username = session.getUserName();

            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

        //REFRESH
        final SwipeRefreshLayout swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        final UserTimeline timeline = new UserTimeline.Builder()
                .screenName(username)
                .build();
        final TweetTimelineListAdapter adapter2 = new TweetTimelineListAdapter.Builder(self1).setTimeline(timeline).build();
        listView.setAdapter(adapter2);

        assert swipeLayout != null;
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);
                adapter2.refresh(new Callback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        swipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Toast or some other action
                    }
                });
            }
        });

        //POST BUTTON
        Button postButton = (Button) findViewById(R.id.postbutton);
        assert postButton != null;
        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Tweet Composer
                File myImageFile = new File("drawable/censoricon2.png");
                Uri myImageUri = Uri.fromFile(myImageFile);

                TweetComposer.Builder builder = new TweetComposer.Builder(self1)
                        .text("")
                        .image(myImageUri);
                builder.show();
            }
        });

        //SPINNER
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //CENSOR SWITCH
        final Switch censorSwitch = (Switch) findViewById(R.id.switch1);
        assert censorSwitch != null;
        censorSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                censorSwitch.getThumbDrawable().setColorFilter(censorSwitch.isChecked() ? Color.argb(255, 85, 172, 238) : Color.WHITE, PorterDuff.Mode.MULTIPLY);
                censorSwitch.getTrackDrawable().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                if(censorSwitch.isChecked()){
                    Spinner spinner = (Spinner) findViewById(R.id.spinner);
                    Toast.makeText(getApplicationContext(), "Browsing from: "+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginButtonView.performClick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
