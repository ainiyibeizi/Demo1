package mail.kotlin.com.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String url = "http://10.0.3.2:8888/Swap/AllGoodsServlet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyOkHttp mMyOkhttp = new MyOkHttp();

        /*Map<String, String> params = new HashMap<>();
        params.put("name", "tsy");
        params.put("age", "24");*/

        mMyOkhttp.post()
                .url(url)
                //.params(params)
                .tag(this)
                .enqueue(new JsonResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        Log.d("aaaa", "doPost onSuccess JSONObject:" + response);
                    }

                    @Override
                    public void onSuccess(int statusCode, JSONArray response) {
                        Log.d("aaaa", "doPost onSuccess JSONArray:" + response);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        Log.d("aaaa", "doPost onFailure:" + error_msg);
                    }
                });
    }
}
