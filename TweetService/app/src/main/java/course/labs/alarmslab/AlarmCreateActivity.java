package course.labs.alarmslab;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

public class AlarmCreateActivity extends Activity {

	public static final String TWEET_STRING = "TWEET";
	private AlarmManager mAlarmManager;

	private static final String TAG = "AlarmCreateActivity";
	private EditText mTweetTextView, mDelayTextView;
	private int mID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//TO/DO - Set up an AlarmManager
		mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		mTweetTextView = (EditText) findViewById(R.id.text);
		mDelayTextView = (EditText) findViewById(R.id.time);

	}

	public void set(View v) {
		String tweetText = mTweetTextView.getText().toString();
		Long delay = Integer.parseInt(mDelayTextView.getText().toString()) * 1000L;

		//TO/DO - Create an Intent to start the AlarmTweetService
		Intent mAlarmTweetServiceIntent = new Intent(AlarmCreateActivity.this,
				AlarmTweetService.class);
		

		//TO/DO - Add the tweet as an extra to the Intent
		mAlarmTweetServiceIntent.putExtra(TWEET_STRING, tweetText);

		//TO/DO - Create a PendingIntent that will use the Intent above to start the
		// AlarmTweetService. Use PendingIntent.getService().

		// Pass in a unique value for the request code. Otherwise, your
		// pendingIntent will be overridden if there are two or more at any one time.

		// Pass in PendingIntent.FLAG_ONE_SHOT as a flag, which will make
		// sure that your PendingIntent is only used once.

		Random rand = new Random();
		mID = rand.nextInt();
		Log.d(TAG, Integer.toString(mID));
		PendingIntent pendingIntent = PendingIntent.getService(
				this.getApplicationContext(),
				mID,
				mAlarmTweetServiceIntent,
				PendingIntent.FLAG_ONE_SHOT);

		Log.d(TAG, "Tweet sent to AlarmTweetService");
		Log.d(TAG, Long.toString(System.currentTimeMillis()));
		//TO/DO - Use the AlarmManager set method to set the Alarm

		mAlarmManager.set(
				AlarmManager.ELAPSED_REALTIME,
				SystemClock.elapsedRealtime() + delay,
				pendingIntent);

	}

	public void clear(View v) {
		mTweetTextView.setText("");
		mDelayTextView.setText("");

	}
}
