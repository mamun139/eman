package com.jarvishub.emandemo.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.data.prefs.PreferencesHelper;
import com.jarvishub.emandemo.model.CreateOrderInfo;
import com.jarvishub.emandemo.model.eman_list.EmansLocationData;
import com.jarvishub.emandemo.ui.activePromotion.ActivePromotion;
import com.jarvishub.emandemo.ui.base.BaseActivity;
import com.jarvishub.emandemo.ui.createOrder.CreateOrderFragment;
import com.jarvishub.emandemo.ui.createOrder.ICreateOrderPresenter;
import com.jarvishub.emandemo.ui.createOrder.ICreateOrderView;
import com.jarvishub.emandemo.ui.createOrder.IOnCreateOrderInteractionListener;
import com.jarvishub.emandemo.ui.login.LoginActivity;
import com.jarvishub.emandemo.ui.orderHistory.OrderHistory;
import com.jarvishub.emandemo.ui.servicePackages.IOnPackagesInteractionListener;
import com.jarvishub.emandemo.ui.servicePackages.IServicePackagesPresenter;
import com.jarvishub.emandemo.ui.servicePackages.IServicePackagesView;
import com.jarvishub.emandemo.ui.servicePackages.PackagesFragment;
import com.jarvishub.emandemo.ui.track.OrderTrack;
import com.jarvishub.emandemo.utils.InternetChecker;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        IMapView, IOnCreateOrderInteractionListener, IOnPackagesInteractionListener {
    String TAG = "HOME ACTIVITY";
    public static final String DELIVERY_LATITUDE = "delivery_latitude";
    public static final String DELIVERY_LONGITUDE = "delivery_longitude";
    public static final String DELIVERY_ADDRESS = "delivery_address";
    public static final String PICKUP_LATITUDE = "pickup_latitude";
    public static final String PICKUP_LONGITUDE = "delivery_longitude";
    public static final String PICKUP_ADDRESS = "pickup_address";

    GoogleMap mMap;

    LocationRequest mLocationRequest;
    Location userLocation;
    FragmentManager fragmentManager;
    boolean doubleBackToExitPressedOnce = false;

    @Inject
    PreferencesHelper mPreferencesHelper;


    private HashMap<String, Double> coOrdinates;

    @Inject
    IMapPresenter mapPresenter;
    @Inject
    IServicePackagesPresenter servicePackagesPresenter;
    @Inject
    ICreateOrderPresenter createOrderPresenter;
    ICreateOrderView createOrderView;
    IServicePackagesView servicePackagesView;

    @Inject
    DataManager dataManager;

    @BindView(R.id.etToAddress)
    EditText editTextToAddress;
    @BindView(R.id.cardViewToAddress)
    CardView cardViewToAddress;
    @BindView(R.id.etFromAddress)
    EditText editTextFromAddress;
    @BindView(R.id.cardViewFromAddress)
    CardView cardViewFromAddress;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.frameLayoutServicePackages)
    FrameLayout frameServices;
    @BindView(R.id.frameLayoutCreateOrder)
    FrameLayout frameCreateOrder;
    @BindView(R.id.frameLayoutMap)
    FrameLayout frameMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ((EmanApp) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();

        coOrdinates = new HashMap<>();
        mapPresenter.setView(this);
        servicePackagesPresenter.setView(this);
        createOrderPresenter.setView(this);
        super.setView(this);

        // createOrderPresenter.setView(this);
        editTextToAddress.requestFocus();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d(TAG, "onCreate: ...................................... GPS enabled ");
        } else {
            showGPSDisabledAlertToUser();
        }
        if (googleServicesAvailable()) {
            initMap();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Snackbar snackbar = Snackbar
                .make(drawerLayout, getResources().getText(R.string.alert_drag_map), Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(Color.BLUE)
                .setDuration(2000);

        snackbar.show();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
        TextView navEmail = hView.findViewById(R.id.tvUserMobile);
        navEmail.setText(mPreferencesHelper.getMobileNumber());

        TextView navName = hView.findViewById(R.id.tvUserName);
        navName.setText(mPreferencesHelper.getUserName());
        navigationView.setNavigationItemSelectedListener(this);
    }

    @OnClick(R.id.btnSubmitAddress)
    public void submitAddressButtonClicked(View view) {
        if (InternetChecker.isInternetAvailable(this)) {
            if (coOrdinates.get(DELIVERY_LATITUDE) != null && coOrdinates.get(DELIVERY_LONGITUDE) != null) {
                dataManager.saveCollectionAddress(editTextFromAddress.getText().toString());
                dataManager.saveDeliveryAddress(editTextToAddress.getText().toString());
                mapPresenter.sendLocation(coOrdinates);
            } else {
                Toast.makeText(getApplicationContext(), getResources().getText(R.string.alert_enter_right_loc), Toast.LENGTH_LONG).show();
            }
        } else {
            onInternetUnavailable();
        }

    }

    //gps enable check
    private void showGPSDisabledAlertToUser() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getResources().getText(R.string.alert_gps_disabled_wanna_enable))
                .setCancelable(false)
                .setPositiveButton(getResources().getText(R.string.alert_enable_gps),
                        (dialog, id) -> {
                            Intent callGPSSettingIntent = new Intent(
                                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(callGPSSettingIntent);
                        });
        alertDialogBuilder.setNegativeButton(getResources().getText(R.string.label_cancel),
                (dialog, id) -> dialog.cancel());
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

/*    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, getResources().getText(R.string.alert_install_play_services), Toast.LENGTH_LONG).show();
        }
        return false;
    }


    private void initMap() {
        Log.e(TAG, "initMap: 190.................................................. home act");
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
        //demoMapLocation();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int count = fragmentManager.getBackStackEntryCount();
        //TODO: Test Needed
        Log.d(TAG, "onBackPressed: ................................... " + count);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (count == 0) {
            setActionBarTitle(getResources().getString(R.string.title_activity_home));
            if (doubleBackToExitPressedOnce) {
                finishAffinity();
                System.exit(0);
            }
            doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getResources().getString(R.string.alert_press_again_exit), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        } else if (count == 1) {
            setActionBarTitle(getResources().getString(R.string.title_activity_home));
            makeMapFragVisible();
            fragmentManager.popBackStackImmediate();
        } else if (count == 2) {
            setActionBarTitle(getResources().getString(R.string.title_fragment_packages));
            makePackagesFragVisible();
            fragmentManager.popBackStackImmediate();
        } else if (count == 3) {
            setActionBarTitle(getResources().getString(R.string.title_fragment_packages));
            makePackagesFragVisible();
            fragmentManager.popBackStackImmediate();
        } else {
            fragmentManager.popBackStackImmediate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
       if (id == R.id.track) {
            HomeActivity.this.startActivity(new Intent(HomeActivity.this, OrderTrack.class));

        } else if (id == R.id.orderhistory) {
            HomeActivity.this.startActivity(new Intent(HomeActivity.this, OrderHistory.class));

        } else if (id == R.id.discount) {
            HomeActivity.this.startActivity(new Intent(HomeActivity.this, ActivePromotion.class));
        }
        else if (id == R.id.logout) {
            mapPresenter.doLogOut();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //map
    @OnClick(R.id.etToAddress)
    void openPlacesView() {
        editTextToAddress.requestFocus();
        if (!editTextToAddress.isSelected()) {
            editTextToAddress.selectAll();
            openPlaceAutoCompleteView();
            Log.d(TAG, "onClick: .....................  editTextToAddress focused");
        }
        //openPlaceAutoCompleteView();
    }

    @OnClick(R.id.etFromAddress)
    public void onFromEditClicked() {
        Log.d(TAG, "onFromEditClicked: ........................................ bleh");
        editTextFromAddress.requestFocus();
        if (!editTextFromAddress.isSelected()) {
            editTextFromAddress.selectAll();
            openPlaceAutoCompleteView();
            Log.d(TAG, "onClick: .....................  etFromAddress focused");
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.e(TAG, "onMapReady: 248 ....................................................... home act");
        super.onMapReady(googleMap);
        mMap = googleMap;

    }

    /**
     * this method will generate address from lat long and set in fromTextView
     *
     * @param lat
     * @param lng
     */

    public void getAddressFromCoOrdinate(Double lat, Double lng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> list = geocoder.getFromLocation(lat, lng, 1);
            Address address = list.get(0);

            String result = address.getAddressLine(0);
            coOrdinates.put(PICKUP_LATITUDE, lat);
            coOrdinates.put(PICKUP_LONGITUDE, lng);

            Log.d(TAG, "getAddressFromCoOrdinate: ........................... .................... .......");
            //get all the nearby emans and send to presenter
            mapPresenter.getActiveEmanList(lat, lng);
            //set current loc to view
            editTextFromAddress.setText(result);
            //editTextToAddress.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void removeMarkers() {


    }

    @Override
    public void showMarkerAt(List<EmansLocationData> emansLocationList) {
        setEmanMarker(emansLocationList);
        Log.d(TAG, "showMarkerAt: ....................... " + emansLocationList.size() + " " + emansLocationList.get(0).getLatitute());
    }

    private void makeMapFragVisible() {
        frameMap.setVisibility(View.VISIBLE);
    }

    private void makePackagesFragVisible() {
        frameCreateOrder.setVisibility(View.GONE);
        frameServices.setVisibility(View.VISIBLE);
    }

    @Override
    public void gotoServicePackagesFrag() {
        frameMap.setVisibility(View.GONE);
        frameServices.setVisibility(View.VISIBLE);
        frameCreateOrder.setVisibility(View.GONE);
        PackagesFragment frag1 = new PackagesFragment();
        servicePackagesView = frag1;
        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frameLayoutServicePackages, frag1);
        fragmentTransaction.commit();

        //change action bar title
        setActionBarTitle(getResources().getString(R.string.title_fragment_packages));
    }

    @Override
    public void gotoCreateOrderFrag(CreateOrderInfo orderInfo) {
        Log.d(TAG, "gotoCreateOrderFrag: ...................... ");
        frameMap.setVisibility(View.GONE);
        frameServices.setVisibility(View.GONE);
        frameCreateOrder.setVisibility(View.VISIBLE);
        CreateOrderFragment frag1 = new CreateOrderFragment();
        // FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        createOrderView = frag1;
        orderInfo.setCollection_latitude(String.valueOf(coOrdinates.get(PICKUP_LATITUDE)));
        orderInfo.setCollection_longitude(String.valueOf(coOrdinates.get(PICKUP_LONGITUDE)));
        orderInfo.setDelivery_latitude(String.valueOf(coOrdinates.get(DELIVERY_LATITUDE)));
        orderInfo.setDelivery_longitude(String.valueOf(coOrdinates.get(DELIVERY_LONGITUDE)));
        createOrderView.getCreateOrderDraft(orderInfo);
        fragmentTransaction.replace(R.id.frameLayoutCreateOrder, frag1);
        fragmentTransaction.commit();

        //change action bar title
        setActionBarTitle(getResources().getString(R.string.title_fragment_create_order));
    }

    //todo add collection marker
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void autocompleteSuggestionAddress(Double suggestionAddressLat, Double suggestionAddressLong, String suggestionAddress) {
        Log.d(TAG, "autocompleteSuggestionAddress: .......................  451 no line");
        /*editTextToAddress.setText(deliveryAddress);
        coOrdinates.put(DELIVERY_LATITUDE, deliveryLat);
        coOrdinates.put(DELIVERY_LONGITUDE, deliveryLong);
        LatLng deliveryCoOrdinates = new LatLng(deliveryLat, deliveryLong);
        addMarker(deliveryCoOrdinates);*/

        if (editTextFromAddress.isFocused()) {
            Log.d(TAG, "onDragLocChange: ............. from focused");
            editTextFromAddress.setText(suggestionAddress);
            coOrdinates.put(PICKUP_LATITUDE, suggestionAddressLat);
            coOrdinates.put(PICKUP_LONGITUDE, suggestionAddressLong);
            LatLng collectionCoOrdinates = new LatLng(suggestionAddressLat, suggestionAddressLong);
            //addCollectionPointMarker(collectionCoOrdinates);
            addMarker(collectionCoOrdinates);
        } else if (editTextToAddress.isFocused()) {
            Log.d(TAG, "onDragLocChange: .............. to focused");
            editTextToAddress.setText(suggestionAddress);
            coOrdinates.put(DELIVERY_LATITUDE, suggestionAddressLat);
            coOrdinates.put(DELIVERY_LONGITUDE, suggestionAddressLong);
            LatLng deliveryCoOrdinates = new LatLng(suggestionAddressLat, suggestionAddressLong);
            addMarker(deliveryCoOrdinates);
        }

    }

    @Override
    public void PickUpAddress(Double pickupLat, Double pickupLong, String pickupAddress) {
        coOrdinates.put(PICKUP_LATITUDE, pickupLat);
        coOrdinates.put(PICKUP_LONGITUDE, pickupLong);
        getAddressFromCoOrdinate(pickupLat, pickupLong);
        Log.d(TAG, pickupAddress + " PickUpAddress: ............................... latitude longitude " + pickupLat + pickupLong);
    }

    public void setActionBarTitle(String title) {
        try {
            getSupportActionBar().setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDragLocChange(Double latitude, Double longitude, String address) {
        Log.d(TAG, "onDragLocChange: ............ " + address);
        if (editTextFromAddress.isFocused()) {
            Log.d(TAG, "onDragLocChange: ............. from focused");
            editTextFromAddress.setText(address);
            coOrdinates.put(PICKUP_LATITUDE, latitude);
            coOrdinates.put(PICKUP_LONGITUDE, longitude);
        } else if (editTextToAddress.isFocused()) {
            Log.d(TAG, "onDragLocChange: .............. to focused");
            editTextToAddress.setText(address);
            coOrdinates.put(DELIVERY_LATITUDE, latitude);
            coOrdinates.put(DELIVERY_LONGITUDE, longitude);
        }
    }

    public void onInternetUnavailable() {

        Snackbar snackbar = Snackbar
                .make(drawerLayout, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
                .setDuration(5000)
                .setAction("OPEN SETTINGS", view -> {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                });
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();

        Log.e("LoginActivity", "onInternetUnavailable: ..........................  No Internet Connection!!");
    }

    @Override
    public void createOrderStatus(String createOrderFlag) {
        createOrderView.getCreateOrderStatus(createOrderFlag);
    }

    @Override
    public void onPromoSuccess() {
        Log.d(TAG, "onPromoSuccess: ......................................................");
        servicePackagesView.onPromoSuccess();
    }

    @Override
    public void onPromoFailed() {
        Log.d(TAG, "onPromoFailed: .........................................................");
        servicePackagesView.onPromoFail();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPermissionsGranted(int i, List<String> list) {
        Log.d(TAG, "onPermissionsGranted: ................. ........................ ....... " + list.toString());
    }

    @Override
    public void onPermissionsDenied(int i, List<String> list) {

    }

    @Override
    public void onPackageSelected() {

    }


    @Override
    public void afterLogOut() {
        HomeActivity.this.startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finishAffinity();
        System.exit(0);

    }
}