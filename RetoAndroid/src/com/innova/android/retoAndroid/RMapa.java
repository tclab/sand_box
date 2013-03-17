package com.innova.android.retoAndroid;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class RMapa extends MapActivity {

	private static final int INSERT_ID = Menu.FIRST;

	public static final int GUARDAR = 2;
	public static final int MOSTRAR = 1;

	MapController mapController;
	Location location;
	MapView mapa;
	TextView mostrarPosicion;
	Double latitud;
	Double longitud;

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

		try {
			super.onCreate(icicle);
			setContentView(R.layout.note_map);
			mapa = mapa();
			
			String context = Context.LOCATION_SERVICE;
			LocationManager locationManager;
			locationManager = (LocationManager) getSystemService(context);
			String provider = locationManager.getBestProvider(
					criteriosProveedor(), true);
			
			location = locationManager.getLastKnownLocation(provider);
			nuevaPosicion(location);
			
			locationManager.requestLocationUpdates(provider, 2000, 10,
					locationListener);
			
			// Se mira si se esta editando una posicion
			Bundle extras = getIntent().getExtras();

			latitud = extras != null ? extras.getDouble(RNotasDAO.KEY_LAT) : null;
			longitud = extras != null ? extras.getDouble(RNotasDAO.KEY_LNG) : null;

			if ((latitud != null && latitud != 0.0) && (longitud != null && longitud != 0.0)) {

				Double lati = latitud * 1E6;
				Double lngi = longitud * 1E6;

				GeoPoint point = new GeoPoint(lati.intValue(), lngi.intValue());
				
				String posicion = "Lat: " + latitud + "\nLon: " + longitud;
				mostrarPosicion.setText("Tu posicion actual es:\n"
						+ posicion);

				mapController.animateTo(point);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * Se crea el menu de la vista mapa
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, MOSTRAR, 0, R.string.menu_mostrar);
		menu.add(0, GUARDAR, 0, R.string.menu_guardarMapa);
		return true;
	}

	/**
	 * Accion que se hace al presionar alguna opcion del menu
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		Location locacion = new Location(location);

		try {
			switch (item.getItemId()) {
			case MOSTRAR:
				locacion.reset();

				try {
					GeoPoint centro = mapa.getMapCenter();

					double lat = centro.getLatitudeE6();
					double lng = centro.getLongitudeE6();

					latitud = lat / 1000000;
					longitud = lng / 1000000;

					mapController.animateTo(centro);
					String posicion = "Lat: " + latitud + "\nLon: " + longitud;
					mostrarPosicion.setText("Tu posicion actual es:\n"
							+ posicion);

				} catch (Exception e) {
					e.printStackTrace();
				}

				return true;

			case GUARDAR:
				Bundle bundle = new Bundle();
				locacion.reset();

				try {
					GeoPoint centro = mapa.getMapCenter();

					double lat = centro.getLatitudeE6();
					double lng = centro.getLongitudeE6();

					latitud = lat / 1000000;
					longitud = lng / 1000000;

					bundle.putDouble(RNotasDAO.KEY_LAT, latitud);
					bundle.putDouble(RNotasDAO.KEY_LNG, longitud);

					Intent intent = new Intent();
					intent.putExtras(bundle);
					setResult(RESULT_OK, intent);
					finish();

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			}
		} catch (Exception e) {
		}
		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * Muestra en el mapa la locacion dada.
	 * 
	 * @param locacion
	 */
	private void nuevaPosicion(Location locacion) {

		try {
			String posicion;
			mostrarPosicion = (TextView) findViewById(R.id.textoPosicion);
			if (locacion != null) {

				// Update the map location.
				Double geoLat = locacion.getLatitude() * 1E6;
				Double geoLng = locacion.getLongitude() * 1E6;
				GeoPoint point = new GeoPoint(geoLat.intValue(), geoLng
						.intValue());
				mapController.animateTo(point);

				double lat = locacion.getLatitude();
				double lng = locacion.getLongitude();
				posicion = "Lat: " + lat + "\nLon: " + lng;

			} else {
				posicion = "No se encontro una posicion";
			}
			mostrarPosicion.setText("Tu posicion actual es:\n" + posicion);
		} catch (Exception e) {
		}
	}

	/**
	 * Referencia y opciones del mapa a mostrar
	 * 
	 * @return
	 */
	private MapView mapa() {
		MapView mapa;

		mapa = (MapView) findViewById(R.id.vistaMapa);

		try {
			// Se obtiene el controlador del mapa
			mapController = mapa.getController();
			mapa.setSatellite(true);
			mapa.setStreetView(true);
			mapa.setClickable(true);
			mapa.displayZoomControls(false);
			mapController.setZoom(17);

		} catch (Exception e) {
		}

		return mapa;

	}

	/**
	 * Criterios para determinar el mejor proveedor de LBS (Location
	 * BasedService)
	 * 
	 * @return
	 */
	private Criteria criteriosProveedor() {

		Criteria criteria = new Criteria();

		try {
			criteria.setAccuracy(Criteria.ACCURACY_FINE);
			criteria.setAltitudeRequired(false);
			criteria.setBearingRequired(false);
			criteria.setCostAllowed(true);
			criteria.setPowerRequirement(Criteria.POWER_LOW);
		} catch (Exception e) {
		}
		return criteria;

	}
}