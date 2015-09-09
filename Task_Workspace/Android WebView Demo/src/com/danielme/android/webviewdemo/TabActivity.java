package com.danielme.android.webviewdemo;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.zaptech.webviewdemo.WebViewDemoActivity;

public class TabActivity extends android.app.TabActivity {

    protected static final String TAG = null;
    Button btnAdd;
    TabHost tabHost;
    TabSpec maintab;
    int counter;
    View layout;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        final ActionBar bar = getActionBar();
        tabHost = getTabHost();

        maintab = tabHost.newTabSpec("Newtab");
        final View layout = LayoutInflater.from(this).inflate(R.layout.tab_button,
                null);

        Button button = (Button) layout.findViewById(R.id.settingsButton);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                int i = tabHost.getCurrentTab();


            }
        });


        tabHost.addTab(tabHost
                .newTabSpec("Your Tab")
                .setIndicator("tab indicator")
                .setContent(
                        new Intent(TabActivity.this, WebViewDemoActivity.class)));

        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                tabHost.addTab(tabHost
                        .newTabSpec("Your Tab")
                        .setIndicator("tab indicator")
                        .setContent(
                                new Intent(TabActivity.this,
                                        WebViewDemoActivity.class)));


                layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                tabHost.addView(layout);


            }
        });

        getTabHost().setOnTabChangedListener(new OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {

                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {


                }

            }
        });

    }
}
