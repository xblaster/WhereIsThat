package net.lo2k.where;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class GetPosition extends MapActivity {
	private ProgressDialog dialog;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_pos);
        
        dialog = ProgressDialog.show(GetPosition.this, "", 
                "Retrieve coordinate", true);
        
      //define locationManager
		final LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		final LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location
				// provider.
				makeUseOfNewLocation(location);
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};
		
		makeUseOfNewLocation(locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
		
		
		
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	}
	
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
/*        
        //Button btaction = (Button) findViewById(R.id.takeNewLoc);
        
        //define locationManager
		final LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		final LocationListener locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location
				// provider.
				makeUseOfNewLocation(location);
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};
		
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		
		/*btaction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				locationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
			}
		});
    }
*/
	protected void makeUseOfNewLocation(Location location) {
		
		if (location==null) {
			return;
		}
		
		dialog.hide();
		
		
		//TextView text = (TextView) findViewById(R.id.gpsCoord);
		MapView map = (MapView) findViewById(R.id.mapview);
		map.setClickable(true);
		map.setBuiltInZoomControls(true);
		GeoPoint p = new GeoPoint((int)(location.getLatitude()*1E6), (int)(location.getLongitude()*1E6));
		MapController mc = map.getController();
		mc.animateTo(p);
		
		//geocode !
		Geocoder geoCoder = new Geocoder(
                getBaseContext(), Locale.getDefault());
		
		try {
            List<Address> addresses = geoCoder.getFromLocation(
                p.getLatitudeE6()  / 1E6, 
                p.getLongitudeE6() / 1E6, 1);

            String add = "";
            if (addresses.size() > 0) 
            {
                for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); 
                     i++)
                   add += addresses.get(0).getAddressLine(i) + "\n";
            }

            Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {                
            e.printStackTrace();
        } 
		//map.a
		//map.
		//text.setText(location.toString());
	}
}
