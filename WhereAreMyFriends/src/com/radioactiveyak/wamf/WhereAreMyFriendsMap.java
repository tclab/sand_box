package com.radioactiveyak.wamf;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Map based Activity screen that shows your 
 * current location and each of your contacts
 * within 10km with lines connecting you to them.
 * 
 * @author Reto Meier
 * Author of Professional Android Application Development
 * http://www.amazon.com/gp/product/0470344717?tag=interventione-20
 *
 */
public class WhereAreMyFriendsMap extends MapActivity {

  private MapController mapController;
  private FriendLocationOverlay positionOverlay;
  private Criteria criteria;
  private LocationManager locationManager;
  private MapView myMapView;
 	
  static final private int MENU_ITEM_LIST = Menu.FIRST;
  static final private int MENU_ITEM_REFRESH = Menu.FIRST + 1;

	@Override
	public void onCreate(Bundle icicle) {
	  super.onCreate(icicle);
	  setContentView(R.layout.map_layout);

	  // Get the MapView and its Controller
    myMapView = (MapView)findViewById(R.id.myMapView);
	  mapController = myMapView.getController();
	    
	  // Configure the map display options
	  myMapView.setSatellite(false);
	  myMapView.setStreetView(false);
	           
	  // Zoom in
	  mapController.setZoom(15);
        
    // Add my friends position overlay
	  positionOverlay = new FriendLocationOverlay(getApplicationContext());
	  List<Overlay> overlays = myMapView.getOverlays();
	  overlays.add(positionOverlay);
	
	  // Add the MyLocationOverlay
    MyLocationOverlay myLocationOverlay = new MyLocationOverlay(this, myMapView);
    overlays.add(myLocationOverlay);
    myLocationOverlay.enableMyLocation();

    // Get the location manager
	  locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
	
	  // Set the criteria for selection a location provider
	  criteria = new Criteria();
	  criteria.setAccuracy(Criteria.ACCURACY_FINE);
	  criteria.setAltitudeRequired(false);
	  criteria.setBearingRequired(false);
	  criteria.setCostAllowed(true);
	  criteria.setPowerRequirement(Criteria.POWER_LOW);
	  
      // Refresh the hash of contact locations.
	  positionOverlay.refreshFriendLocations();
	}

	@Override
	public void onStart() {
		super.onStart();
		
		// Find an available provider to use which matches the criteria
    String provider = locationManager.getBestProvider(criteria, true);
	
    // Update the UI using the last known locations
    Location location = locationManager.getLastKnownLocation(provider);
    updateWithNewLocation(location);
	
    // Start listening for location changes
    locationManager.requestLocationUpdates(provider, 
                                           60000, // 1min
                                           1000,  // 1km
                                           locationListener);
	}
	
	@Override 
	public void onStop() {
	  // Stop listening for location changes 
	  locationManager.removeUpdates(locationListener);
	 
	  super.onStop();
	}
	
  private final LocationListener locationListener = new LocationListener() {
    public void onLocationChanged(Location location) {
      updateWithNewLocation(location);
    }
   
    public void onProviderDisabled(String provider){
      updateWithNewLocation(null);
    }

    public void onProviderEnabled(String provider) {}
    public void onStatusChanged(String provider, int status, Bundle extras) {}
  };
  
  /** Update the map with a new location */
	private void updateWithNewLocation(Location location) {
    // Update the map position and overlay
	  if (location != null) {
	    // Update my location marker
	    positionOverlay.setLocation(location);
	    myMapView.invalidate();

	    // Update the map location.
	    Double geoLat = location.getLatitude()*1E6;
	    Double geoLng = location.getLongitude()*1E6;
	    GeoPoint point = new GeoPoint(geoLat.intValue(), geoLng.intValue());

	    mapController.animateTo(point);
	  }
	  
	  // Update the text box that displays your current address
	  String latLongString = "";
    String addressString = "No address found";
    TextView myLocationText = (TextView)findViewById(R.id.myLocationText);

	  if (location != null) {
      double lat = location.getLatitude();
	    double lng = location.getLongitude();
	    latLongString = "Lat:" + lat + "\nLong:" + lng;

	    Geocoder gc = new Geocoder(this, Locale.getDefault());
	    try {
	      List<Address> addresses = gc.getFromLocation(lat, lng, 1);
	      StringBuilder sb = new StringBuilder();
	      if (addresses.size() > 0) {
	        Address address = addresses.get(0);

          sb.append(address.getLocality()).append("\n");
          sb.append(address.getCountryName());
	      }
	      addressString = sb.toString();
	    } catch (IOException e) {}
	  } else {
	    latLongString = "No location found";
	  }
	  myLocationText.setText("Your Current Position is:\n" + latLongString + "\n" + addressString);
	}

  @Override
	protected boolean isRouteDisplayed() {
	  return false;
	}
    
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    menu.add(0, MENU_ITEM_LIST, Menu.NONE, R.string.menu_item_list);
    menu.add(0, MENU_ITEM_REFRESH, Menu.NONE, R.string.menu_item_refresh);
    return true;
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    super.onOptionsItemSelected(item);

    switch (item.getItemId()) {
      // Check for each known menu item
      case (MENU_ITEM_LIST):
        // Display the List View
        startActivity(new Intent(this, WhereAreMyFriends.class));
        return true;
      case (MENU_ITEM_REFRESH) :
        // Refresh the contact location hash
        positionOverlay.refreshFriendLocations();
        myMapView.invalidate(); 
        return true;
    }
    
    // Return false if you have not handled the menu item.
    return false;
  }
}