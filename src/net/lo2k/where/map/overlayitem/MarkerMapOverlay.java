package net.lo2k.where.map.overlayitem;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

@SuppressWarnings("unchecked")
public class MarkerMapOverlay extends ItemizedOverlay  {
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Drawable marker;
	
	public MarkerMapOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		marker = defaultMarker;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
		boundCenterBottom(marker);
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}    
}
