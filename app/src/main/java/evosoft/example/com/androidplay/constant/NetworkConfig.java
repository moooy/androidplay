package evosoft.example.com.androidplay.constant;

/**
 * @ author yaocl
 * Created on 2016/4/14.
 */
public interface NetworkConfig {
    int CACHE_SIZE = 10 * 1024 * 1024;
    int DEFAULT_TIMEOUT = 5;

    String BASE_URL = "http://192.168.11.101:8099";
    String URL_GANK_BASE = "http://gank.io/api/";

    String URL_CHECKUSER  = BASE_URL+"/adai/rest/account/repeat/check";
}
