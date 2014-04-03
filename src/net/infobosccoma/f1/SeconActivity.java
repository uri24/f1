package net.infobosccoma.f1;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SeconActivity extends Activity implements OnClickListener {

	private ImageButton btvideo, btimatge, btveu, btPlay;
	private TextView tvNom;
	private ImageView iUsuari;
	private Usuari usuari;
	private MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secon);

		gui();
	}

	private void gui() {
		this.setTitle("");
		usuari = (Usuari) getIntent().getExtras().getSerializable("usuari");

		tvNom = (TextView) findViewById(R.id.tvNom);
		tvNom.setText(usuari.getNom() + " " + usuari.getCognoms());
		iUsuari = (ImageView)findViewById(R.id.ivUsuari);
		
		String path = Environment.getExternalStorageDirectory() + "/" + this.getPackageName() + "/" + usuari.getImatge();
		iUsuari.setImageBitmap(Utils.obtenirImatgeFromSD(path, 20, 20));

		mediaPlayer = new MediaPlayer();

		try {
			AssetFileDescriptor descriptor = getAssets().openFd("canco.mp3");
			mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
			descriptor.close();
			mediaPlayer.setLooping(true);
			mediaPlayer.setVolume(100, 100);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		btvideo = (ImageButton) findViewById(R.id.btvideo);
		btvideo.setOnClickListener(this);
		btimatge = (ImageButton) findViewById(R.id.btImatge);
		btimatge.setOnClickListener(this);
		btveu = (ImageButton) findViewById(R.id.btGrabar);
		btveu.setOnClickListener(this);
		btPlay = (ImageButton)findViewById(R.id.btPlay);
		btPlay.setOnClickListener(this);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		if(mediaPlayer.isPlaying())
			mediaPlayer.pause();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		if(!mediaPlayer.isPlaying())
			mediaPlayer.start();
	}
	
	@Override
	protected void onStop(){
		super.onStop();
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		if(mediaPlayer.isPlaying())
			mediaPlayer.stop();
		mediaPlayer.release();
		mediaPlayer = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secon, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
		case R.id.btvideo:
			i = new Intent(SeconActivity.this, VideoActivity.class);
			startActivity(i);
			break;
		case R.id.btImatge:
			i = new Intent(SeconActivity.this, ImatgeActivity.class);
			startActivity(i);
			break;
		case R.id.btGrabar:
			
			break;
		case R.id.btPlay:
			break;
		default:
			break;
		}	
	}
}
