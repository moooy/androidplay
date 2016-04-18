package evosoft.example.com.androidplay;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import evosoft.example.com.androidplay.apiV1.ApiErrorHelper;
import evosoft.example.com.androidplay.apiV1.ApiFactory;
import evosoft.example.com.androidplay.apiV1.model.NKApiError;
import evosoft.example.com.androidplay.apiV1.model.NKReponse;
import evosoft.example.com.androidplay.model.UserBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/fresco-logo.png");
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);

    }

    public void onCallClick(View view) {
//        Call<Message> call = NKNetworkManager.getInstance().getsNKNetworkService().request(NetworkConfig.URL_CHECKUSER,new UserBean("admin","phone"));
//        call.enqueue(new Callback<Message>() {
//            @Override
//            public void onResponse(Call<Message> call, Response<Message> response) {
//                Log.i(TAG, "onResponse: "+response.body().getErrorCode());
//            }
//
//            @Override
//            public void onFailure(Call<Message> call, Throwable t) {
//
//            }
//        });
//        ApiFactory.getNKUserApi().checkUser("admin", "phone",new Callback<NKReponse>() {
//            @Override
//            public void onResponse(Call<NKReponse> call, Response<NKReponse> response) {
//                Log.i(TAG, "onResponse: ");
//            }
//
//            @Override
//            public void onFailure(Call<NKReponse> call, Throwable t) {
//
//            }
//        });

        Call<NKReponse> call =  ApiFactory.getNKUserApi().checkUser(new UserBean("admin11","phone"));
        call.enqueue(new Callback<NKReponse>() {
            @Override
            public void onResponse(Call<NKReponse> call, Response<NKReponse> response) {
                Log.i(TAG, "onResponse: "+response.body().getErrorMsg());
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: "+"成功获取");
                }else{
                    NKApiError apiError = ApiErrorHelper.parser(response);
                    Log.d(TAG, "onResponse: "+apiError.getMessage());
                }
            }

            @Override
            public void onFailure(Call<NKReponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t);
            }
        });
    }
}
