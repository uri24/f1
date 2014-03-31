package net.infobosccoma.f1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

				if (isIntentAvailable(this, MediaStore.ACTION_IMAGE_CAPTURE)) {
					// intenció de fer una foto
					Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					// crear la ruta del fitxer on desar la foto
					tempImageFile = crearFitxer();
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
				// mostrar una miniatura del fitxer que ha desat l'app de
				// captura
				imatge.setImageBitmap(obtenirImatge(tempImageFile.getAbsolutePath(), 200, 200));
				usuari.setImatge(tempImageFile.getName());
				bFoto.setText("Fer una altra foto");
			}
		}
	}


	/**
	 * Mètode que comprova si hi ha una aplicició per a captura de fotos
	 * 
	 * @param context
	 * @param action
	 * @return true si existeix, false en cas contrari
	 */
	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	/**
	 * Crea la ruta absoluta per a un nou fitxer temporal
	 * 
	 * @return L'objecte File que representa el fitxer
	 */
	private File crearFitxer() {
		String imageFileName;
		if (usuari.getImatge() == null) {
			// Create an image file name
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			imageFileName = usuari.getCognoms() + timeStamp + ".jpg";
		} else {
			imageFileName = usuari.getImatge();
		}
		File path = new File(Environment.getExternalStorageDirectory(),
				this.getPackageName());
		if (!path.exists())
			path.mkdirs();

		return new File(path, imageFileName);
	}

	public static Bitmap obtenirImatge(String path, int reqWidth, int reqHeight) {
		// Primer, descodificar el bitmap amb inJustDecodeBounds=true per
		// comprovar les dimensions
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		// Calcular inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		// Descodificar el bitmap amb el valor indicat de inSampleSize
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, options);
	}

	private static int calculateInSampleSize(Options options, int reqWidth,
			int reqHeight) {
		// alçadai ampladade la imatge
		int height = options.outHeight;
		int width = options.outWidth;
		int inSampleSize = 1;
		int heightRatio, widthRatio;
		if (height > reqHeight || width > reqWidth) {
			heightRatio = Math.round((float) height / (float) reqHeight);
			widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
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