package jp.fkmsoft.sample.asynctask;

import android.os.AsyncTask;
import android.util.Log;

public class MyTask extends AsyncTask<Integer, Void, Void> {

    private static final String TAG = "MyTask";

    @Override
    protected Void doInBackground(Integer... params) {
        int waitTime = params[0];
        Log.i(TAG, "begin : time=" + waitTime);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "end : time=" + waitTime);
        return null;
    }

}
