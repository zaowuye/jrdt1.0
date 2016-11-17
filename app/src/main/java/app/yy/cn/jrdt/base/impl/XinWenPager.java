package app.yy.cn.jrdt.base.impl;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import app.yy.cn.jrdt.base.BasePager;
import app.yy.cn.jrdt.tool.Url;

/**
 * 新闻
 * Created by Administrator on 2016/11/17 0017.
 */
public class XinWenPager extends BasePager {


    public XinWenPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        System.out.println("新闻初始化");

        //给针布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("今日新闻");
        view.setTextSize(25);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);

        getServierData();




    }

    public void getServierData() {
        System.out.println("调用网络方法");
        //请求网络，拿取数据
        //String URL = Url.XZ_RUL+"consName="+CN+"&type="+Type+"&key="+Url.XZ_URL_KEY;

        RequestQueue mQueue = Volley.newRequestQueue(mActivity.getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, Url.XW_URL, new Response.Listener<String>() {
           @Override
           public void onResponse(String s) {
               System.out.println("访问成功");
               Toast.makeText(mActivity.getApplicationContext(), "返回结果"+s, Toast.LENGTH_SHORT).show();
                processData(s);

















           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError volleyError) {
               System.out.println("访问失败");
               Toast.makeText(mActivity.getApplicationContext(), "错误"+volleyError, Toast.LENGTH_SHORT).show();
           }
       });
        request.setTag("XZ");
        mQueue.add(request);
    }

    /**
     * 解析JSON
     */
    private void processData(String json) {
        //GSON解析
        Gson gson = new Gson();
//        gson.fromJson(json,);
    }
}
