package org.jkarsten.experiment.experimentandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LocalBroadcastActivity extends AppCompatActivity  {

    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_broadcast);

        editText = (EditText) findViewById(R.id.edit_text);
        textView = (TextView) findViewById(R.id.result);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LocalBroadcastActivity.class.getName());

        LocalBroadcastManager.getInstance(this).registerReceiver(new MyBroadcastReceiver(), intentFilter);
    }

    public void sendBroadcast(View view) {
        Intent intent = new Intent(LocalBroadcastActivity.class.getName());
        intent.putExtra("data", editText.getText().toString());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            textView.setText(intent.getStringExtra("data"));
        }
    }
}
