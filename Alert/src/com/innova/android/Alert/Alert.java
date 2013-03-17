package com.innova.android.Alert;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.DialogInterface.OnClickListener;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Alert extends Activity {

	private static String TREASURE_PROXIMITY_ALERT = "com.paad.treasurealert";

	private final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			updateWithNewLocation(location);
		}

		public void onProviderDisabled(String provider) {
			updateWithNewLocation(null);
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		// Alerta
		// Sn Fernando
		double lat = 6.224767;
		double lng = -75.597336;
		float radius = 100f;
		long expiration = -1;

		LocationManager locationManager;
		String context = Context.LOCATION_SERVICE;

		locationManager = (LocationManager) getSystemService(context);

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);

		String provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(provider);
		updateWithNewLocation(location);

		// Actualizacion de lugar
		locationManager.requestLocationUpdates(provider, 2000, 10,
				locationListener);

		// Alerta
		Intent intent = new Intent(TREASURE_PROXIMITY_ALERT);

		PendingIntent proximityIntent = PendingIntent.getBroadcast(this, -1,
				intent, 0);

		locationManager.addProximityAlert(lat, lng, radius, expiration,
				proximityIntent);

		// Filtro para la alerta
		IntentFilter filter = new IntentFilter(TREASURE_PROXIMITY_ALERT);
		registerReceiver(new ProximityIntentReceiver(), filter);
			
	}

	// Muestra las coordenadas de la accion que se le muestre
	private void updateWithNewLocation(Location location) {

		String latLongString;
		TextView myLocationText;
		myLocationText = (TextView) findViewById(R.id.myLocationText);

		if (location != null) {

			double lat = location.getLatitude();
			double lng = location.getLongitude();
			latLongString = "Latitud:" + lat + "\nLongitud:" + lng;

		} else {
			latLongString = "No se encontro una posición";
		}

		myLocationText.setText("Tu posición actual es:\n" + latLongString);
	}

	public class ProximityIntentReceiver extends BroadcastReceiver {
 
		@Override
		public void onReceive(Context context, Intent intent) {
			
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
	        builder.setMessage("Actividad cerca").setTitle("Alerta")
	               .setCancelable(false)
	               .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                        finish();
	                   }
	               }).show();
	        
//	        Dialog ventana = new Dialog (context);
//	        
//	        ListView Lista = (ListView) ventana.findViewById(R.id.lista);
//	        Lista.setAdapter(adapterlista);
//	        ventanaIng.setTitle("Lista");
//	        ventanaIng.show();

		}
	}

}
