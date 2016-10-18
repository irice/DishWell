package katey2658.com.my.dishwell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by 11456 on 2016/10/16.
 */

public class CookActivity extends Activity {

    private ImageView cook_info_photo;
    private TextView cook_info_title;
    private TextView cook_info_desc;
    private RequestQueue requestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cook_detail);
        requestQueue= Volley.newRequestQueue(CookActivity.this);
        initView();
    }

    private void initView() {
        cook_info_photo= (ImageView) findViewById(R.id.cook_info_photo);
        cook_info_title= (TextView) findViewById(R.id.cook_info_title);
        cook_info_desc= (TextView) findViewById(R.id.cook_info_desc);

        Intent intent=getIntent();
        Cook cook=(Cook)intent.getParcelableExtra("cook");
        Log.d("TAG","--------------"+cook);
        GetNetDishData getNetDishData=new GetNetDishData(CookActivity.this,new ArrayList<Cook>());
        getNetDishData.setNetBitmap(cook_info_photo,cook.getImg());
        cook_info_title.setText(cook.getName());
        getCookDetail(cook.getId());
    }


    public void getCookDetail(int id) {
        String url = "http://www.tngou.net/api/food/show?id=" + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    cook_info_desc.setText((String) response.get("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        request.setTag("katey2658");
        requestQueue.add(request);
        requestQueue.start();
    }
}
