/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")savedInstanceState;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.innova.android.retoAndroid;

import java.util.Vector;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TNotas extends ListActivity {

	private static String TREASURE_PROXIMITY_ALERT = "com.paad.treasurealert";

	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;

	// Id de elementos del menu ppal
	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;

	private TNotasDAO mDbHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.notes_list);

		mDbHelper = new TNotasDAO(this);
		mDbHelper.open();
		fillData();
		registerForContextMenu(getListView());

//		LocationManager locationManager;
//		String context = Context.LOCATION_SERVICE;
//
//		locationManager = (LocationManager) getSystemService(context);
//
//		Criteria criteria = new Criteria();
//		criteria.setAccuracy(Criteria.ACCURACY_FINE);
//		criteria.setAltitudeRequired(false);
//		criteria.setBearingRequired(false);
//		criteria.setCostAllowed(true);
//		criteria.setPowerRequirement(Criteria.POWER_LOW);
//
//		String provider = locationManager.getBestProvider(criteria, true);
//		Location location = locationManager.getLastKnownLocation(provider);
//		// updateWithNewLocation(location);
//
//
//		Cursor notesCursor = mDbHelper.fetchAllCoor();
//		startManagingCursor(notesCursor);
//
//		notesCursor.moveToFirst();
//
//		Vector<Double> lat = new Vector();
//		Vector<Double> lng = new Vector();
//
//		while (notesCursor.moveToNext()) {
//
//			lat.add(notesCursor.getDouble(notesCursor.getColumnIndex(TNotasDAO.KEY_LAT)));
//			lng.add(notesCursor.getDouble(notesCursor.getColumnIndex(TNotasDAO.KEY_LNG)));
//		}
//
//		// Datos alerta
////		double lat = 6.204028;
////		double lng = -75.571206;
//		float radius = 100f;
//		long expiration = -1;
//
//		// Alerta
//		Intent intent = new Intent(TREASURE_PROXIMITY_ALERT);
//
//		PendingIntent proximityIntent = PendingIntent.getBroadcast(this, -1,
//				intent, 0);
//
//
//		int i = 0;
//		while (i <= lat.size()) {
//			Double lati = (Double) lat.get(i);
//			Double lngi = (Double) lng.get(i);
//			i++;
//
//			if ((lati != null && lati != 0.0) && (lngi != null && lngi != 0.0)) {
//			locationManager.addProximityAlert(lati, lngi, radius, expiration,
//					proximityIntent);
//			}
//		}
//
//		// Filtro para la alerta
//		IntentFilter filter = new IntentFilter(TREASURE_PROXIMITY_ALERT);
//		registerReceiver(new ProximityIntentReceiver(), filter);
	}

//	public class ProximityIntentReceiver extends BroadcastReceiver {
//
//		@Override
//		public void onReceive(Context context, Intent intent) {
//
//			AlertDialog.Builder builder = new AlertDialog.Builder(context);
//			builder.setMessage("Actividad cerca").setTitle("Alerta")
//					.setCancelable(false).setPositiveButton("Aceptar",
//							new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog,
//										int id) {
//									// finish();
//								}
//							}).show();
//		}
//	}

	/**
	 * Se llena la lista de notas
	 */
	private void fillData() {
		Cursor notesCursor = mDbHelper.fetchAllNotes();
		startManagingCursor(notesCursor);

		// Array con titulos de las notas
		String[] from = new String[] { TNotasDAO.KEY_TITLE };

		// and an array of the fields we want to bind those fields to (in this
		// case just text1)
		int[] to = new int[] { R.id.text1 };

		// Now create a simple cursor adapter and set it to display
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this,
				R.layout.notes_row, notesCursor, from, to);
		setListAdapter(notes);
	}

	/**
	 * Se crean las opciones del menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, R.string.menu_insert);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
		return true;
	}

	/**
	 * Accion que se hace al presionar alguna opcion del menu
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			createNote();
			return true;

		case DELETE_ID:
			mDbHelper.deleteNote(getListView().getSelectedItemId());
			fillData();
		}

		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * Se crea una nota
	 */
	private void createNote() {
		try {
			Intent i = new Intent(this, TEditarNota.class);
			startActivityForResult(i, ACTIVITY_CREATE);
		} catch (Exception e) {
		}
	}

	/**
	 * Accion que se hace al seleccionar una nota de la lista
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Intent i = new Intent(this, TEditarNota.class);
		i.putExtra(TNotasDAO.KEY_ROWID, id);

		startActivityForResult(i, ACTIVITY_EDIT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		fillData();
	}
}
