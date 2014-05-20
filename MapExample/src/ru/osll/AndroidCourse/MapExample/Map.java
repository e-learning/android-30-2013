package ru.osll.AndroidCourse.MapExample;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;

public class Map extends Activity {
















    private GoogleMap m_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        m_map = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragment)).getMap();
        m_map.setMyLocationEnabled(true);
        m_map.setTrafficEnabled(true);

        //m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        m_map.addMarker(new MarkerOptions()
                .position(new LatLng(59.95, 30.30))
                .title("Hello world").title("Hello world"));




    }
}