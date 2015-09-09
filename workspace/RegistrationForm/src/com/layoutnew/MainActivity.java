package com.layoutnew;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int CAMERA_REQUEST = 1888; 
	final Context context = this;
	Button Submit_form,Cancle_form;
	EditText emailEditText,Name_form,Address_form,password_form,confirm_pass,last_form;
	ImageView camara_from,exit_btn,profile_btn;
	RadioGroup rg1;
	ImageView form_banner;
	Spinner sp1;
	Animation animation;
	String gender,city=null;
	TextView takepicture_text;
	
	MediaPlayer player;
	AssetFileDescriptor afd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	takepicture_text=(TextView)findViewById(R.id.Pic_text);
	
		
	exit_btn=(ImageView)findViewById(R.id.form_btn_Back);
	camara_from=(ImageView)findViewById(R.id.form_camara);
	profile_btn=(ImageView)findViewById(R.id.form_btn_profile);
	
	form_banner=(ImageView)findViewById(R.id.form_banner);
	
	
	Submit_form=(Button)findViewById(R.id.form_submit);
	Cancle_form=(Button)findViewById(R.id.form_cancle);
	
	Name_form=(EditText)findViewById(R.id.form_name);
	emailEditText=(EditText)findViewById(R.id.form_email);
	Address_form=(EditText)findViewById(R.id.form_address);
	password_form=(EditText)findViewById(R.id.form_password);
	confirm_pass=(EditText)findViewById(R.id.form_confirm);
	last_form=(EditText)findViewById(R.id.form_lastname);
	
	
	
	animation=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockanimation);
	animation.setRepeatCount(Animation.INFINITE);
	profile_btn.startAnimation(animation);
	
	animation=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationalpha);
	animation.setRepeatCount(Animation.INFINITE);
	exit_btn.startAnimation(animation);
	
	/*TranslateAnimation animation = new TranslateAnimation(0.0f, 400.0f,
		    0.0f, 0.0f);
		  animation.setDuration(5000);
		  animation.setRepeatCount(5);
		  animation.setRepeatMode();;
		  animation.setFillAfter(true);
		  takepicture_text.startAnimation(animation);
	*/
	
	Animation anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animationtranslate);
	takepicture_text.startAnimation(anim);
	
	/*RotateAnimation anim = new RotateAnimation(90, 180, takepicture_text.getWidth()/2, takepicture_text.getHeight()/2);
	anim.setFillAfter(true);
	anim.setRepeatCount(0);
	anim.setDuration(10000);
	takepicture_text.startAnimation(anim); 
	*/
	
	
	rg1=(RadioGroup)findViewById(R.id.gender_radiogrp);
	
	
	sp1=(Spinner)findViewById(R.id.city);
	
	
	try {
		// Read the music file from the asset folder
		afd = getAssets().openFd("song.mp3");
		// Creation of new media player;
		player = new MediaPlayer();
		// Set the player music source.
		player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
		// Set the looping and play the music.
		player.setLooping(true);
		player.prepare();
		player.start();
		} catch (IOException e) {
		e.printStackTrace();
		}
		

	sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			
			city=sp1.getSelectedItem().toString();
			
			
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	});
	
	rg1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			
			if(checkedId==R.id.male)
			{
				gender="Male";
				Toast.makeText(getApplicationContext(), ""+gender, Toast.LENGTH_SHORT).show();
			}
			if(checkedId==R.id.female)
			{
				gender="Female";
				Toast.makeText(getApplicationContext(), ""+gender, Toast.LENGTH_SHORT).show();
			}
			
			
			
		}
	});
	
	
	
	exit_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			AlertDialog.Builder alert=new AlertDialog.Builder(context);
			alert.setTitle("Are you sure?").setCancelable(true).setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					
					MainActivity.this.finish();
				}
			  }).setNegativeButton("No",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					
					dialog.cancel();
				}
			});
			
			
			AlertDialog dialog=alert.create();
			
			dialog.show();
			

			
			
			
			
			
			
			
			 
			
		}
	});
	
	
	
	profile_btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			
			Dialog d=new Dialog(MainActivity.this);
			d.setContentView(R.layout.profile);
			d.setTitle("Profile");
			
			TextView text = (TextView) d.findViewById(R.id.profile_name);
			d.show();
			
			
			
		}
	});
	
	
	
	camara_from.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
            startActivityForResult(cameraIntent, CAMERA_REQUEST); 
			
		}
	});
	
	
	
	
	
	Cancle_form.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Name_form.setText("");
			emailEditText.setText("");
			Address_form.setText("");
			password_form.setText("");
			confirm_pass.setText("");
			last_form.setText("");
			
			
			
		}
	});
	
	
	
	
	
	
	
	Submit_form.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			Intent i=new Intent(MainActivity.this,data.class);
			i.putExtra("name",Name_form.getText().toString());
			i.putExtra("lastname",last_form.getText().toString());
			i.putExtra("password",password_form.getText().toString());
			i.putExtra("confirmpass",confirm_pass.getText().toString());
			i.putExtra("radioGroup",gender);
			i.putExtra("email",emailEditText.getText().toString());
			i.putExtra("address",Address_form.getText().toString());
			i.putExtra("city",city);
			startActivity(i);
			
			
			
			
			final String email = emailEditText.getText().toString();
			if (!isValidEmail(email)) 
			{
				emailEditText.setError("Invalid Email");
			}
			
			
			final String pass = password_form.getText().toString();
			if (!isValidPassword(pass)) {
				password_form.setError("Invalid Password");
			}
		
		
			
			
			
			
			
			
			
			
		
		
		
		}
		
		
	});
	
	
		
	
	player.start();

	
	
	
	
		
	}
	
	
	
	/*public void onPause() {
		super.onPause();
		player.pause();
		}

		
		public void onResume() {
		super.onResume();
		//player.start();
		}

		
		protected void onStop() {
		super.onStop();
		player.stop();
		player = null;
		}
*/
	
	private boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	// validating password with retype password
	private boolean isValidPassword(String pass) {
		if (pass != null && pass.length() > 6) {
			return true;
		}
		return false;
	}

	
	
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {  
	            Bitmap photo = (Bitmap) data.getExtras().get("data"); 
	            form_banner.setImageBitmap(photo);
	        }  
	    } 

	
}
