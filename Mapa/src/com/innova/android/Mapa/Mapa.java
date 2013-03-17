package com.innova.android.Mapa;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class Mapa extends MapActivity{
	
	MapController mapController;

	//Escucha la posicion del mapa
	private final LocationListener locationListener = new LocationListener() {
		
		public void onLocationChanged(Location location) {
			nuevaPosicion(location);
		}

		public void onProviderDisabled(String provider) {
			nuevaPosicion(null);
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	};

	//Metodo de la interfaz MapActivity
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.main);

		//Vista del mapa
		MapView mapa = mapa();
		

		// LocationManager
		String context = Context.LOCATION_SERVICE;
		LocationManager locationManager;
		locationManager = (LocationManager) getSystemService(context);

		String provider = locationManager.getBestProvider(criteriosProveedor(), true);
		Location location = locationManager.getLastKnownLocation(provider);

		//Se muestra la ultima locacion obtenida
		nuevaPosicion(location);

		//Finalmente se sensa cada 2 segundos la posicion del mapa y se muestra
		locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
	}
	
	

	//Muestra en el mapa la locacion dada.
	private void nuevaPosicion(Location locacion) {

		String posicion;
		TextView mostrarPosicion;
		mostrarPosicion = (TextView) findViewById(R.id.textoPosicion);

		if (locacion != null) {
			
			// Update the map location.
			Double geoLat = locacion.getLatitude()*1E6;
			Double geoLng = locacion.getLongitude()*1E6;
			GeoPoint point = new GeoPoint(geoLat.intValue(),
			                              geoLng.intValue());
			mapController.animateTo(point);


			double lat = locacion.getLatitude();
			double lng = locacion.getLongitude();
			posicion = "Lat: " + lat + "\nLon: " + lng;

		} else {
			posicion = "No se encontro una posicion";
		}

		mostrarPosicion.setText("Tu posicion actual es:\n" + posicion);
	}
	
	//Referencia y opciones del mapa a mostrar
	private MapView mapa(){
		MapView mapa = (MapView)findViewById(R.id.vistaMapa);
		
		// Se obtiene el controlador del mapa
		mapController = mapa.getController();
		
		mapa.setSatellite(true);
		mapa.setStreetView(true);
		mapa.setClickable(true);
		mapa.displayZoomControls(false);
		mapController.setZoom(17);
		
		return mapa;
		
	}
	
	//Criterios para determinar el mejor proveedor de LBS (Location Based Service)
	private Criteria criteriosProveedor(){
		
		Criteria criteria = new Criteria();
		
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		
		return criteria;
		
	}
}