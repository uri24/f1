package net.infobosccoma.f1;




import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class SeconActivity extends Activity {

	ImageButton btvideo, btimatge,btveu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secon);
		// posar musica
		
			MediaPlayer mediaPlayer;
	        mediaPlayer = MediaPlayer.create(this, Uri.parse("canso.mp3"));
	        mediaPlayer.setLooping(true);
	        mediaPlayer.setVolume(100,100);
	        mediaPlayer.start();
	        
	        
		
		  btvideo = (ImageButton) findViewById(R.id.btvideo);
	      btvideo.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					Intent i = new Intent(SeconActivity.this,VideoActivity.class);
					startActivity(i);
				}
			});
	      btimatge = (ImageButton) findViewById(R.id.button2);
	      btimatge.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(SeconActivity.this,ImatgeActivity.class);
				startActivity(i);
			}
		});
	      btveu =(ImageButton) findViewById(R.id.button3);
	      btveu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SeconActivity.this,VeuActivity.class);
				startActivity(i);
				
			}
		});
	      
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secon, menu);
		return true;
	}

}
