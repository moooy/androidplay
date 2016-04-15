package evosoft.example.com.androidplay.apiV1;

import evosoft.example.com.androidplay.apiV1.rest.NKUserApi;

/**
 * @ author yaocl
 * Created on 2016/4/15.
 */
public class ApiFactory {
    private static NKUserApi mNKUserApi;

    public static NKUserApi getNKUserApi(){
        if (mNKUserApi == null) {
            mNKUserApi = ApiHttpClient.getInstance().createApi(NKUserApi.class);
        }
        return mNKUserApi;
    }
}
