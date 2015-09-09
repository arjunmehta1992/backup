package com.example.songsplay;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityHome extends Activity {

	ListView lv_listSongs;
	Button btn_loadSong;
	private MediaPlayer mMediaPlayer;
	private String[] mMusicList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();

		mMediaPlayer = new MediaPlayer();
		File extStore = Environment.getExternalStorageDirectory();

		ListView mListView = (ListView) findViewById(R.id.lv_Songs);

		mMusicList = getMusic();

		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mMusicList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String name = (String) parent.getItemAtPosition(position);
				Toast.makeText(ActivityHome.this, "" + name, Toast.LENGTH_SHORT)
						.show();
				try {
					playSong(mMusicList[position]);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			private void playSong(String path) throws IllegalArgumentException,
					IllegalStateException, IOException {

				String extStorageDirectory = Environment
						.getExternalStorageDirectory().toString();

				path = extStorageDirectory + File.separator + path;

				mMediaPlayer.reset();
				mMediaPlayer.setDataSource(path);
				mMediaPlayer.prepare();
				mMediaPlayer.start();

			}
		});
	}

	private String[] getMusic() {
				final Cursor mCursor = managedQuery(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.Audio.Media.DISPLAY_NAME }, null,
				null, "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC");

		int count = mCursor.getCount();

		String[] songs = new String[count];
		int i = 0;
		if (mCursor.moveToFirst()) {
			do {
				songs[i] = mCursor.getString(0);
				i++;
			} while (mCursor.moveToNext());
		}

		mCursor.close();

		return songs;
	}

	public void init() {

		lv_listSongs = (ListView) findViewById(R.id.lv_Songs);
		btn_loadSong = (Button) findViewById(R.id.btn_loadSongs);

	}

}