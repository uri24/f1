package net.infobosccoma.f1;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class Reproduir_video extends Activity {
	private VideoView videoView;
	private MediaController mediaController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_video);
		videoView = (VideoView) findViewById(R.id.vView);
		mediaController = new MediaController(this);
		mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
		videoView.setMediaController(mediaController);
		
		int posicio = (int) getIntent().getIntExtra("video",0);
		Uri path = Uri.parse("android.resource://net.infobosccoma.f1/" + posicio);
		
		videoView.setVideoURI(path);
		videoView.start();
		
		videoView.requestFocus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reproduir_video, menu);
		return true;
	}

}
