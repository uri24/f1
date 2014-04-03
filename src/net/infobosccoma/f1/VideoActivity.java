package net.infobosccoma.f1;

import android.app.Activity;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {

	private VideoView videoView;
	private MediaController mediaController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		videoView = (VideoView) findViewById(R.id.vView);
		mediaController = new MediaController(this);
		videoView.setMediaController(mediaController);

		videoView.setVideoURI(Uri.parse("http://www.youtube.com/watch?v=2eSh7YDHXrU"));
		videoView.start();
		mediaController.show();
		videoView.requestFocus();
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
}
