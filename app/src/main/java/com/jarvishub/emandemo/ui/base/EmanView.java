package com.jarvishub.emandemo.ui.base;

/**
 * Created by mrrobot on 8/21/17.
 */

public interface EmanView {

    void showLoading();

    void hideLoading();

    // void openActivityOnTokenExpire();

    // void onError(@StringRes int resId);

    // void onError(String message);

    void showMessage(String message);

    // void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    //void hideKeyboard();
}
