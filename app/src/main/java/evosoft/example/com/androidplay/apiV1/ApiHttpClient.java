package evosoft.example.com.androidplay.apiV1;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import evosoft.example.com.androidplay.constant.NetworkConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private final HttpLoggingInterceptor mHttpLoggingInterceptor;

    private ApiHttpClient(){
        mHttpLoggingInterceptor = new HttpLoggingInterceptor();//设置log网络拦截器
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

       mOkHttpClient = new OkHttpClient().newBuilder()
               .retryOnConnectionFailure(true)
               .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
               .addNetworkInterceptor(new StethoInterceptor())
               .addInterceptor(mHttpLoggingInterceptor)
               .build();

        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConfig.BASE_URL)
                .build();
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

    public <T> T createApi(Class<T> api){
        return mRetrofit.create(api);
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
