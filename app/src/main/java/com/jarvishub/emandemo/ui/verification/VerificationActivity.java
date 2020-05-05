package com.jarvishub.emandemo.ui.verification;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.ui.login.LoginActivity;
import com.jarvishub.emandemo.utils.AppConstants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class VerificationActivity extends AppCompatActivity implements IVerificationView {

    private String TAG = "VerificationActivity";
    @BindView(R.id.tvVerificationMessage)
    TextView textViewVerificationMsg;
    @BindView(R.id.tvVerificationStatus)
    TextView textViewVerificationStatus;
    @BindView(R.id.etVerificationCode)
    EditText editTextVerificationCodeInput;
    @BindView(R.id.btnConfirmVerification)
    Button buttonConfirmVerification;

    @Inject
    IVerificationPresenter verificationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        ((EmanApp) getApplication()).getApplicationComponent().inject(this);
        verificationPresenter.setView(this);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String code = intent.getStringExtra("otp");

        if (code != null) {
            editTextVerificationCodeInput.setText(code);
            textViewVerificationStatus.setVisibility(View.VISIBLE);
            textViewVerificationStatus.setText("Account Verified!!");
        } else
            textViewVerificationStatus.setText("Enter A Valid Code!!");

    }

    @OnTextChanged(R.id.etVerificationCode)
    public void onVerificationCodeChanged() {
        if (editTextVerificationCodeInput.getText().toString().length() == AppConstants.NUM_OF_VERIFICATION_CODE_DIGIT) {
            buttonConfirmVerification.setClickable(true);
            buttonConfirmVerification.setBackgroundResource(R.drawable.shape_round);
        } else {
            buttonConfirmVerification.setClickable(false);
            buttonConfirmVerification.setBackgroundResource(R.drawable.shape_round_grey);
        }
        Log.d(TAG, "onVerificationCodeChanged: ................. " + editTextVerificationCodeInput.getText().toString());
    }

    @OnClick(R.id.btnConfirmVerification)
    public void onConfirmVerificationClicked() {
        verificationPresenter.authenticateVerificationCode(editTextVerificationCodeInput.getText().toString());
        Log.d(TAG, "onConfirmVerificationClicked: ............ ..... clicked ");
    }

    @OnClick(R.id.btnBckToLogin)
    public void backToLogin(View view) {
        Intent intent = new Intent(VerificationActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, AppConstants.SMS_PERMISSION_REQUESR_CODE);
            Toast.makeText(this, "Please give SMS permission", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void showSuccessfulDialogue() {
        Log.d(TAG, "showSuccessfulDialogue: ........................ ");
    }

    @Override
    public void showFailedDialogue() {
        Log.d(TAG, "showFailedDialogue: .............................. ");
    }
}
