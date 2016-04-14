package evosoft.example.com.androidplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import evosoft.example.com.androidplay.model.Message;
import evosoft.example.com.androidplay.model.UserBean;
import evosoft.example.com.androidplay.network.NKNetworkManager;
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
        Call<Message> call = NKNetworkManager.getInstance().getsNKNetworkService().checkUserBeanCall(new UserBean("admin","phone"));
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Log.i(TAG, "onResponse: "+response.body().getErrorCode());
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
}
