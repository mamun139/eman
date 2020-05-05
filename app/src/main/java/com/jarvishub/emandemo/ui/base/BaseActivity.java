package com.jarvishub.emandemo.ui.base;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.model.eman_list.EmansLocationData;
import com.jarvishub.emandemo.ui.home.IMapView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by mrrobot on 9/18/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements OnMapReadyCallback, EasyPermissions.PermissionCallbacks {
    private String TAG = "BaseActivity";

    GoogleMap mMap;
    Location userLocation;
    Marker fromMarker, toMarker;
    private LatLng destination;
    private List<LatLng> listLatLng = new ArrayList<>();
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 100;
    public static final int PLACE_AUTOCOMPLETE_TYPE_LOCALITY = 1009;
    public static int MAP_ZOOM_SIZE = 17;
    public static String LOCATION_TO_TAG = "Delivery";
    public static String LOCATION_FROM_TAG = "PickUp";
    private Map<String, String> addressList;

    private FusedLocationProviderClient mFusedLocationClient;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 4000;
    private IMapView mapView;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;
    protected LocationRequest locationRequest;
    int REQUEST_CHECK_SETTINGS = 100;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        configureCameraIdle();
    }

    protected void setView(IMapView view) {
        this.mapView = view;
    }


    private void configureCameraIdle() {
        onCameraIdleListener = () -> {
            Double lat, lng;
            String address;
            LatLng latLng = mMap.getCameraPosition().target;
            Geocoder geocoder = new Geocoder(BaseActivity.this);

            try {
                List<Address> addressList1 = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                if (addressList1 != null && addressList1.size() > 0) {

                    // String subLocality = addressList1.get(0).getSubLocality();
                    String locality = addressList1.get(0).getAddressLine(0);
                    String country = addressList1.get(0).getCountryName();
                    lat = addressList1.get(0).getLatitude();
                    lng = addressList1.get(0).getLongitude();
                    if (!locality.isEmpty() && !country.isEmpty()) {
                        address = locality;
                        mapView.onDragLocChange(lat, lng, address);
                        Log.e(TAG, "onCameraIdle: .................................... drag " + address + " latitude " + lat + " longitude " + lng);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e(TAG, "onMapReady: ........................................... ");
        mMap = googleMap;
        mMap.setMaxZoomPreference(20);

        mMap.setOnCameraIdleListener(onCameraIdleListener);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, new MyLocationListener());
        Criteria criteria = new Criteria();

        if (checkPermission()) onLocationPermissionGranted();
    }

    private void demoMapLocation(){
        LatLng initialLoc= new LatLng(23.7657978,90.3635862);
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                initialLoc, 15);
        mMap.animateCamera(location);
    }

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    private void onLocationPermissionGranted() {
        if (!checkPermission()) return;

        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setMyLocationEnabled(true);
        //mMap.setPadding(0, 360, 0, 0);  change my location button enable
        mMap.setBuildingsEnabled(true);
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {

                    if (location != null) {
                        userLocation = location;
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()))
                                .zoom(17)
                                .build();
                        mapView.PickUpAddress(userLocation.getLatitude(), userLocation.getLongitude(), "dhaka,Bangladesh");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            addOverlay(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()));
                        }
                        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                    } else {
                        userLocation = null;
                    }
                });

    }


    /**
     * auto complete map location
     */
    public void openPlaceAutoCompleteView() {
        mMap.clear();
        this.listLatLng.clear();
        try {
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder().setCountry("BD").setTypeFilter(PLACE_AUTOCOMPLETE_TYPE_LOCALITY).build();
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).setFilter(typeFilter).build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Ask for the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            Toast.makeText(this, "Please give location permission", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public Location getUserLocation() {
        if (userLocation != null)
            return userLocation;

        return null;
    }

    public LatLng getDestinationLatLong() {

        if (destination != null)
            return destination;
        else
            return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                destination = place.getLatLng();
                Log.d(TAG, place.getAddress() + "onActivityResult: ................................ " + destination.latitude);
                //setUpPolyLine();

                mapView.autocompleteSuggestionAddress(destination.latitude, destination.longitude, String.valueOf(place.getAddress()));

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Toast.makeText(this, "Error " + status, Toast.LENGTH_SHORT).show();

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (checkPermission()) {
                onLocationPermissionGranted();
            } else {
                Toast.makeText(this, "Location permission denied.", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    protected void addMarker(LatLng destination) {

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(destination, MAP_ZOOM_SIZE);
        /*MarkerOptions options = new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                .title(LOCATION_TO_TAG)
                .draggable(true)
                .position(destination);*/

        mMap.moveCamera(update);
        mMap.animateCamera(update);
        //mMap.addMarker(options);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void addCollectionPointMarker(LatLng destination) {

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(destination, MAP_ZOOM_SIZE);
        MarkerOptions options = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(drawableToBitmap(getDrawable(R.drawable.collection_marker))))
                .title(LOCATION_TO_TAG)
                //.draggable(true)
                .position(destination);

        mMap.moveCamera(update);
        mMap.animateCamera(update);
        mMap.addMarker(options);

    }

    /*
        public void gotoLocationZoom(String locality, double lat, double lng, float zoom) {
       *//* if (toMarker != null){
            toMarker.remove();
            toMarker = null;
        }*//*
       toMarker = markerCheck(toMarker);
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);

        mMap.moveCamera(update);

        MarkerOptions options = new MarkerOptions()
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                .title(LOCATION_TO_TAG)
                .draggable(true)
                .position(new LatLng(lat, lng));

        mMap.animateCamera(update);
        toMarker = mMap.addMarker(options);
    }*/
    public void setEmanMarker(List<EmansLocationData> eManLocationsList) {

        double lat, lng;
        for (EmansLocationData eManLoc : eManLocationsList) {

            if (eManLoc.getEmanId() > 0) {
                lat = eManLoc.getLatitute();
                lng = eManLoc.getLongitude();
                //LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions options = new MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .draggable(false)
                        .position(new LatLng(lat, lng));
                fromMarker = mMap.addMarker(options);
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addOverlay(LatLng place) {

        GroundOverlay groundOverlay = mMap.addGroundOverlay(new
                GroundOverlayOptions()
                .position(place, 100)
                .transparency(0.5f)
                .zIndex(3)
                .image(BitmapDescriptorFactory.fromBitmap(drawableToBitmap(getDrawable(R.drawable.map_overlay)))));

        startOverlayAnimation(groundOverlay);
    }


    private void startOverlayAnimation(final GroundOverlay groundOverlay) {

        AnimatorSet animatorSet = new AnimatorSet();

        ValueAnimator vAnimator = ValueAnimator.ofInt(0, 100);
        vAnimator.setRepeatCount(ValueAnimator.INFINITE);
        vAnimator.setRepeatMode(ValueAnimator.RESTART);
        vAnimator.setInterpolator(new LinearInterpolator());
        vAnimator.addUpdateListener(valueAnimator -> {
            final Integer val = (Integer) valueAnimator.getAnimatedValue();
            groundOverlay.setDimensions(val);

        });

        ValueAnimator tAnimator = ValueAnimator.ofFloat(0, 1);
        tAnimator.setRepeatCount(ValueAnimator.INFINITE);
        tAnimator.setRepeatMode(ValueAnimator.RESTART);
        tAnimator.setInterpolator(new LinearInterpolator());
        tAnimator.addUpdateListener(valueAnimator -> {
            Float val = (Float) valueAnimator.getAnimatedValue();
            groundOverlay.setTransparency(val);
        });

        animatorSet.setDuration(3000);
        animatorSet.playTogether(vAnimator, tAnimator);
        animatorSet.start();
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}
