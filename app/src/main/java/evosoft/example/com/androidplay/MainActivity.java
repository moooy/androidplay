package evosoft.example.com.androidplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import evosoft.example.com.androidplay.api.ApiFactory;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onCallClick(View view) {
//        Calendar calendar = Calendar.getInstance();
//        Call<GankDayModel> call = ApiFactory.getNKUserApi()
//                .getDayData(Integer.toString(calendar.get(Calendar.YEAR))
//                        ,Integer.toString(calendar.get(Calendar.MONTH))
//                        ,Integer.toString(calendar.get(Calendar.DATE)));
//        call.enqueue(new Callback<GankDayModel>() {
//            @Override
//            public void onResponse(Call<GankDayModel> call, Response<GankDayModel> response) {
//                Log.i(TAG, "onResponse: "+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<GankDayModel> call, Throwable t) {
//
//            }
//        });

        Call<ResponseBody> call = ApiFactory.getNKUserApi().contributors("square", "retrofit");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
