package com.zaptech.shoppingcart;

import com.zaptech.shoppingkart.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Admin_Home extends TabActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin__home);
		TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
		
		TabSpec addItem = tabHost.newTabSpec("Add");
		TabSpec updateItem = tabHost.newTabSpec("Update");
		TabSpec deleteItem = tabHost.newTabSpec("Delete");
		TabSpec searchItem = tabHost.newTabSpec("Search");
         TabSpec orderList = tabHost.newTabSpec("Order");
       
        
         addItem.setIndicator("", getResources().getDrawable(R.drawable.add));
         addItem.setContent(new Intent(this,AddItem.class));
     
         
         updateItem.setIndicator("", getResources().getDrawable(R.drawable.update));
         updateItem.setContent(new Intent(this,OrderList.class));
         
        deleteItem.setIndicator("", getResources().getDrawable(R.drawable.deleted));
         deleteItem.setContent(new Intent(this,OrderList.class));
         
         searchItem.setIndicator("", getResources().getDrawable(R.drawable.search));
         searchItem.setContent(new Intent(this,OrderList.class));
        orderList.setIndicator("", getResources().getDrawable(R.drawable.list));
        orderList.setContent(new Intent(this,OrderList.class));
        
        
        tabHost.addTab(addItem);
        tabHost.addTab(updateItem);
        tabHost.addTab(deleteItem);
        tabHost.addTab(searchItem);
        tabHost.addTab(orderList);
       
	}
}
