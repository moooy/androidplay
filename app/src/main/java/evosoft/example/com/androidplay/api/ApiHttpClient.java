package evosoft.example.com.androidplay.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import evosoft.example.com.androidplay.constant.NetworkConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ author yaocl
 * Created on 2016/4/15.
 */
public class ApiHttpClient {

    private static final int DEFAULT_TIMEOUT = 5;

    private static ApiHttpClient mApiHttpClient = null;
    private final Retrofit mRetrofit;
    private final OkHttpClient mOkHttpClient;
    private final ApiService mApiService;

    private ApiHttpClient(){
       mOkHttpClient = new OkHttpClient().newBuilder()
               .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
               .addInterceptor(new StethoInterceptor())
               .build();

        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConfig.BASE_URL)
                .build();

        mApiService = mRetrofit.create(ApiService.class);

    }

    public static ApiHttpClient getInstance(){
        if (mApiHttpClient == null) {
            synchronized (ApiHttpClient.class){
                if (mApiHttpClient == null) {
                    mApiHttpClient = new ApiHttpClient();
                }
            }
        }
        return mApiHttpClient;
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
