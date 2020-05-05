package com.jarvishub.emandemo.ui.splash;

import com.jarvishub.emandemo.ui.base.EmanView;

/**
 * Created by mrrobot on 8/21/17.
 */

public interface SplashView extends EmanView {
    void gotoHomeActivity();
    void gotoLoginActivity();
    void discardSplash();
    void showAlert(String message);
    void startSyncService();
}
