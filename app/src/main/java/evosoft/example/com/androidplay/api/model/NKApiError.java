package evosoft.example.com.androidplay.api.model;

/**
 * @ author yaocl
 * Created on 2016/4/15.
 */
public class NKApiError {
    private int statusCode;
    private String message;

    public NKApiError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("code:").append(statusCode)
                .append("msg:").append(getMessage()).toString();
    }
}
