package evosoft.example.com.androidplay.apiV1.rest;

import evosoft.example.com.androidplay.apiV1.model.NKReponse;
import evosoft.example.com.androidplay.model.UserBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author yaocl
 * Created on 2016/4/15.
 */
public interface NKUserApi {
    @POST("adai/rest/account/repeat/check")
    Call<NKReponse> checkUser(@Body UserBean userBean);
}
