package com.innova.android.Mapa;

import java.text.DecimalFormat;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class Mapa extends MapActivity {

	MapController mapController;
	Location location;
	MapView mapa;
	TextView mostrarPosicion;

	// Escucha la posicion del mapa
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

	// Metodo de la interfaz MapActivity
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.main);

		// LocationManager
		String context = Context.LOCATION_SERVICE;
		LocationManager locationManager;
		locationManager = (LocationManager) getSystemService(context);

		String provider = locationManager.getBestProvider(criteriosProveedor(),
				true);
		location = locationManager.getLastKnownLocation(provider);

		// Vista del mapa
		mapa = mapa();

		mapa.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Location locacion = new Location(location);
				locacion.reset();

				try {
					GeoPoint centro = mapa.getMapCenter();

					double lat = centro.getLatitudeE6()/1000000;
					double lng = centro.getLongitudeE6()/1000000;

					mapController.animateTo(centro);
					String posicion = "Lat: " + lat + "\nLon: " + lng;
					mostrarPosicion.setText("Tu posicion actual es:\n" + posicion);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});

//		mapa.setOnTouchListener(new View.OnTouchListener() {
//
//			public boolean onTouch(View v, MotionEvent event) {
//				Location locacion = new Location(location);
//				locacion.reset();
//
//				try {
//					GeoPoint centro = mapa.getMapCenter();
//
//					double lat = centro.getLatitudeE6();
//					double lng = centro.getLongitudeE6();
//
//					mapController.animateTo(centro);
//					String posicion = "Lat: " + lat + "\nLon: " + lng;
//					mostrarPosicion.setText("Tu posicion actual es:\n" + posicion);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				return true;
//
//			}
//		});

		// Se muestra la ultima locacion obtenida
		nuevaPosicion(location);

		// Finalmente se sensa cada 2 segundos la posicion del mapa y se muestra
		locationManager.requestLocationUpdates(provider, 2000, 10,
				locationListener);
	}

	/**
	 * Se crea el menu de la vista mapa
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 1, 0, "Mostar centro");
		return true;
	}

	/**
	 * Accion que se hace al presionar alguna opcion del menu
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Location locacion = new Location(location);
			locacion.reset();

			try {
				GeoPoint centro = mapa.getMapCenter();

				double lat = centro.getLatitudeE6();
				double lng = centro.getLongitudeE6();

				mapController.animateTo(centro);
				String posicion = "Lat: " + lat / 1000000 + "\nLon: " + lng / 1000000;
				mostrarPosicion.setText("Tu posicion actual es:\n" + posicion);				

			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	// Muestra en el mapa la locacion dada.
	private void nuevaPosicion(Location locacion) {

		String posicion;
		mostrarPosicion = (TextView) findViewById(R.id.textoPosicion);

		if (locacion != null) {

			// Update the map location.
			Double geoLat = locacion.getLatitude() * 1E6;
			Double geoLng = locacion.getLongitude() * 1E6;
			GeoPoint point = new GeoPoint(geoLat.intValue(), geoLng.intValue());
			mapController.animateTo(point);

			double lat = locacion.getLatitude();
			double lng = locacion.getLongitude();
			posicion = "Lat: " + lat + "\nLon: " + lng;

		} else {
			posicion = "No se encontro una posicion";
		}

		mostrarPosicion.setText("Tu posicion actual es:\n" + posicion);
	}

	// Referencia y opciones del mapa a mostrar
	private MapView mapa() {
		MapView mapa = (MapView) findViewById(R.id.vistaMapa);

		// Se obtiene el controlador del mapa
		mapController = mapa.getController();

		mapa.setSatellite(true);
		mapa.setStreetView(true);
		mapa.setClickable(true);
		mapa.displayZoomControls(false);
		mapController.setZoom(17);

		return mapa;

	}

	// Criterios para determinar el mejor proveedor de LBS (Location Based
	// Service)
	private Criteria criteriosProveedor() {

		Criteria criteria = new Criteria();

		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);

		return criteria;

	}
}