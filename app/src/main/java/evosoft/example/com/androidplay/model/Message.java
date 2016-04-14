package evosoft.example.com.androidplay.model;

/**
 * @ author yaocl
 * Created on 2016/4/14.
 */
public class Message {


    /**
     * errorCode : 0
     * errorMsg : yyyy
     * value : xxxxxxxxx
     */

    private String errorCode;
    private String errorMsg;

    public Message(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
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
}
