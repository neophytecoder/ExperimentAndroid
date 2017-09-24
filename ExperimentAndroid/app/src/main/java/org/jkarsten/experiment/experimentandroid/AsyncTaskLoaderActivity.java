package org.jkarsten.experiment.experimentandroid;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by juankarsten on 9/24/17.
 */

public class AsyncTaskLoaderActivity extends AppCompatActivity implements Callback, LoaderManager.LoaderCallbacks<String> {
    private static final int LOADER_ID = 123345;
    Loader<String> mLoader;
    private TextView mTextView;
    private LoaderManager mLoaderManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        mTextView = (TextView) findViewById(R.id.result);
        mLoaderManager = getSupportLoaderManager();
    }

    public void startAsync(View view) {
        if (mLoader == null) {
            mLoader = mLoaderManager.initLoader(LOADER_ID, null, this);
        } else {
            mLoader = mLoaderManager.restartLoader(LOADER_ID, null, this);
        }
    }

    @Override
    public void onDone(String result) {
        mTextView.setText(result);
    }


    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new AsyncTask(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        onDone(s);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

}

interface Callback {
    void onDone(String result);
}

class AsyncTask extends AsyncTaskLoader<String> {
    String cache;
    int count;

    public AsyncTask(Context context) {
        super(context);
        count = 0;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (cache == null) {
            forceLoad();
        } else {
            deliverResult(cache + " " + ++count);
        }
    }

    @Override
    public String loadInBackground() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cache = "Successful";
        }
        return cache + " " + ++count;
    }

}


