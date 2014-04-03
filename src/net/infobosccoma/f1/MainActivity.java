package net.infobosccoma.f1;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	// on visualitzarem la imatge capturada
	private ImageView imatge;
	private Button bFoto, bNext;
	private EditText nom, cognom;
	// el fitxer on es guardarà la imatge
	private File tempImageFile;
	private Usuari usuari;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gui();
	}

	public void gui() {
		imatge = (ImageView) findViewById(R.id.ifoto);

		imatge.setImageBitmap(Utils.obtenirImatgeFromResource(getResources(), R.drawable.perfil, 250, 250));
		bFoto = (Button) findViewById(R.id.bfoto);
		bFoto.setOnClickListener(this);
		bNext = (Button) findViewById(R.id.bNext);
		bNext.setOnClickListener(this);
		bNext.setVisibility(8);
		nom = (EditText) findViewById(R.id.enom);
		cognom = (EditText) findViewById(R.id.ecognom);
		usuari = new Usuari();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bfoto:
			if (!nom.getText().toString().equals("") && !cognom.getText().toString().equals("")) {
				usuari.setNom(nom.getText().toString());
				usuari.setCognoms(cognom.getText().toString());

				if (Utils.isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
					// intenció de fer una foto
					Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					// crear la ruta del fitxer on desar la foto
					tempImageFile = Utils.crearFitxer(usuari, this.getPackageName());
					// li passem paràmetres a l'Inent per indicar que es vol
					// guarda
					// la captura en un fitxer
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempImageFile));
					// inciar l'intent
					startActivityForResult(takePictureIntent, 0);
				} else {
					Toast.makeText(this,
							"No hi ha cap aplicació per capturar fotos", Toast.LENGTH_LONG).show();
				}
			} else {
				Toast.makeText(this, "Has de posar les teves dades", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.bNext:
			Intent i = new Intent(MainActivity.this, SeconActivity.class);
			Bundle b = new Bundle();
			b.putSerializable("usuari", usuari);
			i.putExtras(b);
			startActivity(i);
			// Tancament de l'activitat per tal que l'usuari no pugui tornar
			// a aquesta activitat pitjant el botó Tornar
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				bNext.setVisibility(0);
				// mostrar una miniatura del fitxer que ha desat l'app de captura
				imatge.setImageBitmap(Utils.obtenirImatgeFromSD(tempImageFile.getAbsolutePath(), 250, 250));
				usuari.setImatge(tempImageFile.getName());
				bFoto.setText("Fer una altra foto");
			}
		}
	}

	// metode per no perdre les dades introduides
	@Override
	public void onConfigurationChanged(Configuration novaconfig) {
		super.onConfigurationChanged(novaconfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}