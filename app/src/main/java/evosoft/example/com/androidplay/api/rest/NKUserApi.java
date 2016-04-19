package evosoft.example.com.androidplay.api.rest;

import evosoft.example.com.androidplay.api.model.GankDayModel;
import evosoft.example.com.androidplay.api.model.NKReponse;
import evosoft.example.com.androidplay.model.UserBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author yaocl
 * Created on 2016/4/15.
 */
public interface NKUserApi {
    @POST("adai/rest/account/repeat/check")
    Call<NKReponse> checkUser(@Body UserBean userBean);

    @GET("day/{year}/{month}/{day}")
    Call<GankDayModel> getDayData(@Path("year") String year, @Path("month") String month,@Path("day") String day);

    @GET("https://api.github.com/repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributors(@Path("owner") String owner, @Path("repo") String repo);
}
