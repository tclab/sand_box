package com.innova.android.LocationTracker;



import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class LocationTracker extends Activity {

	private final LocationListener locationListener = new LocationListener() {
		   public void onLocationChanged(Location location) {
		     updateWithNewLocation(location);
		   }
		   public void onProviderDisabled(String provider){
		     updateWithNewLocation(null);
		   }
		   public void onProviderEnabled(String provider){ }
		   public void onStatusChanged(String provider, int status,
		                               Bundle extras){ }
		};

	
    @Override
    public void onCreate(Bundle icicle) {
    	super.onCreate(icicle);
    	setContentView(R.layout.main);
    	
    	LocationManager locationManager;
    	String context = Context.LOCATION_SERVICE;
    	
    	locationManager = (LocationManager)getSystemService(context);
    	
    	Criteria criteria = new Criteria();
    	criteria.setAccuracy(Criteria.ACCURACY_FINE);
    	criteria.setAltitudeRequired(false);
    	criteria.setBearingRequired(false);
    	criteria.setCostAllowed(true);
    	criteria.setPowerRequirement(Criteria.POWER_LOW);
    	
    	String provider = locationManager.getBestProvider(criteria, true);
    	
//    	String provider = LocationManager.GPS_PROVIDER;
    	Location location = locationManager.getLastKnownLocation(provider);
    	
    	updateWithNewLocation(location);
    	
    	locationManager.requestLocationUpdates(provider, 2000, 10,locationListener);

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