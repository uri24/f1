package net.infobosccoma.f1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class ImatgeActivity extends Activity {

	private ArrayList<Escuderia> dades;
	private ListView lista;
	private FuAdapter adapter;
	private Bitmap imatge;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imatge);
		
		this.setTitle("Escuderias");
		
		Resources res = getResources();
		String[] escuderias = res.getStringArray(R.array.escuderias);
		String[] motors = res.getStringArray(R.array.motores);
		String[] pilots = res.getStringArray(R.array.pilots);
		
		dades = new ArrayList<Escuderia>();
		
		for (int i = 0; i < escuderias.length; i++) {
			Escuderia e = new Escuderia(escuderias[i], null, pilots[i * 2], pilots[(i * 2) + 1], null);

			switch (i) {
			case 0:
			case 3:
			case 7:
			case 9:
				if(i == 0) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.caterham, 206, 58));
				if(i == 3) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.lotus, 206, 58));
				if(i == 7) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.redbull, 206, 58));
				if(i == 9) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.tororosso, 206, 58));
				e.setMotor(motors[0]);				
				break;
			case 2:
			case 5:
			case 6:
			case 10:
				if(i == 2) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.forceindia, 206, 58));
				if(i == 5) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.mclaren, 206, 58));
				if(i == 6) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.mercedes, 206, 58));
				if(i == 10) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.williams, 206, 58));	
				e.setMotor(motors[1]);
				break;
			case 1:
			case 4:
			case 8:
				if(i == 1) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.ferrari, 206, 58));
				if(i == 4) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.marussia, 206, 58));
				if(i == 8) e.setImatge(Utils.obtenirImatgeFromResource(getResources(), R.drawable.sauber, 206, 58));
				e.setMotor(motors[2]);
				break;
			default:
				break;
			}
			dades.add(e);
		}
		
		adapter = new FuAdapter(this, dades);
		lista = (ListView) findViewById(R.id.llistaEscuderias);
		lista.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.imatge, menu);
		return true;
	}
	
	// metode per no perdre les dades introduides
	@Override
	public void onConfigurationChanged(Configuration novaconfig) {
		super.onConfigurationChanged(novaconfig);
	}
}
