package com.jarvishub.emandemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import com.jarvishub.emandemo.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 11/14/2017.
 */

public class Test extends AppCompatActivity {
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etSurName)
    EditText etSurName;
    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.etEmailId)
    EditText etEmailId;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnProfileSave)
    Button btnProfileSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

    }

}
