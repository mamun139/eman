package com.jarvishub.emandemo.ui.home;

import com.jarvishub.emandemo.network.CoOrdinates;

import java.util.HashMap;

/**
 * Created by mrrobot on 9/20/17.
 */

public interface IMapPresenter {
    void setView(IMapView view);

    void sendLocation(HashMap<String, Double> coOrdinates);

    void getActiveEmanList(Double latitude, Double longitude);

    void getServicePackages(CoOrdinates coOrdinatesObj);

    void doLogOut();
}
