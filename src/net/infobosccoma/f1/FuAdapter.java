package net.infobosccoma.f1;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FuAdapter extends ArrayAdapter<Escuderia>{

	class Vista{
		public TextView nomEsc, motor, pilot1, pilot2;
		public ImageView imatge;
	}
	private ArrayList<Escuderia> dades;
	
	public FuAdapter(Activity context, ArrayList<Escuderia> dades) {
		super(context, R.layout.activity_imatge, dades);
		this.dades = dades;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View element = convertView;
		Vista vista;
		
		// optimització de codi per vizualitzar mes ràpid
		if (element == null) {
			LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
			element = inflater.inflate(R.layout.llistat, null);

			vista = new Vista();
			vista.nomEsc = (TextView) element.findViewById(R.id.tNomEsc);
			vista.motor = (TextView) element.findViewById(R.id.tMotor);
			vista.pilot1 = (TextView) element.findViewById(R.id.tPilot1);
			vista.pilot2 = (TextView) element.findViewById(R.id.tPilot2);
			vista.imatge = (ImageView) element.findViewById(R.id.iCoche);

			element.setTag(vista);
		} else {
			vista = (Vista) element.getTag();
		}

		vista.nomEsc.setText(dades.get(position).getNom());
		vista.motor.setText("Motor: " + dades.get(position).getMotor());
		vista.pilot1.setText("Pilot Nº 1: " + dades.get(position).getPilot1());
		vista.pilot2.setText("Pilot Nº 2: " + dades.get(position).getPilot2());
		vista.imatge.setImageBitmap(dades.get(position).getImatge());
		
		return element;
	}
}
