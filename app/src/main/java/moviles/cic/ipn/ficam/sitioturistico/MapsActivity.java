package moviles.cic.ipn.ficam.sitioturistico;

import android.content.Intent;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.provider.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class MapsActivity extends SupportMapFragment implements OnMapReadyCallback ,
        LocationListener {
    // Atributos de la clase MapsActivity.
    private LatLng currentLocation = new LatLng(19.432608, -99.133208);
    private boolean moveCameraCurrentLocation = true;
    private static final int DEFAULT_ZOOM_LEVEL = 19;
    private GoogleMap mMap;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        getMapAsync(this);
        return rootView;
    }

    // Changes current camera locatio to new position.
    public void moveCamera(LatLng newLocation) {
        if (this.mMap == null)
            return;
        this.mMap.moveCamera(CameraUpdateFactory.newLatLng(newLocation));
    }

    // Create a marker into the map.
    public void addMarker(LatLng location, String caption) {
        if (this.mMap == null)
            return;
        this.mMap.addMarker(new MarkerOptions().position(location).title(caption));
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addCitiesMarkers();

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void addCitiesMarkers() {
        this.addMarker(new LatLng(19.432608, -99.133208), "Ciudad de Mexico");
        this.addMarker(new LatLng(35.689487, 139.691706), "Tokio, Japon");
        this.addMarker(new LatLng(-33.868820, 151.209296), "Sidney, Australia");
        this.addMarker(new LatLng(45.501689, -73.567256), "Montreal, Canada");
        this.addMarker(new LatLng(39.904200, 116.407396), "Pekin, China");
        this.addMarker(new LatLng(48.856614, 2.352222), "Paris, Francia");
        this.addMarker(new LatLng(-22.906847, -43.172896), "Rio de Janeiro, Brasil");
        this.addMarker(new LatLng(52.520007, 13.404954), "Berlin, Alemania");
        this.addMarker(new LatLng(40.712775, -74.005973), "New York, EUA");
        this.addMarker(new LatLng(41.902783, 12.496366), "Roma, Italia");
    }


    @Override
    public void onLocationChanged(Location location) {
        // This method is called when the location changes.
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        this.currentLocation = new LatLng(latitude, longitude);
        Log.d("onLocationChanged", "Latitud:" + latitude + " Longitud:" + longitude);
        this.mMap.clear();
        this.mMap.addMarker(new MarkerOptions()
                .position(this.currentLocation)
                .title("PosiciÃ³n Actual")
                .icon(BitmapDescriptorFactory.defaultMarker(new Random().nextInt(360))));
        addCitiesMarkers();
        if (this.moveCameraCurrentLocation) {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(this.currentLocation)
                    .zoom(DEFAULT_ZOOM_LEVEL)
                    .build();
            this.mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }
}