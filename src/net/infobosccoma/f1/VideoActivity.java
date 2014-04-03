package net.infobosccoma.f1;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.AdapterView.OnItemClickListener;

public class VideoActivity extends ListActivity  implements OnItemClickListener{


	
	String[] listItems = {"f1", "item 2 ", "list", "android", "item 3", "foobar", "bar", }; 
   int[] ids ={R.raw.f1};
	@Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.list);
         setListAdapter(new ArrayAdapter(this,  android.R.layout.simple_list_item_1, listItems));
         

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video, menu);
		return true;
	}
	
	// metode per no perdre les dades introduides
	@Override
	public void onConfigurationChanged(Configuration novaconfig) {
		super.onConfigurationChanged(novaconfig);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int posicio, long arg3) {
		// TODO Auto-generated method stub
		Intent i = new Intent(VideoActivity.this, Reproduir_video.class);
		Bundle b = new Bundle();
		b.putSerializable("video", ids[posicio]);
		i.putExtras(b);
		startActivity(i);
		
	}
}
