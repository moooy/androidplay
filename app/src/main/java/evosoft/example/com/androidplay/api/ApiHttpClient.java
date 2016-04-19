package evosoft.example.com.androidplay.api;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import evosoft.example.com.androidplay.MyApplication;
import evosoft.example.com.androidplay.utils.NetworkUtil;
import evosoft.example.com.androidplay.constant.NetworkConfig;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ author yaocl
 * Created on 2016/4/15.
 */
public class ApiHttpClient {
    private static ApiHttpClient mApiHttpClient = null;
    private  Retrofit mRetrofit;

    private ApiHttpClient(){
        mRetrofit = new Retrofit.Builder()
                .client(createOkHttpClient(MyApplication.getInstance()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(NetworkConfig.URL_GANK_BASE)
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


    /**
     *
     * @param context
     * @return
     */
    private OkHttpClient createOkHttpClient(Context context){
        File cacheDir = new File(context.getCacheDir(),"http");
        Cache cache = new Cache(cacheDir,NetworkConfig.CACHE_SIZE);

        return new OkHttpClient().newBuilder()
                .cache(cache)
                .retryOnConnectionFailure(true)
                .connectTimeout(NetworkConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(createLoggingInterceptor())
                .addNetworkInterceptor(createInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

    }


    /**
     * 网络日志拦截器
     * @return
     */
    private HttpLoggingInterceptor createLoggingInterceptor(){
        HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor();
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return mHttpLoggingInterceptor;
    }


    /**
     * 网络请求拦截器
     * @return
     */
    private Interceptor createInterceptor(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                if(NetworkUtil.isNetworkAvailable(MyApplication.getInstance().getApplicationContext())){
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .header("User-Agent", "OkHttp Example")
                            .build();
                }

                Response originalResponse = chain.proceed(chain.request());
                //有网络的情况下设置60秒的过期时间
                if (NetworkUtil.isNetworkAvailable(MyApplication.getInstance().getApplicationContext())) {
                    int maxAge = 60;
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control","public,only-if-cached,max-age="+maxAge)
                            .build();
                }else {
                    /**
                     * 没有网络的情况下，设置四周的过期时间
                     */
                    int maxStale = 60*60*24*28;
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control","public,only-if-cached,max-stale="+maxStale)
                            .build();
                }
            }
        };
        return interceptor;
    }

}
