package net.infobosccoma.f1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VeuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_veu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.veu, menu);
		return true;
	}

}
