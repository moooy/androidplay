package evosoft.example.com.androidplay.apiV1;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import evosoft.example.com.androidplay.MyApplication;
import evosoft.example.com.androidplay.common.utils.NetworkUtil;
import evosoft.example.com.androidplay.constant.NetworkConfig;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
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
        //设置网络请求Log拦截器
        mHttpLoggingInterceptor = new HttpLoggingInterceptor();
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //设置网络请求缓存
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                
                Response originalResponse = chain.proceed(chain.request());
                //有网络的情况下设置60秒的过期时间
                if (NetworkUtil.isNetworkAvailable(MyApplication.getInstance().getApplicationContext())) {
                    int maxAge = 60;
                    return originalResponse.newBuilder()
                            .header("Cache-Control","public,only-if-cached,max-age="+maxAge)
                            .build();
                }else {
                    /**
                     * 没有网络的情况下，设置四周的过期时间
                     */
                    int maxStale = 60*60*24*28;
                    return originalResponse.newBuilder()
                            .header("Cache-Control","public,only-if-cached,max-stale="+maxStale)
                            .build();
                }
            }
        };

        //setup cache
        File httpCacheDirectory = new File(MyApplication.getInstance().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

       mOkHttpClient = new OkHttpClient().newBuilder()
               .retryOnConnectionFailure(true)
               .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
               .addNetworkInterceptor(interceptor)
               .addNetworkInterceptor(new StethoInterceptor())
               .cache(cache)
               .addInterceptor(mHttpLoggingInterceptor)
               .build();

        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
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
}
