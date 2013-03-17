package com.radioactiveyak.wamf;

import java.util.HashMap;
import java.util.Iterator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.location.Location;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

/**
 * Overlay class used to draw circles on the locations
 * of each contact in the contact list, along with their 
 * name and a line connecting them to your current
 * position.
 * 
 * @author Reto Meier
 * Author of Professional Android Application Development
 * http://www.amazon.com/gp/product/0470344717?tag=interventione-20
 *
 */
public class FriendLocationOverlay extends Overlay {

  private Context context;
  private HashMap<String, Location> friendLocations;
  private Location location;
  private GeoPoint locationPoint;
  
  private Paint paint;
  private Paint backPaint;

  private static int markerRadius = 7;

  /** Get your current location */
	public Location getLocation() {
		return location;
	}
  /** Set your current location */
	public void setLocation(Location location) {
	  this.location = location;
		
      Double latitude = location.getLatitude()*1E6;
      Double longitude = location.getLongitude()*1E6;
    
      locationPoint = new GeoPoint(latitude.intValue(),longitude.intValue());      
	}  
    
	/** Refresh the locations of each of the contacts */
  public void refreshFriendLocations() {
    friendLocations = FriendLocationLookup.GetFriendLocations(context);
  }
  
  /**
   * Create a new FriendLocationOverlay to show your contact's locations on a map
   * @param _context Parent application context
   */
  public FriendLocationOverlay(Context _context) {
    super();
    
    context = _context;
    friendLocations = new HashMap<String, Location>();
    //refreshFriendLocations();

    // Create the paint objects
    backPaint = new Paint();
    backPaint.setARGB(200, 200, 200, 200);
    backPaint.setAntiAlias(true);
    
    paint = new Paint();
    paint.setARGB(255, 10, 10, 255);
    paint.setAntiAlias(true);
    paint.setFakeBoldText(true);    
  }
  
  @Override
  public void draw(Canvas canvas, MapView mapView, boolean shadow) {	  

    // Get the map projection to convert lat/long to screen coordinates
    Projection projection = mapView.getProjection();
    
    Point lPoint = new Point();
    projection.toPixels(locationPoint, lPoint);
    
    // Draw the overlay
    if (shadow == false) {
      if (friendLocations.size() > 0) {
        Iterator<String> e = friendLocations.keySet().iterator();
        do {
          // Get the name and location of each contact
          String name = e.next();          
          Location location = friendLocations.get(name);
          
          // Convert the lat / long to a Geopoint
          Double latitude = location.getLatitude()*1E6;
          Double longitude = location.getLongitude()*1E6;
          GeoPoint geopoint = new GeoPoint(latitude.intValue(),longitude.intValue());

          // Ensure each contact is within 10km
          float dist = location.distanceTo(getLocation()); 
          if (dist < 10000) {
            Point point = new Point();
            projection.toPixels(geopoint, point);
            
            // Draw a line connecting the contact to your current location.
            canvas.drawLine(lPoint.x, lPoint.y, point.x, point.y, paint);
            
            // Draw a marker at the contact's location.
            RectF oval = new RectF(point.x-markerRadius,
                                   point.y-markerRadius,
                                   point.x+markerRadius,
                                   point.y+markerRadius);
            
            canvas.drawOval(oval, backPaint);
            oval.inset(2, 2);
            canvas.drawOval(oval, paint);
            
            // Draw the contact's name next to their position.
            float textWidth = paint.measureText(name);
            float textHeight = paint.getTextSize();
            RectF textRect = new RectF(point.x+markerRadius, point.y-textHeight,
                                       point.x+markerRadius+8+textWidth, point.y+4);
            canvas.drawRoundRect(textRect, 3, 3, backPaint);
            canvas.drawText(name, point.x+markerRadius+4, point.y, paint);
          }
        } while (e.hasNext());
      }
    }
    super.draw(canvas, mapView, shadow);
  }
	  
	@Override
	public boolean onTap(GeoPoint point, MapView mapView) {
	  // Do not react to screen taps.
	  return false;
	}
}