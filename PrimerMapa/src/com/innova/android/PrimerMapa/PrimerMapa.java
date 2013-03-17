package com.innova.android.PrimerMapa;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class PrimerMapa extends MapActivity {
    
	private MapView mapView;
	private MapController mapController;
	
    @Override
    public void onCreate(Bundle icicle) {
    	  super.onCreate(icicle);
    	  setContentView(R.layout.main);
    	  mapView = (MapView)findViewById(R.id.map_view);
    	}
    	@Override
    	protected boolean isRouteDisplayed() {
    	  return false;
    	}

}