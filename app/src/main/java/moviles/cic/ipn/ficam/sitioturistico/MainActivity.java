package moviles.cic.ipn.ficam.sitioturistico;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.location.LocationManager;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity  {

    Button buttonSitioMexico;
    Button buttonSitioTokio;
    Button buttonSitioSydney;
    Button buttonSitioMontreal;
    Button buttonSitioPekin;
    Button buttonSitioParis;
    Button buttonSitioRio;
    Button buttonSitioBerlin;
    Button buttonSitioNewYork;
    Button buttonSitioRoma;

    MapsActivity mapsActivity;
    LocationManager locationManager;
    private int timeUpdateLocation = 2000;
    private float distanceUpateLocation = (float)0.05;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        this.mapsActivity = (MapsActivity) getSupportFragmentManager().findFragmentById(R.id.fragmentGoogleMaps);
        // Set OnClickListeners methods.
        buttonSitioMexico = findViewById(R.id.buttonSitioMexico);
        buttonSitioMexico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsActivity.moveCamera(new LatLng(19.432608, -99.133208));
            }
        });
        buttonSitioTokio = findViewById(R.id.buttonSitioTokio);
        buttonSitioTokio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsActivity.moveCamera(new LatLng(35.689487, 139.691706));
            }
        });
        buttonSitioSydney = findViewById(R.id.buttonSitioSidney);
        buttonSitioSydney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsActivity.moveCamera(new LatLng(-33.868820, 151.209296));
            }
        });
        buttonSitioPekin = findViewById(R.id.buttonSitioPekin);
        buttonSitioPekin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsActivity.moveCamera(new LatLng(39.904200, 116.407396));
            }
        });
        buttonSitioMontreal = findViewById(R.id.buttonSitioMontreal);
        buttonSitioMontreal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsActivity.moveCamera(new LatLng(45.501689, -73.567256));
            }
        });
        buttonSitioParis = findViewById(R.id.buttonSitioParis);
        buttonSitioParis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsActivity.moveCamera(new LatLng(48.856614, 2.352222));
            }
        });
        buttonSitioRio = findViewById(R.id.buttonSitioRio);
        buttonSitioRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsActivity.moveCamera(new LatLng(-22.906847, -43.172896));
            }
        });
        buttonSitioBerlin = findViewById(R.id.buttonSitioBerlin);
        buttonSitioBerlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsActivity.moveCamera(new LatLng(52.520007, 13.404954));
            }
        });
        buttonSitioNewYork = findViewById(R.id.buttonSitioNewYork);
        buttonSitioNewYork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsActivity.moveCamera(new LatLng(40.712775, -74.005973));
            }
        });
        buttonSitioRoma = findViewById(R.id.buttonSitioRoma);
        buttonSitioRoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapsActivity.moveCamera(new LatLng(41.902783, 12.496366));
            }
        });

        initLocationService();
    }

    private void initLocationService() {
        this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Log.d("initLocationService", "Registrando Servicio....");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                requestPermissions(new String[] {
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.INTERNET
                }, 10);
                return;
            }
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    timeUpdateLocation, distanceUpateLocation, this.mapsActivity);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode)
        {
            case 10:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            timeUpdateLocation, distanceUpateLocation, this.mapsActivity);
                return;
        }
    }
}
