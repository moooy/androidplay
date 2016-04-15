package evosoft.example.com.androidplay.api.model;

import android.support.annotation.Nullable;

/**
 * @ author yaocl
 * Created on 2016/4/15.
 */
public class NKReponse<T> {

    /**
     * errorCode : 0
     * errorMsg : yyyy
     * value : xxxxxxxxx
     */

    private String errorCode;
    private String errorMsg;
    private T value;

    public NKReponse(String errorCode, @Nullable String errorMsg, @Nullable T value) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.value = value;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isSuccess(){
        return errorCode.equals("0");
    }
}
