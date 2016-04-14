package evosoft.example.com.androidplay.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import evosoft.example.com.androidplay.common.constant.NetworkConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ author yaocl
 * Created on 2016/4/14.
 */
public class NKNetworkManager {

    private static final int DEFAULT_TIMEOUT = 5;

    private static NKNetworkManager  sNetworkManager = null;

    private  NKAPIService sNKNetworkService;
    private  Retrofit sRetrofit;

    private NKNetworkManager(){

        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        httpBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        sRetrofit = new Retrofit.Builder()
                .client(httpBuilder.addNetworkInterceptor(new StethoInterceptor()).build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConfig.BASE_URL)
                .build();

        sNKNetworkService = sRetrofit.create(NKAPIService.class);
    }

    public NKAPIService getsNKNetworkService() {
        return sNKNetworkService;
    }

    public Retrofit getsRetrofit() {
        return sRetrofit;
    }

    public static NKNetworkManager getInstance(){
        if (sNetworkManager == null) {
            synchronized (NKNetworkManager.class){
                if (sNetworkManager == null) {
                    sNetworkManager = new NKNetworkManager();
                }
            }
        }
        return  sNetworkManager;
    }


}
