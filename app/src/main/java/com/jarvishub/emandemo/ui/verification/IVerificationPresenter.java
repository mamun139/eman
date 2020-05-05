package com.jarvishub.emandemo.ui.verification;

/**
 * Created with love by mrrobot on 11/29/17.
 */

public interface IVerificationPresenter {
    void setView(IVerificationView view);
    void authenticateVerificationCode(String verificationCode);
}
