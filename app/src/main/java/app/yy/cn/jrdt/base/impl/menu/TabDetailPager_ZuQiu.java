package app.yy.cn.jrdt.base.impl.menu;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import app.yy.cn.jrdt.R;
import app.yy.cn.jrdt.base.BaseMenuDetailPager;
import app.yy.cn.jrdt.bean.ZuQiuMenu;
import app.yy.cn.jrdt.bean.ZuQiuTabBean;
import app.yy.cn.jrdt.view.PubllToReFreshListViewe;

/**
 * Created by Administrator on 2016/12/8 0008.
 */
public class TabDetailPager_ZuQiu extends BaseMenuDetailPager{

    private ZuQiuMenu.ZuQiuTabData mZuQiuTabData;//单个页签
    private TextView view;
    private final String mUrl;
    @ViewInject(R.id.lv_list_zuqiu)
    private PubllToReFreshListViewe listView;
    private ZuQiuTabBean.SaiCheng mZuQiuList;
    private ZuQiuAdapter mZuQiuAdapter;

    public TabDetailPager_ZuQiu(Activity activity, ZuQiuMenu.ZuQiuTabData zuQiuTabData) {
        super(activity);
      mZuQiuTabData = zuQiuTabData;
        mUrl = mZuQiuTabData.url;
    }



    @Override
    public View initView() {
        //给针布局填充布局对象
//        view = new TextView(mActivity);
//        view.setTextSize(25);
//        view.setGravity(Gravity.CENTER);
        View view = View.inflate(mActivity,R.layout.pager_tab_datail_zuqiu,null);
        ViewUtils.inject(this, view);

        return view;
    }

    @Override
    public void initData() {
//        view.setText(mZuQiuTabData.title);
//        System.out.println("3"+mZuQiuTabData);
        getDataFromServer();
    }

    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
//                System.out.println(result);
                processData(result,false);
                //收起下拉控件
//                listView.onRefreshComplete(true);
            }


            @Override
            public void onFailure(HttpException e, String s) {
                //请求失败
                e.printStackTrace();
                Toast.makeText(mActivity, "" + s, Toast.LENGTH_SHORT).show();
                //收起下拉控件
//                listView.onRefreshComplete(false);
            }
        });
    }

    private void processData(String result, boolean b) {
        Gson gson = new Gson();
        ZuQiuTabBean zuQiuTabBean = gson.fromJson(result, ZuQiuTabBean.class);
        mZuQiuList = zuQiuTabBean.result.views;
//        System.out.println("大大的 "+zuQiuTabBean.result.views.saicheng1.get(0).c1);
//        System.out.println("saicheng1"+mZuQiuList);

        if (mZuQiuList.saicheng1 != null){
            mZuQiuAdapter = new ZuQiuAdapter();
            listView.setAdapter(mZuQiuAdapter);

        }


    }

    class ZuQiuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mZuQiuList.saicheng1.size();
        }

        @Override
        public ZuQiuTabBean.Sc1 getItem(int i) {
            return mZuQiuList.saicheng1.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder_ZuQiu holder;
            if (view == null){
                view = View.inflate(mActivity,R.layout.list_item_zuqiu,null);
                holder = new ViewHolder_ZuQiu();
                holder.c1 = (TextView) view.findViewById(R.id.c1);
                holder.c2 = (TextView) view.findViewById(R.id.c2);
                holder.c3 = (TextView) view.findViewById(R.id.c3);
                holder.c4T1 = (TextView) view.findViewById(R.id.c4T1);
                holder.c4R = (TextView) view.findViewById(R.id.c4R);
                holder.c4T2 = (TextView) view.findViewById(R.id.c4T2);
                holder.c51 = (TextView) view.findViewById(R.id.c51);
                holder.c52 = (TextView) view.findViewById(R.id.c52);
                view.setTag(holder);
            }else {
                holder = (ViewHolder_ZuQiu) view.getTag();
            }

            ZuQiuTabBean.Sc1 zuqiu = getItem(i);
            holder.c1.setText(zuqiu.c1);
            holder.c2.setText(zuqiu.c2);
            holder.c3.setText(zuqiu.c3);
            holder.c4T1.setText(zuqiu.c4T1);
            holder.c4R.setText(zuqiu.c4R);
            holder.c4T2.setText(zuqiu.c4T2);
            holder.c51.setText(zuqiu.c51);
            holder.c52.setText(zuqiu.c52);


            return view;
        }
    }

    static class ViewHolder_ZuQiu {
        public TextView c1;
        public TextView c2;
        public TextView c3;
        public TextView c4T1;
        public TextView c4R;
        public TextView c4T2;
        public TextView c51;
        public TextView c52;

    }
}
