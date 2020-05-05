package com.jarvishub.emandemo.ui.createOrder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.data.DataManager;
import com.jarvishub.emandemo.model.CreateOrderInfo;
import com.jarvishub.emandemo.network.APIService;
import com.jarvishub.emandemo.ui.home.HomeActivity;
import com.jarvishub.emandemo.ui.termsConditions.TermsConditions;
import com.jarvishub.emandemo.utils.InternetChecker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CreateOrderFragment extends Fragment implements ICreateOrderView {
   /* @BindView(R.id.tvPopUpTermsCondition)
    TextView tvTermsCondition;*/
    @Inject
    DataManager mDataManager;
    private APIService apiInterface;
    String TAG="TermsConditions";
    public static String termsCondition;

    @Inject
    ICreateOrderPresenter createOrderPresenter;
    @Inject
    DataManager dataManager;

    private IOnCreateOrderInteractionListener mListener;
    CreateOrderInfo createOrderInfo;
    String paymentMethodId = "0";
    String collectionInstruction;

    public CreateOrderFragment() {
    }

    @BindView(R.id.tvPriceOfPackage)
    TextView tvPriceOfPackage;
    @BindView(R.id.tvTimeOfPackage)
    TextView tvTimeOfPackage;
    @BindView(R.id.tvSizeOfPackage)
    TextView tvSizeOfPackage;
    @BindView(R.id.tvMaxWeightOfPackage)
    TextView tvMaxWeightOfPackage;
    @BindView(R.id.tvCollectionAddress)
    TextView tvCollectionAddress;
    @BindView(R.id.tvDeliveryAddress)
    TextView tvDeliveryAddress;
    @BindView(R.id.tvCollectionTime)
    TextView tvCollectionTime;
    @BindView(R.id.tvDeliveryTime)
    TextView tvDeliveryTime;
    @BindView(R.id.etSenderName)
    EditText etSenderName;
    @BindView(R.id.etSenderMobile)
    EditText etSenderMobile;
    @BindView(R.id.etReceiverName)
    EditText etReceiverName;
    @BindView(R.id.etReceiverMobile)
    EditText etReceiverMobile;
    @BindView(R.id.etDeliveryInstruction)
    EditText etDeliveryInstruction;
    @BindView(R.id.radioPaymentMethod)
    RadioGroup radioPaymentMethod;
    @BindView(R.id.radioCashDelivery)
    RadioButton radioCashOnDelivery;
    @BindView(R.id.radioCashReceive)
    RadioButton radioCashOnReceive;
    @BindView(R.id.radioInvoice)
    RadioButton radioInvoice;
    @BindView(R.id.llNotificationCheckBox)
    LinearLayout linearLayoutNotificationCheckbox;
    @BindView(R.id.llCreateOrder)
    LinearLayout linearLayoutCreateOrder;
    @BindView(R.id.radioGroupCollectionInstruction)
    RadioGroup radioGroupCollectionInstruction;
    @BindView(R.id.checkBoxCall)
    CheckBox checkCall;
    @BindView(R.id.checkboxEmail)
    CheckBox checkEmail;
    @BindView(R.id.checkboxSms)
    CheckBox checkSms;
    @BindView(R.id.checkboxApp)
    CheckBox checkApp;
    @BindView(R.id.cbTermsCondition)
    CheckBox cbTermsCondition;
    @BindView(R.id.tvTermsCondition)
    TextView tvTermsCondition;
    String stc="bla bla";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_create_order, container, false);
        ButterKnife.bind(this, rootView);
        ((EmanApp) getActivity().getApplication()).getApplicationComponent().inject(this);
        setShowableValues();

        tvTermsCondition.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity().getApplication(),TermsConditions.class);
            startActivity(intent);
        });

        radioPaymentMethod.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioCashDelivery: {
                    paymentMethodId = "Cash on delivery";
                    radioCashOnDelivery.setBackgroundResource(R.drawable.radio_button_checked);
                    radioCashOnReceive.setBackgroundResource(R.drawable.button_normal_bg);
                    radioInvoice.setBackgroundResource(R.drawable.button_normal_bg);
                    break;
                }
                case R.id.radioCashReceive: {
                    paymentMethodId = "Cash on collection";
                    radioCashOnReceive.setBackgroundResource(R.drawable.radio_button_checked);
                    radioCashOnDelivery.setBackgroundResource(R.drawable.button_normal_bg);
                    radioInvoice.setBackgroundResource(R.drawable.button_normal_bg);
                    break;
                }
                case R.id.radioInvoice: {
                    paymentMethodId = "Invoice";
                    radioInvoice.setBackgroundResource(R.drawable.radio_button_checked);
                    radioCashOnReceive.setBackgroundResource(R.drawable.button_normal_bg);
                    radioCashOnDelivery.setBackgroundResource(R.drawable.button_normal_bg);
                    break;
                }
            }
        });

        radioGroupCollectionInstruction.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioWantToNotify: {
                    linearLayoutNotificationCheckbox.setVisibility(View.VISIBLE);
                    collectionInstruction = " ";
                    break;
                }
                case R.id.radioDontNotify: {
                    linearLayoutNotificationCheckbox.setVisibility(View.GONE);
                    collectionInstruction = "Don't Notify";
                    break;
                }
            }
        });
        return rootView;
    }

    private void setShowableValues() {
        int time = createOrderInfo.getPackageTime();
        Log.d(TAG, "setShowableValues: .............. "+time);
        if (time != 0)
            time = time / 60;

        tvPriceOfPackage.setText(String.format("%s Taka", String.valueOf(createOrderInfo.getPackagePrice())));
        tvSizeOfPackage.setText(createOrderInfo.getPackageType());
        tvTimeOfPackage.setText(String.valueOf(time + " Hours"));
        tvMaxWeightOfPackage.setText(retrieveSelectedPackageWeight((createOrderInfo.getPackageWeight())));

        etSenderName.setText(dataManager.getUserName());
        etSenderMobile.setText(dataManager.getMobileNumber());
        tvCollectionTime.setText(createOrderInfo.getCollection_datetime());
        tvDeliveryTime.setText(createOrderInfo.getDelivery_datetime());
        tvCollectionAddress.setText(dataManager.getCollectionAddress());
        tvDeliveryAddress.setText(dataManager.getDeliveryAddress());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!= null) {
            stc=this.getArguments().getString(TermsConditions.TERMS);
            Log.d(TAG, "onCreateView: ............................"+stc);

        }

    }

    @OnClick(R.id.btnConfirmOrder)
    public void onConfirmOrderButtonClicked() {
        Log.d(TAG, "onConfirmOrderButtonClicked: ................. " + paymentMethodId);
        retrieveCollectionInstruction();
        if (collectionInstruction == null){
            collectionInstruction = "Don't Notify";
        }
        createOrderInfo.setPayment_method(paymentMethodId);
        createOrderInfo.setCollection_contact_name(etSenderName.getText().toString());
        createOrderInfo.setCollection_contact_number(etSenderMobile.getText().toString());
        createOrderInfo.setCollection_instructions(collectionInstruction);
        createOrderInfo.setDelivery_contact_name(etReceiverName.getText().toString());
        createOrderInfo.setDelivery_contact_number(etReceiverMobile.getText().toString());
        createOrderInfo.setDelivery_instructions(etDeliveryInstruction.getText().toString());
        createOrderInfo.setCollection_address(dataManager.getCollectionAddress());
        createOrderInfo.setDelivery_address(dataManager.getDeliveryAddress());
        //paymentMethodId.equals("0")

        if(etReceiverName.getText().toString().equals("")||etReceiverMobile.getText().toString().equals("")||!isNameValid(etReceiverName.getText().toString())||!cbTermsCondition.isChecked()||!isPhoneValid(etReceiverMobile.getText().toString())||paymentMethodId.equals("0")){
        if(etReceiverName.getText().toString().equals("")){
            showToast("No Receiver Name");

        }
        if(etReceiverMobile.getText().toString().equals("")){
            showToast("No Receiver Mobile");}

        if (paymentMethodId.equals("0")) {
            showToast("Select a payment method");
            Log.d(TAG, "onButtonPackageSelected: ........................ No payment policy selected");
        }
        if(!cbTermsCondition.isChecked()){
            showToast("Please Read Terms & Condition CareFully.");
        }
        if (!isNameValid(etReceiverName.getText().toString())){
            showToast(getString(R.string.error_invalid_name));
        }
        if (!isPhoneValid(etReceiverMobile.getText().toString())){
            showToast(getString(R.string.error_invalid_mobile));
        }
        }
        else  {

            if (InternetChecker.isInternetAvailable(getActivity())) {
                createOrderPresenter.sendOrderDataToServer(createOrderInfo);
            } else {
                onInternetUnavailable();
            }
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
    private boolean isPhoneValid(String phone) {
        if (phone.length() == 11) {
            return true;
        } else
            return false;

    }
    public void retrieveCollectionInstruction(){
        if (checkApp.isChecked())
            collectionInstruction = collectionInstruction + "App,";
        if (checkCall.isChecked())
            collectionInstruction = collectionInstruction + "Call,";
        if (checkSms.isChecked())
            collectionInstruction = collectionInstruction + "SMS,";
        if (checkEmail.isChecked())
            collectionInstruction = collectionInstruction + "Email,";
    }
    public void onInternetUnavailable() {

        Snackbar snackbar = Snackbar
                .make(linearLayoutCreateOrder, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
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

    public void orderConfirmedDialog() {
        AlertDialog.Builder alertDialogue = new AlertDialog.Builder(getContext());
        alertDialogue.setTitle("Confirmation").setMessage("Your order submitted successfully.")
                .setCancelable(false)
                .setIcon(R.drawable.confirmation)
                .setPositiveButton("OK", (dialogInterface, i) -> startActivity(new Intent(getContext(), HomeActivity.class)));
        AlertDialog alertDialog = alertDialogue.create();
        alertDialog.show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnCreateOrderInteractionListener) {
            mListener = (IOnCreateOrderInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void getCreateOrderDraft(CreateOrderInfo createOrderInfo) {
        this.createOrderInfo = createOrderInfo;
    }

    @Override
    public void getCreateOrderStatus(String createOrderStatus) {
        Log.d(TAG, "onConfirmOrderButtonClicked: ......... create order res " + createOrderStatus);
        if (createOrderStatus.equals("success")) {
            orderConfirmedDialog();
        } else {
            showToast("Failed To Create Order!! Try Again");
        }
    }

    private String retrieveSelectedPackageWeight(int packageWeightId){
        if (packageWeightId == 0){
            return "500 gm";
        }else if (packageWeightId == 1){
            return "1 kg";
        }else if (packageWeightId == 2){
            return "2 kg";
        }else if (packageWeightId == 3){
            return "5 kg";
        }
        return "500 gm";
    }

    private void showToast(String message){
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            getView().setFocusableInTouchMode(true);
            getView().requestFocus();
            getView().setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().onBackPressed();
                    return true;
                }
                return false;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
