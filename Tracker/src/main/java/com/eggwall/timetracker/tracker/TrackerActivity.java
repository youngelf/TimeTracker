package com.eggwall.timetracker.tracker;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;

/** The main activity. Create a time tracker. Allow the user
 * to start and stop the time tracker and provide a menu option for
 * showing the statistics over time.
*/
public class TrackerActivity extends ActionBarActivity {
// TODO(vikram): Basic time tracking framework: button to start/stop time and log the activity in
//   a trivial SQLite database.
// TODO(vikram): Notification that you are currently working
// TODO(vikram): Ability to modify previous work session.
// TODO(vikram): Show past work days with a graph.
// TODO(vikram): Suggest most productive time.
// TODO(vikram): Upload work day as a CSV/XML/Google spreadsheet
// TODO(vikram): Better UI for showing working.

    /** UTC timestamp for the previous start. */
    long mStartTime = -1;
    final Handler mHandler = new Handler();
    TextView mMainButton;
    final Runnable mUpdate = new Runnable() {
        @Override
        public void run() {
            mMainButton.setText("" + timeSinceStart());
            // Update the status every second.
            mHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        mMainButton = (TextView) findViewById(R.id.start_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tracker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    /**
     * Start tracking time. Called from the UI.
     * @param ignored view that is currently ignored.
     */
    public void startTracking(View ignored) {
        if (mStartTime < 0) {
            // We are stopped, start tracking
            mStartTime = System.currentTimeMillis();
            Log.d("Viki", "tracking started");
            mMainButton.setBackgroundColor(0xff11ff00);
            mHandler.post(mUpdate);
        } else {
            // We are started, stop tracking.
            mMainButton.setBackgroundColor(0xffff1100);
            mMainButton.setText("Stopped after " + timeSinceStart());
            mStartTime = -1;
            mHandler.removeCallbacks(mUpdate);
            // Write the session to disk with the current time stamp.
        }
    }

    private String timeSinceStart() {
        long endTime = System.currentTimeMillis();
        String timeSpent = TimeUtils.timeSinceStart(endTime, mStartTime, getResources());
        Log.d("Viki", "tracking now time = " + endTime + ": " + timeSpent);
        return timeSpent;
    }
}
