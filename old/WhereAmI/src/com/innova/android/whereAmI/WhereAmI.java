package com.innova.android.whereAmI;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;


public class WhereAmI extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
    	super.onCreate(icicle);
    	setContentView(R.layout.main);
    	
    	LocationManager locationManager;
    	String context = Context.LOCATION_SERVICE;
    	
    	locationManager = (LocationManager)getSystemService(context);
    	
    	String provider = LocationManager.GPS_PROVIDER;
    	Location location = locationManager.getLastKnownLocation(provider);
    	
    	updateWithNewLocation(location);

    }
    
    private void updateWithNewLocation(Location location) {
    	
    	String latLongString;
    	TextView myLocationText;
    	myLocationText = (TextView)findViewById(R.id.myLocationText);
    	
    	if (location != null) {
    		
    	  double lat = location.getLatitude();
    	  double lng = location.getLongitude();
    	  latLongString = "Latitud:" + lat + "\nLongitud:" + lng;
    	  
    	} else {
    	  latLongString = "No se encontro una posición";
    	}
    	
    	myLocationText.setText("Tu posición actual es:\n" + latLongString);
    }

}