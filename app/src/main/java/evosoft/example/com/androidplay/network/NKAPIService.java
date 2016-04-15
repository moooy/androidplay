package evosoft.example.com.androidplay.network;

import evosoft.example.com.androidplay.api.model.NKReponse;
import evosoft.example.com.androidplay.model.Message;
import evosoft.example.com.androidplay.model.UserBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by evosoft-C01 on 2016/4/14.
 *
 */
public interface NKAPIService {
    @POST("adai/rest/account/repeat/check")
    Call<Message> checkUserBeanCall(@Body UserBean userBean);

    @POST
    Call<NKReponse> request(@Url String url , @Body Object userBean);

    @GET
    Call<Message> request(@Url String url);


}
