package evosoft.example.com.androidplay.api.rest;

import evosoft.example.com.androidplay.api.ApiHttpClient;
import evosoft.example.com.androidplay.api.model.NKReponse;
import evosoft.example.com.androidplay.constant.NetworkConfig;
import evosoft.example.com.androidplay.model.UserBean;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author yaocl
 * Created on 2016/4/15.
 */
public class NKUserApi {

    private static final String TAG = "NKUserApi";

   public static void checkUser(String id,String registerType,Callback<NKReponse> callback){
       UserBean userBean = new UserBean(id,registerType);
       Call<NKReponse> call = ApiHttpClient.getInstance().getApiService().postRequest(NetworkConfig.URL_CHECKUSER,userBean);
       call.enqueue(callback);
   }
}
