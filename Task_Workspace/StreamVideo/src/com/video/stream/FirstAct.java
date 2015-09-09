package com.video.stream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FirstAct extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
//        Bundle extras = new Bundle();
//        extras.putInt("media", 2);
//        Intent intent = new Intent(this,MediaPlayerDemo_Video.class);
//        intent.putExtra("com.video.stream.bundle", extras);
        Intent intent = new Intent(this,StreamingPlayer.class);
        startActivity(intent);
    }
}