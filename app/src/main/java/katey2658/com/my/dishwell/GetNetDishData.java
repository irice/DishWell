package katey2658.com.my.dishwell;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;

/**
 * Created by 11456 on 2016/10/16.
 */

public class GetNetDishData{

    //创建一个请求队列
    private RequestQueue requestQueue;
    private Context context;
    private List<Cook> cookList;

    public  GetNetDishData(){

    }

    public GetNetDishData(Context context, List<Cook> cookList){
        this.cookList=cookList;
        this.context=context;
        requestQueue= Volley.newRequestQueue(context);
    }

    //请求返回json数据格式-jsonObjectRequest
    public void getCookJsonObject(){
        String url="http://www.tngou.net/api/cook/list?id=3&rows=10";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject jsonObject = null;
                JSONArray jsonArray=null;
                Cook cook = null;
                Parcel dest=null;
                try {
                    jsonArray= (JSONArray) response.get("tngou");
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        jsonObject = (JSONObject) jsonArray.get(i);
                        cook = new Cook((Integer) jsonObject.get("id")
                        ,(String) jsonObject.get("name")
                        ,(String) jsonObject.get("food")
                        ,(String) jsonObject.get("img")
                        ,(String) jsonObject.get("images")
                        ,(String) jsonObject.get("description")
                        ,(String) jsonObject.get("keywords")
                        ,(Integer) jsonObject.get("count")
                        ,(Integer) jsonObject.get("rcount")
                        ,(Integer) jsonObject.get("fcount"));
                        cookList.add(cook);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
                , new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //获取失败后又做什么
//                Snackbar.make(,"获取内容失败",Snackbar.LENGTH_SHORT)
//                        .setAction("忽略", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        }).show();
                Toast.makeText(context,"获取内容失败",Toast.LENGTH_LONG).show();
            }
        });

        request.setTag("katey2658");
        requestQueue.add(request);
        requestQueue.start();
    }




    //获得数据
    public  List<Cook> getCookList(){
        return cookList;
    }


    //获取图片内容
    public void setNetBitmap(ImageView imageView,String url){
        url="http://tnfs.tngou.net/img"+url;

        ImageLoader imageLoader=new ImageLoader(requestQueue,new BitmapCache());

        ImageLoader.ImageListener imageListener= ImageLoader.getImageListener(imageView,R.mipmap.dish,R.mipmap.ic_launcher);
        imageLoader.get(url,imageListener,100,100);

        /*
        ImageRequest request=new ImageRequest(url
                , new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        },0,0,null
        ,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setTag("katey2658");
        requestQueue.add(request);
        requestQueue.start();
        return imageView.getDrawable();
        */
    }

}
