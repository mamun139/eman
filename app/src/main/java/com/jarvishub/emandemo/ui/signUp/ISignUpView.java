package com.jarvishub.emandemo.ui.signUp;

/**
 * Created by mrrobot on 8/23/17.
 */

public interface ISignUpView {
    void navigateToVerificationActivity();
    void signUpFailed();
    void showLoading();
    void hideLoading();
}
