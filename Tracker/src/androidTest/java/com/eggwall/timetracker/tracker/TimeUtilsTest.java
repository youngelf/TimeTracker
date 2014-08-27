package com.eggwall.timetracker.tracker;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Tests {@link TrackerActivity}
 */
public class TimeUtilsTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Do nothing
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        // Again, do nothing
    }

    public void testNothing() {
        long currentTime = System.currentTimeMillis();
        Log.d("Viki", "Tests running " + TimeUtils.timeSinceStart(currentTime+3661000, currentTime,
                mContext.getResources()));
        Log.d("Viki", "Tests running " + TimeUtils.timeSinceStart(currentTime+3661, -1,
                mContext.getResources()));

        assertTrue(true);
    }

    public void testNothing2() {
        Log.d("Viki", "Tests running");
        assertTrue(true);
    }
}
