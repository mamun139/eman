package com.jarvishub.emandemo.ui.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.felipecsl.gifimageview.library.GifImageView;
import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.ui.DashBoard;
import com.jarvishub.emandemo.ui.Test;
import com.jarvishub.emandemo.ui.base.BaseActivity;
import com.jarvishub.emandemo.ui.home.HomeActivity;
import com.jarvishub.emandemo.ui.home.LocationSettingsActivity;
import com.jarvishub.emandemo.ui.login.LoginActivity;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity implements SplashView {
    private String TAG = "Splash Activity";

    @Inject
    ISplashPresenter splashPresenter;
    @BindView(R.id.gifImageView)
    GifImageView splash;
    @Inject
    DataManager mDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        ((EmanApp) getApplication()).getApplicationComponent().inject(this);
        splashPresenter.setView(this,getApplicationContext());
        startSplashScreen();
    }

    private void startSplashScreen() {
        try {
            InputStream inputStream = getAssets().open("eman_demo.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            splash.setBytes(bytes);
            splash.startAnimation();
        } catch (IOException ex) {
            Log.e(TAG, "startSplashScreen: ............... "+ex );
        }

        new Handler().postDelayed(() -> splashPresenter.doBackGroundTasks(), 2500);
    }

    @Override
    public void gotoLoginActivity() {
            SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            SplashScreenActivity.this.finish();
    }

    @Override
    public void discardSplash() {

        //Wait for 3 seconds and start Activity Main
        new Handler().postDelayed(() -> {
          LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            assert locationManager != null;
            if (mDataManager.isLoggedIn() && (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) && checkPermission()) {
                SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                SplashScreenActivity.this.finish();
            } else if (mDataManager.isLoggedIn() && (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))) {
                SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, LocationSettingsActivity.class));
                SplashScreenActivity.this.finish();
            } else {
                SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                SplashScreenActivity.this.finish();
            }
            splash.stopAnimation();
            SplashScreenActivity.this.finish();
        }, 2500);

    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, BaseActivity.LOCATION_PERMISSION_REQUEST_CODE);
            Toast.makeText(this, "Please give location permission", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void gotoHomeActivity() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        assert locationManager != null;
        if (mDataManager.isLoggedIn() && (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) && checkPermission()) {
            SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
            SplashScreenActivity.this.finish();
        } else if (mDataManager.isLoggedIn() && (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))) {
            SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, LocationSettingsActivity.class));
            SplashScreenActivity.this.finish();
        }
    }

    @Override
    public void startSyncService() {

    }

    @Override
    public void showAlert(String message) {
        int color;
        color = Color.RED;
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

}
