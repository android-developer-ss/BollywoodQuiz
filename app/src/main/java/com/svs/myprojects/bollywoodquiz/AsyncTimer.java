package com.svs.myprojects.bollywoodquiz;

import android.os.AsyncTask;
import android.os.SystemClock;

/**
 * Created by snehalsutar on 2/13/16.
 */
public class AsyncTimer extends AsyncTask<Integer, Integer, Void> {
    AsyncTimerInterface mAsyncTimerInterface;

    public AsyncTimer(AsyncTimerInterface asyncTimerInterface) {
        this.mAsyncTimerInterface = asyncTimerInterface;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        for (int i = params[0]; i >= 0; i--) {
            publishProgress(i);
            SystemClock.sleep(1000);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        this.mAsyncTimerInterface.setText(values[0]+"");
    }
}

