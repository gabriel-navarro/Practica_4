package com.cutonala.practica4;

import java.io.File;
import java.io.FilenameFilter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VideoActivity extends ListActivity{
	
	ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		adapter = new ArrayAdapter<String>(this, R.layout.activity_main, getVideos());
		setListAdapter(adapter);
		
		ListView listView = getListView();
		listView.setOnItemClickListener (new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String absulutePath=parent.getItemAtPosition(position).toString();
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				intent.putExtra("video", absulutePath);
				startActivity(intent);
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	static String[] mFiles=null;
	
	public static String[] getVideos(){
		File videos = Environment.getExternalStorageDirectory();
		File[] videoslist = videos.listFiles(new FilenameFilter() {
			
			public boolean accept(File dir, String name){
				return((name.endsWith(".mp3") || name.endsWith(".mp4")));
			}
		});
		
		mFiles = new String[videoslist.length];
				
	for(int i=0;i<videoslist.length;i++){
		mFiles[i] = videoslist[i].getAbsolutePath();
	}
	return mFiles;
	
	}		
}