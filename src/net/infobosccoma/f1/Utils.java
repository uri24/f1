package net.infobosccoma.f1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;

public class Utils {

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
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	public static Bitmap obtenirImatgeFromSD(String path, int reqWidth, int reqHeight) {
		// Primer, descodificar el bitmap amb inJustDecodeBounds=true per comprovar les dimensions
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		// Calcular inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		// Descodificar el bitmap amb el valor indicat de inSampleSize
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, options);
	}

	public static Bitmap obtenirImatgeFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
		// Primer, descodificar el bitmapambinJustDecodeBounds=true per comprovarles dimensions
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		// Calcular inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		// Descodificar el bitmapambel valor indicatde inSampleSize
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	/**
	 * Crea la ruta absoluta per a un nou fitxer temporal
	 * 
	 * @return L'objecte File que representa el fitxer
	 */
	public static File crearFitxer(Usuari u, String context) {
		String imageFileName;
		if (u.getImatge() == null) {
			// Create an image file name
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
					.format(new Date());
			imageFileName = u.getCognoms() + timeStamp + ".jpg";
		} else {
			imageFileName = u.getImatge();
		}
		File path = new File(Environment.getExternalStorageDirectory(), context);
		if (!path.exists())
			path.mkdirs();

		return new File(path, imageFileName);
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
}
