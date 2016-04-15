package evosoft.example.com.androidplay.apiV1;

import java.io.IOException;
import java.lang.annotation.Annotation;

import evosoft.example.com.androidplay.apiV1.model.NKApiError;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * @author yaocl
 * Created on 2016/4/15.
 */
public class ApiErrorHelper {

    public static NKApiError parser(Response<?> response){
        Converter<ResponseBody,NKApiError> converter = ApiHttpClient.getInstance().getRetrofit()
                                                  .responseBodyConverter(NKApiError.class,new Annotation[0]);
        NKApiError apiError = null;
        try {
            apiError = converter.convert(response.errorBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiError;
    }
}
