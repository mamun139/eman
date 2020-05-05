package com.jarvishub.emandemo.ui.servicePackages;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.jarvishub.emandemo.EmanApp;
import com.jarvishub.emandemo.R;
import com.jarvishub.emandemo.model.CreateOrderInfo;
import com.jarvishub.emandemo.model.service_packages.GenericCategory;
import com.jarvishub.emandemo.model.service_packages.Packages;
import com.jarvishub.emandemo.model.service_packages.ServicePackageData;
import com.jarvishub.emandemo.model.service_packages.ServicesData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PackagesFragment extends Fragment implements IServicePackagesView{

    private String TAG = "PackagesFragment";

    @Inject
    IServicePackagesPresenter servicePackagesPresenter;
    IOnPackagesInteractionListener mIOnPackagesClickListener;
    ServicePackageData servicePackageData;
    CreateOrderInfo createOrderInfo;
    GenericCategory selectedCategory;
    List<Packages> selectedPackagesWeightList;
    Packages selectedPackage;

    SimpleDateFormat simpleDateFormat;
    SimpleDateFormat simpleTimeFormat;
    SimpleDateFormat simpleDateOnlyFormat;
    SimpleDateFormat customDateTimeFormat;
    String collectionTime;
    String deliveryTime;
    String customCollectionTime = "0";
    String customDeliveryTime = "0";
    String productDescription = "none";
    int collectionTimeFlag = 2; //time after collection will happen
    int deliveryTimeFlag = 6;   //time after delivery will happen
    int packageWeightId = 0;
    int parcelPackageId = 1;
    int envelopePackageId = 1;

    int collectionMinTime = 1;
    int collectionMaxTime = 2;
    int deliveryMinTime = 5;
    int deliveryMaxTime = 6;


    @BindView(R.id.btnServiceCategory1)
    Button buttonServiceCategory1;
    @BindView(R.id.btnServiceCategory2)
    Button buttonServiceCategory2;
    @BindView(R.id.btnServiceCategory3)
    Button buttonServiceCategory3;
    @BindView(R.id.btnServiceCategory4)
    Button buttonServiceCategory4;
    @BindView(R.id.btnServiceCategory5)
    Button buttonServiceCategory5;


    @BindView(R.id.tvServiceCategory1)
    TextView textViewServiceCategory1;
    @BindView(R.id.tvServiceCategory2)
    TextView textViewServiceCategory2;
    @BindView(R.id.tvServiceCategory3)
    TextView textViewServiceCategory3;
    @BindView(R.id.tvServiceCategory4)
    TextView textViewServiceCategory4;
    @BindView(R.id.tvServiceCategory5)
    TextView textViewServiceCategory5;

    @BindView(R.id.viewServiceCategory1)
    View viewServiceCategory1;
    @BindView(R.id.viewServiceCategory2)
    View viewServiceCategory2;
    @BindView(R.id.viewServiceCategory3)
    View viewServiceCategory3;
    @BindView(R.id.viewServiceCategory4)
    View viewServiceCategory4;



    @BindView(R.id.btnServiceWeight1)
    Button buttonServiceWeight1;
    @BindView(R.id.btnServiceWeight2)
    Button buttonServiceWeight2;
    @BindView(R.id.btnServiceWeight3)
    Button buttonServiceWeight3;
    @BindView(R.id.btnServiceWeight4)
    Button buttonServiceWeight4;
    @BindView(R.id.btnServiceWeight5)
    Button buttonServiceWeight5;


    @BindView(R.id.tvServiceWeight1)
    TextView textViewServiceWeight1;
    @BindView(R.id.tvServiceWeight2)
    TextView textViewServiceWeight2;
    @BindView(R.id.tvServiceWeight3)
    TextView textViewServiceWeight3;
    @BindView(R.id.tvServiceWeight4)
    TextView textViewServiceWeight4;
    @BindView(R.id.tvServiceWeight5)
    TextView textViewServiceWeight5;

    @BindView(R.id.viewServiceWeight1)
    View viewServiceWeight1;
    @BindView(R.id.viewServiceWeight2)
    View viewServiceWeight2;
    @BindView(R.id.viewServiceWeight3)
    View viewServiceWeight3;
    @BindView(R.id.viewServiceWeight4)
    View viewServiceWeight4;





    @BindView(R.id.btnPackage1)
    Button buttonPackage1;
    @BindView(R.id.btnPackage2)
    Button buttonPackage2;
    @BindView(R.id.btnPackage3)
    Button buttonPackage3;
    @BindView(R.id.btnPackage4)
    Button buttonPackage4;
    @BindView(R.id.btnPackage5)
    Button buttonPackage5;

    @BindView(R.id.tvPackage1)
    TextView textViewPackage1;
    @BindView(R.id.tvPackage2)
    TextView textViewPackage2;
    @BindView(R.id.tvPackage3)
    TextView textViewPackage3;
    @BindView(R.id.tvPackage4)
    TextView textViewPackage4;
    @BindView(R.id.tvPackage5)
    TextView textViewPackage5;

    @BindView(R.id.viewPackage1)
    View viewPackage1;
    @BindView(R.id.viewPackage2)
    View viewPackage2;
    @BindView(R.id.viewPackage3)
    View viewPackage3;
    @BindView(R.id.viewPackage4)
    View viewPackage4;


    @BindView(R.id.tvServicePackagePrice)
    TextView textViewServicePackagePrice;
    @BindView(R.id.tvInputCollectionTime)
    TextView textViewCollectionTime;
    @BindView(R.id.tvInputDeliveryTime)
    TextView textViewDeliveryTime;


    @BindView(R.id.btnPackageConfirm)
    Button buttonPackageSelected;
    @BindView(R.id.etPromoCode)
    EditText etPromoCode;
    @BindView(R.id.etProductDescription)
    EditText etProductDescription;
    @BindView(R.id.btnApplyPromo)
    Button buttonApplyPromo;
    @BindView(R.id.radioGroupProductDescription)
    RadioGroup radioGroupProductDescription;
    @BindView(R.id.tvProductDescription)
    TextView tvProductDescription;


    public PackagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_packages, container, false);
        ButterKnife.bind(this, rootView);
        ((EmanApp) getActivity().getApplication()).getApplicationComponent().inject(this);
        this.simpleDateFormat = new SimpleDateFormat("EEE d MMM HH:mm", Locale.getDefault());
        this.simpleTimeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());
        this.simpleDateOnlyFormat = new SimpleDateFormat("EEE d MMM", Locale.getDefault());
        this.customDateTimeFormat = new SimpleDateFormat("y-M-d hh:mm:ss", Locale.getDefault());


        createOrderInfo = new CreateOrderInfo();
        servicePackageData = servicePackagesPresenter.getServicePackages();

        setUpCategoryUi();
        Log.d(TAG, "onCreateView: ........................................................... "+servicePackageData.getValue().getCategoryName());

        radioGroupProductDescription.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.radioDocument:
                    etProductDescription.setVisibility(View.GONE);
                    productDescription = "Document";
                    break;
                case R.id.radioGoods:
                    etProductDescription.setVisibility(View.GONE);
                    productDescription = "Goods";
                    break;
                case R.id.radioElectronics:
                    etProductDescription.setVisibility(View.GONE);
                    productDescription = "Electronics";
                    break;
                case R.id.radioValuable:
                    etProductDescription.setVisibility(View.GONE);
                    productDescription = "Valuable";
                    break;
                case R.id.radioOtherProducts:
                    productDescription = "others";
                    etProductDescription.setVisibility(View.VISIBLE);
                    break;
            }
        });
        return rootView;

    }

    private void setUpCategoryUi() {
        //setting package names
        try {
            textViewServiceCategory1.setText(servicePackageData.getStandard().getCategoryName());
            textViewServiceCategory2.setText(servicePackageData.getEmergency().getCategoryName());
            textViewServiceCategory3.setText(servicePackageData.getPrime().getCategoryName());
            textViewServiceCategory4.setText(servicePackageData.getOffDay().getCategoryName());
            textViewServiceCategory5.setText(servicePackageData.getValue().getCategoryName());
        } catch (Exception e) {
            Log.e(TAG, "setUpCategoryUi: ..........................  package missing or problem in retrieving data ");
            e.printStackTrace();
        }

        try {
            unCheckAllTheServiceCategoryButtons();
            buttonServiceCategory1.setBackgroundResource(R.drawable.checked);

            selectedCategory = servicePackageData.getStandard();
            generateWeightView(selectedCategory.getData());
            collapseDependentViews();

            selectDefaultWeight();
        } catch (Exception e) {
            Log.e(TAG, "setUpCategoryUi: ........................... error while selecting default category" );
            e.printStackTrace();
        }


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }



    @OnClick(R.id.btnPackageConfirm)
    public void onButtonPackageSelected() {
       createOrderInfo.setService_id(selectedPackage.getId());
       createOrderInfo.setWeight_id(packageWeightId + 1);
       createOrderInfo.setCategory_id(selectedCategory.getCategoryId());

       createOrderInfo.setPackagePrice(selectedPackage.getPrice().toString());
       createOrderInfo.setPackageTime(selectedPackage.getLeadTime());
       createOrderInfo.setPackageType(selectedPackage.getName());
       createOrderInfo.setPackageWeight(packageWeightId);

       if (productDescription.equals("others")) {
            productDescription = etProductDescription.getText().toString();
        }
        createOrderInfo.setPromo_code(etPromoCode.getText().toString());
        createOrderInfo.setItem_description(productDescription);
        if (customCollectionTime.equals("0") || customDeliveryTime.equals("0")) {
            Toast.makeText(getContext(), "Please enter collection,delivery time", Toast.LENGTH_SHORT).show();
        } else {

            createOrderInfo.setCollection_datetime(customCollectionTime);
            createOrderInfo.setDelivery_datetime(customDeliveryTime);
            servicePackagesPresenter.createDraftOrder(createOrderInfo);
        }
    }


    @OnClick(R.id.btnServiceCategory1)
    public void onFirstCategorySelected() {
        unCheckAllTheServiceCategoryButtons();
        buttonServiceCategory1.setBackgroundResource(R.drawable.checked);

        selectedCategory = servicePackageData.getStandard();
        generateWeightView(selectedCategory.getData());
        collapseDependentViews();
        selectDefaultWeight();

        Log.d(TAG, "onFirstCategorySelected: ............................... " + selectedCategory.getCategoryName());

    }


    @OnClick(R.id.btnServiceCategory2)
    public void onSecondCategorySelected() {
        unCheckAllTheServiceCategoryButtons();
        buttonServiceCategory2.setBackgroundResource(R.drawable.checked);

        selectedCategory = servicePackageData.getEmergency();
        generateWeightView(selectedCategory.getData());
        collapseDependentViews();
        selectDefaultWeight();
    }

    @OnClick(R.id.btnServiceCategory3)
    public void onThirdCategorySelected() {
        unCheckAllTheServiceCategoryButtons();
        buttonServiceCategory3.setBackgroundResource(R.drawable.checked);

        selectedCategory = servicePackageData.getPrime();
        generateWeightView(selectedCategory.getData());
        collapseDependentViews();
        selectDefaultWeight();
    }

    @OnClick(R.id.btnServiceCategory4)
    public void onFourthCategorySelected() {
        unCheckAllTheServiceCategoryButtons();
        buttonServiceCategory4.setBackgroundResource(R.drawable.checked);

        selectedCategory = servicePackageData.getOffDay();
        generateWeightView(selectedCategory.getData());
        collapseDependentViews();
        selectDefaultWeight();
    }

    @OnClick(R.id.btnServiceCategory5)
    public void onFifthCategorySelected() {
        unCheckAllTheServiceCategoryButtons();
        buttonServiceCategory5.setBackgroundResource(R.drawable.checked);

        selectedCategory = servicePackageData.getValue();
        generateWeightView(selectedCategory.getData());
        collapseDependentViews();
        selectDefaultWeight();
    }

    private void collapseDependentViews() {
        unCheckAllPackagesButtons();
        textViewServicePackagePrice.setText("");
        tvProductDescription.setText("");

    }

    public void unCheckAllTheServiceCategoryButtons() {
        buttonServiceCategory1.setBackgroundResource(R.drawable.unchecked);
        buttonServiceCategory2.setBackgroundResource(R.drawable.unchecked);
        buttonServiceCategory3.setBackgroundResource(R.drawable.unchecked);
        buttonServiceCategory4.setBackgroundResource(R.drawable.unchecked);
        buttonServiceCategory5.setBackgroundResource(R.drawable.unchecked);
    }
    public void unCheckAllPackagesButtons() {
        buttonPackage1.setBackgroundResource(R.drawable.unchecked);
        buttonPackage2.setBackgroundResource(R.drawable.unchecked);
        buttonPackage3.setBackgroundResource(R.drawable.unchecked);
        buttonPackage4.setBackgroundResource(R.drawable.unchecked);
        buttonPackage5.setBackgroundResource(R.drawable.unchecked);
    }
    public void unCheckAllServiceWeightButtons() {
        buttonServiceWeight1.setBackgroundResource(R.drawable.unchecked);
        buttonServiceWeight2.setBackgroundResource(R.drawable.unchecked);
        buttonServiceWeight3.setBackgroundResource(R.drawable.unchecked);
        buttonServiceWeight4.setBackgroundResource(R.drawable.unchecked);
        buttonServiceWeight5.setBackgroundResource(R.drawable.unchecked);
    }



    @OnClick(R.id.llCollectionTime)
    public void collectionLayoutClicked() {

        final Date now = new Date();
        final Calendar calendarMin = Calendar.getInstance();
        final Calendar calendarMax = Calendar.getInstance();

        calendarMin.setTime(new Date(now.getTime() + TimeUnit.HOURS.toMillis(collectionMinTime))); // Set min now
        calendarMax.setTime(new Date(now.getTime() + TimeUnit.HOURS.toMillis(collectionMaxTime))); // Set max now + 150 days

        final Date minDate = calendarMin.getTime();
        final Date maxDate = calendarMax.getTime();


        new SingleDateAndTimePickerDialog.Builder(getContext())
                .bottomSheet()
                .curved()
                .minutesStep(5)
                .mainColor(Color.rgb(0, 230, 118))
                .mustBeOnFuture()
                .minDateRange(minDate)
                .maxDateRange(maxDate)

                .title(" Collection Time")
                .listener(date -> {
                    collectionTime = String.valueOf(simpleDateFormat.format(date));
                    customCollectionTime = String.valueOf(customDateTimeFormat.format(date));
                    textViewCollectionTime.setText(collectionTime);
                    Log.d(TAG, "onDateSelected: ........................ " + date);
                }).display();
    }

    @OnClick(R.id.llDeliveryTime)
    public void deliveryLayoutClicked() {

        Date now = new Date();
        Calendar calendarMin = Calendar.getInstance();
        Calendar calendarMax = Calendar.getInstance();


        calendarMin.setTime(new Date(now.getTime() + TimeUnit.HOURS.toMillis(deliveryMinTime))); // Set min now
        calendarMax.setTime(new Date(now.getTime() + TimeUnit.HOURS.toMillis(deliveryMaxTime))); // Set max now + given time
        Log.e(TAG, "deliveryLayoutClicked: ........................... min time "+deliveryMinTime+" max time "+deliveryMaxTime);

        final Date minDate = calendarMin.getTime();
        final Date maxDate = calendarMax.getTime();


        new SingleDateAndTimePickerDialog.Builder(getContext())
                .bottomSheet()
                .curved()
                .minutesStep(5)
                .mainColor(Color.rgb(0, 230, 118))
                .mustBeOnFuture()
                .minDateRange(minDate)
                .maxDateRange(maxDate)

                .title("  Delivery Time")
                .listener(date -> {
                    deliveryTime = String.valueOf(simpleDateFormat.format(date));
                    customDeliveryTime = String.valueOf(customDateTimeFormat.format(date));
                    textViewDeliveryTime.setText(deliveryTime);
                    Log.d(TAG, "onDateSelected: ........................ " + date);
                }).display();
    }


    @OnClick(R.id.btnApplyPromo)
    public void onPromoApplyClicked() {
        servicePackagesPresenter.applyPromo(etPromoCode.getText().toString());
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


    private void setUserOrderSelection(String price, int time, String packageType) {
        createOrderInfo.setPackagePrice(price);
        createOrderInfo.setPackageTime(time);
        createOrderInfo.setPackageType(packageType);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mIOnPackagesClickListener = (IOnPackagesInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException((context.toString())
                    + "must implement IOnPackagesInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mIOnPackagesClickListener = null;
    }

    private void generateWeightView(ServicesData servicesData) {
       textViewServiceWeight1.setText(servicesData.getBelow500().getWeight());
       textViewServiceWeight2.setText(servicesData.getUpto1kg().getWeight());
       textViewServiceWeight3.setText(servicesData.getUpto2kg().getWeight());
    }

    @OnClick(R.id.btnServiceWeight1)
    public void onFirstWeightClicked(){
        packageWeightId = 0;
        unCheckAllServiceWeightButtons();
        buttonServiceWeight1.setBackgroundResource(R.drawable.checked);
        selectedPackagesWeightList = selectedCategory.getData().getBelow500().getService();
        selectDefaultPackage();
        generatePackageView();
    }
    @OnClick(R.id.btnServiceWeight2)
    public void onSecondWeightClicked(){
        packageWeightId = 1;
        unCheckAllServiceWeightButtons();
        buttonServiceWeight2.setBackgroundResource(R.drawable.checked);
        selectedPackagesWeightList = selectedCategory.getData().getUpto1kg().getService();
        selectDefaultPackage();
        generatePackageView();
    }
    @OnClick(R.id.btnServiceWeight3)
    public void onThirdWeightClicked(){
        packageWeightId = 2;
        unCheckAllServiceWeightButtons();
        buttonServiceWeight3.setBackgroundResource(R.drawable.checked);
        selectedPackagesWeightList = selectedCategory.getData().getUpto2kg().getService();
        selectDefaultPackage();
        generatePackageView();
    }


    public void selectDefaultWeight(){
        packageWeightId = 0;
        unCheckAllServiceWeightButtons();
        buttonServiceWeight1.setBackgroundResource(R.drawable.checked);
        selectedPackagesWeightList = selectedCategory.getData().getBelow500().getService();
        selectDefaultPackage();
        generatePackageView();
    }

    public void selectDefaultPackage(){
        try {
            unCheckAllPackagesButtons();
            buttonPackage1.setBackgroundResource(R.drawable.checked);
            selectedPackage = selectedPackagesWeightList.get(0);
            updatePackageUI(selectedPackage);
        } catch (Exception e) {
            Log.e(TAG, "onItemSelected: ................................ " );
            e.printStackTrace();
        }
    }

    /**
     * generate service packages view
     */
    private void generatePackageView() {
        if (selectedPackagesWeightList.size() == 5){
            makePackage2Visible();
            makePackage3Visible();
            makePackage4Visible();
            makePackage5Visible();
            textViewPackage1.setText(selectedPackagesWeightList.get(0).getName());
            textViewPackage2.setText(selectedPackagesWeightList.get(1).getName());
            textViewPackage3.setText(selectedPackagesWeightList.get(2).getName());
            textViewPackage4.setText(selectedPackagesWeightList.get(3).getName());
            textViewPackage5.setText(selectedPackagesWeightList.get(4).getName());
        }else if (selectedPackagesWeightList.size() == 4){
            makePackage5Gone();
            makePackage2Visible();
            makePackage3Visible();
            makePackage4Visible();
            textViewPackage1.setText(selectedPackagesWeightList.get(0).getName());
            textViewPackage2.setText(selectedPackagesWeightList.get(1).getName());
            textViewPackage3.setText(selectedPackagesWeightList.get(2).getName());
            textViewPackage4.setText(selectedPackagesWeightList.get(3).getName());
        } else if (selectedPackagesWeightList.size() == 3){
            makePackage5Gone();
            makePackage4Gone();
            makePackage2Visible();
            makePackage3Visible();
            textViewPackage1.setText(selectedPackagesWeightList.get(0).getName());
            textViewPackage2.setText(selectedPackagesWeightList.get(1).getName());
            textViewPackage3.setText(selectedPackagesWeightList.get(2).getName());
        } else if (selectedPackagesWeightList.size() == 2){
            makePackage5Gone();
            makePackage4Gone();
            makePackage3Gone();
            makePackage2Visible();
            textViewPackage1.setText(selectedPackagesWeightList.get(0).getName());
            textViewPackage2.setText(selectedPackagesWeightList.get(1).getName());
        } else if (selectedPackagesWeightList.size() == 1){
            makePackage5Gone();
            makePackage4Gone();
            makePackage3Gone();
            makePackage2Gone();
            textViewPackage1.setText(selectedPackagesWeightList.get(0).getName());
        }
    }

    private void makePackage5Gone(){
        buttonPackage5.setVisibility(View.GONE);
        textViewPackage5.setVisibility(View.GONE);
        viewPackage4.setVisibility(View.GONE);
    }
    private void makePackage5Visible(){
        buttonPackage5.setVisibility(View.VISIBLE);
        textViewPackage5.setVisibility(View.VISIBLE);
        viewPackage4.setVisibility(View.VISIBLE);
    }
    private void makePackage4Gone(){
        buttonPackage4.setVisibility(View.GONE);
        textViewPackage4.setVisibility(View.GONE);
        viewPackage3.setVisibility(View.GONE);
    }
    private void makePackage4Visible(){
        buttonPackage4.setVisibility(View.VISIBLE);
        textViewPackage4.setVisibility(View.VISIBLE);
        viewPackage3.setVisibility(View.VISIBLE);
    }
    private void makePackage3Gone(){
        buttonPackage3.setVisibility(View.GONE);
        textViewPackage3.setVisibility(View.GONE);
        viewPackage2.setVisibility(View.GONE);
    }
    private void makePackage3Visible(){
        buttonPackage3.setVisibility(View.VISIBLE);
        textViewPackage3.setVisibility(View.VISIBLE);
        viewPackage2.setVisibility(View.VISIBLE);
    }
    private void makePackage2Gone(){
        buttonPackage2.setVisibility(View.GONE);
        textViewPackage2.setVisibility(View.GONE);
        viewPackage1.setVisibility(View.GONE);
    }
    private void makePackage2Visible(){
        buttonPackage2.setVisibility(View.VISIBLE);
        textViewPackage2.setVisibility(View.VISIBLE);
        viewPackage1.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btnPackage1)
    public void onFirstButtonClicked(){
        unCheckAllPackagesButtons();
        buttonPackage1.setBackgroundResource(R.drawable.checked);
        selectedPackage = selectedPackagesWeightList.get(0);
        updatePackageUI(selectedPackage);
    }

    @OnClick(R.id.btnPackage2)
    public void onSecondButtonClicked(){
        unCheckAllPackagesButtons();
        buttonPackage2.setBackgroundResource(R.drawable.checked);
        selectedPackage = selectedPackagesWeightList.get(1);
        updatePackageUI(selectedPackage);
    }

    @OnClick(R.id.btnPackage3)
    public void onThirdButtonClicked(){
        unCheckAllPackagesButtons();
        buttonPackage3.setBackgroundResource(R.drawable.checked);
        selectedPackage = selectedPackagesWeightList.get(2);
        updatePackageUI(selectedPackage);
    }

    @OnClick(R.id.btnPackage4)
    public void onFourthButtonClicked(){
        unCheckAllPackagesButtons();
        buttonPackage4.setBackgroundResource(R.drawable.checked);
        selectedPackage = selectedPackagesWeightList.get(3);
        updatePackageUI(selectedPackage);
    }
    @OnClick(R.id.btnPackage5)
    public void onFifthButtonClicked(){
        unCheckAllPackagesButtons();
        buttonPackage5.setBackgroundResource(R.drawable.checked);
        selectedPackage = selectedPackagesWeightList.get(4);
        updatePackageUI(selectedPackage);
    }


    public void updatePackageUI(Packages packages) {

        Log.d(TAG, "updatePackageUI: "+packages.toString());
        textViewServicePackagePrice.setText(String.format("%s tk", String.valueOf(packages.getPrice())));
        tvProductDescription.setText(packages.getDescription());
        collectionMinTime = packages.getCollectionMinTime();
        collectionMaxTime = packages.getCollectionMaxTime();
        deliveryMinTime = packages.getDeliveryMinTime();
        deliveryMaxTime = packages.getDeliveryMaxTime();
    }


    @Override
    public void onPromoSuccess() {

        Log.d(TAG, "onPromoSuccess: ............. fragment");
        Toast toast = Toast.makeText(getActivity(), "Promo Successfully Applied!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

    }

    @Override
    public void onPromoFail() {
        Log.d(TAG, "onPromoFail: ..................... fail");
        Toast toast = Toast.makeText(getActivity(), "Promo Failed! Try Another!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}