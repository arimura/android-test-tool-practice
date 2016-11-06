package com.hormiga6.androidtesttoolpractice;

/**
 * Created by kotaro.arimura on 2016/10/25.
 */
class DummyModel {
    private String message = "default message";

    public String getMessage() {
        return message;
    }

    public String getClassName(){
        return DummyModel.class.getName();
    }
}
