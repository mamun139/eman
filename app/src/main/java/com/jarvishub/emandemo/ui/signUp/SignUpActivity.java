package com.jarvishub.emandemo.ui.signUp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.User;
import com.jarvishub.emandemo.ui.login.LoginActivity;
import com.jarvishub.emandemo.ui.verification.VerificationActivity;
import com.jarvishub.emandemo.utils.InternetChecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SignUpActivity extends AppCompatActivity implements ISignUpView {

    private final String TAG = "SignUpActivity";

    ProgressDialog progressDialog;


    @Inject
    ISignUpPresenter signUpPresenter;

    @Inject
    DataManager dataManager;

    @BindView(R.id.etRegFirstName)
    EditText etFullName;
    @BindView(R.id.etRegSurName)
    EditText etSurName;
    @BindView(R.id.etRegEmail)
    EditText etEmail;
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;
    @BindView(R.id.etRegPassword)
    EditText etPassword;
    @BindView(R.id.etRegMobile)
    EditText etMobile;
    @BindView(R.id.tvDob)
    TextView tvDob;
    @BindView(R.id.tvBackToLoginMsg)
    TextView tvBackToLogin;
    @BindView(R.id.llSignUp)
    LinearLayout linearLayoutSignUp;

    @BindView(R.id.radioGroupGender)
    RadioGroup radioGender;
    @BindView(R.id.radioGroupUserType)
    RadioGroup radioUserType;

    //int genderId;


    ProgressBar progressBar;

    String gender = "none", userType = "none";
    //String dob = "none";
    String dob = "2000-12-12";// demo dob; dob is disabled right now; To enable: comment this line and make view visible in xml
    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userType = "Individual";

        ((EmanApp) getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        signUpPresenter.setView(this);

        tvBackToLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        tvDob.setOnClickListener(view -> getDate());

        radioGender.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioFemale:
                        gender = "Female";
                    break;
                case R.id.radioMale:
                        gender = "Male";
                    break;
                case R.id.radioOthers:
                        gender = "Others";
                    break;

            }
        });
        radioUserType.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioCorporate:
                        userType = "Corporate";
                    break;
                case R.id.radioIndividual:
                        userType = "Individual";
                    break;
            }
        });
    }


    public void getDate() {
        final Calendar c;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {

                    dob = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    tvDob.setText(dob);
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @OnClick(R.id.btnRegister)
    public void signUpButtonClicked(View view) {

        String firstName = etFullName.getText().toString();
        String surName = etSurName.getText().toString();
        String email = etEmail.getText().toString();
        String mobile = etMobile.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        etMobile.setError(null);
        etPassword.setError(null);
        etFullName.setError(null);
        etEmail.setError(null);
        etSurName.setError(null);
        etConfirmPassword.setError(null);

        boolean cancel = false;
        View focusView = null;

        if (!password.equals(confirmPassword)) {
            Log.d(TAG, "signUpButtonClicked: ............................."+password+"......."+confirmPassword);
            etConfirmPassword.setError(getString(R.string.error_confirm_password));
            focusView=etConfirmPassword;
            cancel=true;

        }


        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        }

        if (TextUtils.isEmpty(firstName)) {
            etFullName.setError(getString(R.string.error_field_required));
            focusView = etFullName;
            cancel = true;

        }
        else if (!isNameValid(firstName)){
            etFullName.setError(getString(R.string.error_invalid_name));
            focusView = etFullName;
            cancel = true;
        }
        else if (firstName.length()>60){
            etFullName.setError(getString(R.string.error_size_name));
            focusView = etFullName;
            cancel = true;
        }
        if (TextUtils.isEmpty(surName)) {
            etSurName.setError(getString(R.string.error_field_required));
            focusView = etSurName;
            cancel = true;

        }
        else if (!isNameValid(surName)){
            etSurName.setError(getString(R.string.error_invalid_name));
            focusView = etSurName;
            cancel = true;
        }
        else if (surName.length()>60){
            etSurName.setError(getString(R.string.error_size_name));
            focusView = etSurName;
            cancel = true;
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.error_invalid_email));
            focusView = etEmail;
            cancel = true;
        }


        if (TextUtils.isEmpty(mobile) || !isPhoneValid(mobile)) {
            etMobile.setError(getString(R.string.error_field_required));
            focusView = etMobile;
            cancel = true;
        }


        if (dob.equals("none")) {

            tvDob.setError(getString(R.string.error_field_required));
            focusView = tvDob;
            cancel = true;

        }
        if (gender.equals("none")) {
            Toast.makeText(this, "Please Provide Required Field", Toast.LENGTH_SHORT).show();
        }
        if (userType.equals("none")) {
            Toast.makeText(this, "Please Provide Required Field", Toast.LENGTH_SHORT).show();

        }
        // if (!gender.equals("none")&& !userType.equals("none")){


        if (cancel) {

            focusView.requestFocus();
        } else {

            User user = new User(firstName, surName, email, password, mobile, gender, dob, userType);

            if (InternetChecker.isInternetAvailable(this)) {
                showLoading();
                signUpPresenter.attemptSignUp(user);
            } else {
                onInternetUnavailable();
            }
        }

    }

    public void onInternetUnavailable() {
        Button button = (Button) findViewById(R.id.btn_login);
        button.setVisibility(VISIBLE);
        progressDialog.dismiss();

        Snackbar snackbar = Snackbar
                .make(linearLayoutSignUp, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
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

    private boolean isPhoneValid(String phone) {
        if (phone.length() == 11) {
            return true;
        } else
            return false;

    }

    private boolean isPasswordValid(String password) {
        if (password.length() > 5) {
            return true;
        } else {
            return false;
        }
    }
    private boolean isNameValid(String name){
        String expression = "^[a-zA-Z0-9]*$";
        CharSequence inputString = name;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.matches())
        {
            return true;
        }
        else{
            return false;
        }

    }


    @Override
    public void navigateToVerificationActivity() {
        progressDialog.dismiss();
        Intent intent = new Intent(SignUpActivity.this, VerificationActivity.class);
        startActivity(intent);
    }

    @Override
    public void signUpFailed() {
        Button button = (Button) findViewById(R.id.btnRegister);
        button.setVisibility(View.VISIBLE);
        progressDialog.dismiss();

    }

    @Override
    public void showLoading() {
        Button button = (Button) findViewById(R.id.btnRegister);
        button.setVisibility(INVISIBLE);
        progressDialog = ProgressDialog.show(this, "Authenticating...", null);

    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();

    }
}
