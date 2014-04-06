package net.infobosccoma.f1;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VideoActivity extends ListActivity implements OnItemClickListener {

	private String[] listItems = { "Promo 2014", "Carrera Australia", "Carrera Malasia", "Indycar vs Formula1", "Sortida carrera", "Top 5" };
	private int[] ids = { R.raw.f1, R.raw.australia, R.raw.carreramalasia, R.raw.indycarvsformula1, R.raw.sortida, R.raw.top5 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTitle("Videos");
		setContentView(R.layout.list);
		setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems));
		ListView lista = (ListView) findViewById(android.R.id.list);
		lista.setOnItemClickListener(this);
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int posicio,
			long arg3) {
		// TODO Auto-generated method stub
		Intent i = new Intent(VideoActivity.this, Reproduir_video.class);
		Bundle b = new Bundle();
		b.putInt("video", ids[posicio]);
		i.putExtras(b);
		startActivity(i);

	}
}
