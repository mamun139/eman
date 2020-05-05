package com.jarvishub.emandemo.ui.home;

import com.jarvishub.emandemo.model.CreateOrderInfo;
import com.jarvishub.emandemo.model.eman_list.EmansLocationData;

import java.util.List;

/**
 * Created by mrrobot on 9/18/17.
 */

public interface IMapView {
    void removeMarkers();
    void showMarkerAt(List<EmansLocationData> emansLocationList);
    void gotoServicePackagesFrag();
    void gotoCreateOrderFrag(CreateOrderInfo orderInfo);
    void autocompleteSuggestionAddress(Double suggestionAddressLat, Double suggestionAddressLong, String suggestionAddress);
    void PickUpAddress(Double pickupLat, Double pickupLong, String pickupAddress);
    void onDragLocChange(Double latitude, Double longitude, String address );
    void createOrderStatus(String createOrderFlag);
    void onPromoSuccess();
    void onPromoFailed();
    void afterLogOut();
}
