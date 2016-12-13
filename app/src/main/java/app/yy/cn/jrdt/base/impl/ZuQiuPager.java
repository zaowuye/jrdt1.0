package app.yy.cn.jrdt.base.impl;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import app.yy.cn.jrdt.base.BaseMenuDetailPager;
import app.yy.cn.jrdt.base.BasePager;
import app.yy.cn.jrdt.base.impl.menu.ZuQiuMenuDetailPager;
import app.yy.cn.jrdt.bean.ZuQiuMenu;
import app.yy.cn.jrdt.tool.CacheUtils;
import app.yy.cn.jrdt.tool.Url;

/**
 * 新闻
 * Created by Administrator on 2016/11/17 0017.
 */
public class ZuQiuPager extends BasePager {
    private ArrayList<BaseMenuDetailPager> mMenuDetaiPagers;//菜单详情页集合
    private ZuQiuMenu zuQiuData;


    public ZuQiuPager(Activity activity) {
        super(activity);
    }


    @Override
    public void initData() {
        System.out.println("足球初始化");
//        //给针布局填充布局对象
//        TextView view = new TextView(mActivity);
//        view.setText("今日足球");
//        view.setTextSize(25);
//        view.setGravity(Gravity.CENTER);

//        flContent.addView(view);

        tvTitle.setText("足球赛程");
        //先判断缓存,优先加载本地缓存
        String cache = CacheUtils.getCache(Url.ZQ_BASE, mActivity);
        if (!TextUtils.isEmpty(cache)) {
            System.out.println("发现缓存啦");
            processData(cache);
        }
        getServierData();


    }

    public void getServierData() {
        System.out.println("调用网络方法");
//        通过VOLLEY方法请求网络.
//        //请求网络，拿取数据
//        RequestQueue mQueue = Volley.newRequestQueue(mActivity.getApplicationContext());
//        StringRequest request = new StringRequest(Request.Method.GET, Url.XW_URL, new Response.Listener<String>() {
//           @Override
//           public void onResponse(String s) {
//               System.out.println("访问成功");
//               Toast.makeText(mActivity.getApplicationContext(), "返回结果"+s, Toast.LENGTH_SHORT).show();
//                processData(s);
//               System.out.println(s);
//           }
//       }, new Response.ErrorListener() {
//           @Override
//           public void onErrorResponse(VolleyError volleyError) {
//               System.out.println("访问失败");
//               Toast.makeText(mActivity.getApplicationContext(), "错误"+volleyError, Toast.LENGTH_SHORT).show();
//           }
//       });
//        request.setTag("XZ");
//        mQueue.add(request);

        //通过Xutils方法请求网络
//        HttpUtils utils = new HttpUtils();
//        utils.send(HttpRequest.HttpMethod.GET, Url.XW_BASE, new RequestCallBack<String>() {
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//                //请求成功
//                processData(Url.XW_BASE);
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                //请求失败
//            }
//        });
        String result = Url.ZQ_BASE;
        processData(Url.ZQ_BASE);
        //写缓存
        CacheUtils.setCache(Url.ZQ_BASE,result,mActivity);
    }
    /**
     * 解析JSON
     */
    private void processData(String json) {
        //GSON解析
        Gson gson = new Gson();
        zuQiuData = gson.fromJson(json, ZuQiuMenu.class);

        mMenuDetaiPagers = new ArrayList<>();
        mMenuDetaiPagers.add(new ZuQiuMenuDetailPager(mActivity,zuQiuData.data.get(0).children));
        //重新给frameLayout添加内容
        BaseMenuDetailPager pager = mMenuDetaiPagers.get(0);//获取当前应该显示的
        View view = pager.mRootView;//当前页面的布局

        //清楚旧布局
        flContent.removeAllViews();

        flContent.addView(view);//给针布局

        //初始化页面数据
        pager.initData();


    }

}
