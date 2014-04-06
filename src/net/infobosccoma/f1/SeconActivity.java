package net.infobosccoma.f1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SeconActivity extends Activity implements OnClickListener {

	private ImageButton btvideo, btimatge;
	private ToggleButton btmicro,btplay;
	private TextView tvNom;
	private ImageView iUsuari;
	private Usuari usuari;
	private MediaPlayer mediaPlayer;
	private MediaRecorder mRecorder;
	private MediaPlayer mPlayer;

	private static final String LOG_TAG = "GravacioAudio";
	private static String mFileName = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secon);

		gui();
	}

	private void gui() {
		this.setTitle("");

		usuari = (Usuari) getIntent().getExtras().getSerializable("usuari");
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		mFileName = usuari.getCognoms() + timeStamp + ".3gp";

		File pathFile = new File(Environment.getExternalStorageDirectory(), LOG_TAG);
		if (!pathFile.exists())
			pathFile.mkdirs();

		mFileName = new File(pathFile, mFileName).getAbsolutePath();
		
		tvNom = (TextView) findViewById(R.id.tvNom);
		tvNom.setText(usuari.getNom() + " " + usuari.getCognoms());
		iUsuari = (ImageView)findViewById(R.id.ivUsuari);
		
		String path = Environment.getExternalStorageDirectory() + "/" + this.getPackageName() + "/" + usuari.getImatge();
		iUsuari.setImageBitmap(Utils.obtenirImatgeFromSD(path, 40, 40));

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
		btmicro = (ToggleButton) findViewById(R.id.btmicro);
		btmicro.setOnClickListener(this);
		btplay = (ToggleButton) findViewById(R.id.btPlay);
		btplay.setOnClickListener(this);
		btplay.setVisibility(4);
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
	
	private void startRecording() {

		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}

		mRecorder.start();
	}
	
	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
		btplay.setVisibility(0);
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
		boolean onMicro, onPlay;
		switch (v.getId()) {
		case R.id.btvideo:
			i = new Intent(SeconActivity.this, VideoActivity.class);
			startActivity(i);
			break;
		case R.id.btImatge:
			i = new Intent(SeconActivity.this, ImatgeActivity.class);
			startActivity(i);
			break;
		case R.id.btmicro:

			// esta el togglebutton on?
			onMicro = btmicro.isChecked();

			if (onMicro) {
				mediaPlayer.pause();
				startRecording();
			} else {
				stopRecording();
				mediaPlayer.start();
			}

			break;
		case R.id.btPlay:
			
			// Esta el togglebutton on?
			onPlay = btplay.isChecked();
			
			if (onPlay){
				
				startPlaying();
				
			}else{
				stopPlaying();
			}
			
			break;
			
		default:
			break;
		}
	}
	
	private void stopPlaying() {
		mPlayer.release();
		mPlayer = null;
		
	}

	private void startPlaying() {
		 mPlayer = new MediaPlayer();
		 mPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				btplay.setChecked(false);
			}
		});
		try {
			mPlayer.setDataSource(mFileName);
			mPlayer.prepare();
			mPlayer.start();
			
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}
		
	}
}
