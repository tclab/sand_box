package com.innova.android.TestProviderController;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

public class TestProviderController extends Activity {

	LocationManager locationManager;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		String location_context = Context.LOCATION_SERVICE;
		locationManager = (LocationManager) getSystemService(location_context);
		testProviders();
	}

	public void testProviders() {
    	
    	TextView tv = (TextView)findViewById(R.id.myTextView);
    	StringBuilder sb = new StringBuilder("Enabled Providers:");
    	
    	List<String> providers = locationManager.getProviders(true);
    	
    	for (String provider : providers) {
    		locationManager.requestLocationUpdates(provider, 1000, 0,
                    new LocationListener() {
    			
				public void onLocationChanged(Location location) {}
				public void onProviderDisabled(String provider){}
				public void onProviderEnabled(String provider){}
				public void onStatusChanged(String provider, int status,Bundle extras){}
				});
    		
    		  sb.append("\n").append(provider).append(": ");
    		  Location location = locationManager.getLastKnownLocation(provider);
    		  
    		  if (location != null) {
    		    double lat = location.getLatitude();
    		    double lng = location.getLongitude();
    		    sb.append(lat).append(", ").append(lng);
    		  } else {
    		    sb.append("No Location");
    		  }
    		  
    	}
    	tv.setText(sb);
    }
}