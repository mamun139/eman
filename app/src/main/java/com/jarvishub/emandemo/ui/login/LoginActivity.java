package com.jarvishub.emandemo.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.User;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.ui.home.HomeActivity;
import com.jarvishub.emandemo.ui.home.LocationSettingsActivity;
import com.jarvishub.emandemo.ui.signUp.SignUpActivity;
import com.jarvishub.emandemo.utils.InternetChecker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.etLoginPassword)
    EditText etPassword;
    @BindView(R.id.etLoginMobile)
    EditText etPhone;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.llSignIn)
    LinearLayout linearLayoutSignIn;

    @Inject
    DataManager mDataManager;
    @Inject
    ILoginPresenter loginPresenter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((EmanApp) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        loginPresenter.setView(this);

        tvRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

    }

    @OnClick(R.id.btn_login)
    public void loginButtonClicked(View view) {

        String phone = etPhone.getText().toString();
        String password = etPassword.getText().toString();
        User user = new User(phone, password);

        etPhone.setError(null);
        etPassword.setError(null);
        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        }
        if (TextUtils.isEmpty(phone)) {
            etPhone.setError(getString(R.string.error_field_required));
            focusView = etPassword;
            cancel = true;
        } else if (!isPhoneValid(phone)) {
            etPhone.setError(getString(R.string.error_invalid_mobile));
            focusView = etPhone;
            cancel = true;
        }
        if (cancel) {

            focusView.requestFocus();
        } else {
             showLoading();
            if (InternetChecker.isInternetAvailable(this)) {
                loginPresenter.attemptLogin(user);
                mDataManager.setUserPassword(etPassword.getText().toString());
            } else {
                onInternetUnavailable();
            }

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private boolean isPhoneValid(String phone) {
        if (phone.length() == 11) {
            return true;
        } else
            return false;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 5;
    }


    @Override
    public void navigateToHomeActivity() {
        Button button = (Button) findViewById(R.id.btn_login);
        button.setVisibility(VISIBLE);
         progressDialog.dismiss();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        assert locationManager != null;
        /*if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(LoginActivity.this, LocationSettingsActivity.class);
            startActivity(intent);
        }*/
        Intent intent = new Intent(LoginActivity.this, LocationSettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFailed() {
        Button button = (Button) findViewById(R.id.btn_login);
        button.setVisibility(VISIBLE);
        progressDialog.dismiss();

        etPassword.setError(getString(R.string.error_login_failed));
        etPhone.setError(getString(R.string.error_login_failed));
        Toast.makeText(this, "Invalid Password or Phone Number", Toast.LENGTH_LONG).show();

    }

    @Override
    public void showLoading() {
        Button button = (Button) findViewById(R.id.btn_login);
        button.setVisibility(INVISIBLE);
        progressDialog = ProgressDialog.show(this, "Authenticating...", null);

    }

    @Override
    public void hideLoading() {

    }


    public void onInternetUnavailable() {
        Button button = (Button) findViewById(R.id.btn_login);
        button.setVisibility(VISIBLE);
        progressDialog.dismiss();

        Snackbar snackbar = Snackbar
                .make(linearLayoutSignIn, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
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
    }
}



