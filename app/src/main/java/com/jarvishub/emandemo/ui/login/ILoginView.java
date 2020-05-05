package com.jarvishub.emandemo.ui.login;


public interface ILoginView {
    void navigateToHomeActivity();

    void loginFailed();

    void showLoading();

    void hideLoading();
}
