package com.david.appprojet;

public interface ApiCallback {
    void onSuccess(String result);

    void onError(String message);
}
