package evosoft.example.com.androidplay.api;

import evosoft.example.com.androidplay.api.model.NKReponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * @ author yaocl
 * Created on 2016/4/15.
 */
public interface ApiService {

    @POST
    Call<NKReponse> postRequest(@Url String path, @Body Object body);

}
