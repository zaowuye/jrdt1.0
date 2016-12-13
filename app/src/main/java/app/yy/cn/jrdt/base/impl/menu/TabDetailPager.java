package app.yy.cn.jrdt.base.impl.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import app.yy.cn.jrdt.NewsDetailActivity;
import app.yy.cn.jrdt.R;
import app.yy.cn.jrdt.base.BaseMenuDetailPager;
import app.yy.cn.jrdt.bean.XinWenMenu;
import app.yy.cn.jrdt.bean.XinWenTabBean;
import app.yy.cn.jrdt.tool.PrefUtils;
import app.yy.cn.jrdt.tool.Url;
import app.yy.cn.jrdt.view.PubllToReFreshListViewe;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class TabDetailPager extends BaseMenuDetailPager {

    private XinWenMenu.XinWenTabData mTabData;//单个页签网络数据
    private TextView view;
    private final String mUrl;

    @ViewInject(R.id.lv_list)
    private PubllToReFreshListViewe listView;
    private ArrayList<XinWenTabBean.NewsData> mNewsList;
    private NewsAdapter mNewsAdapter;


    public TabDetailPager(Activity activity, XinWenMenu.XinWenTabData xinWenTabData) {
        super(activity);
        mTabData = xinWenTabData;

        mUrl = mTabData.url;
    }



    @Override
    public View initView() {
        //给针布局填充布局对象
//        view = new TextView(mActivity);
//        view.setText(mTabData.title);//此处空指针异常
//        view.setTextSize(25);
//        view.setGravity(Gravity.CENTER);
        View view = View.inflate(mActivity, R.layout.pager_tab_datail, null);
        ViewUtils.inject(this, view);

        /**
         * 5.设置回调,前端界面
         */
        listView.setOnRefreshListener(new PubllToReFreshListViewe.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                getDataFromServer();
            }

//            @Override
//            public void onLoadMore() {
//                //加载下一页数据
//                if (mMoreUrl != null){
//                    //有下一页
//                    getMoreDataFromServer();
//                }else {
//                    Toast.makeText(mActivity, "没有更多数据啦", Toast.LENGTH_SHORT).show();
//                }
//            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //设置新闻的点击事件
                //获取头布局占位
                int headerViewsCount = listView.getHeaderViewsCount();
                //减去头布局占位
                i = i - headerViewsCount;
                System.out.println("第"+ i+"被点击了");

                XinWenTabBean.NewsData newsData = mNewsList.get(i);
                String read_ids = PrefUtils.getString(mActivity, "read_ids", "");
               //避免重复添加
                if (!read_ids.contains(newsData.thumbnail_pic_s)){
                    read_ids = read_ids + newsData.thumbnail_pic_s + ",";
                    PrefUtils.setString(mActivity,"read_ids",read_ids);
                }
                //改变已读颜色
                TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
                tvTitle.setTextColor(Color.GRAY);

                //跳到新闻详情页
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("url",newsData.url);
                intent.putExtra("title",newsData.title);
                intent.putExtra("img",newsData.thumbnail_pic_s);
                intent.putExtra("name",newsData.author_name);
                mActivity.startActivity(intent);

            }
        });


        return view;
    }

    @Override
    public void initData() {
//        view.setText(mTabData.title);
        getDataFromServer();


    }

    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                System.out.println(result);
                processData(result,false);
                //收起下拉控件
                listView.onRefreshComplete(true);
            }


            @Override
            public void onFailure(HttpException e, String s) {
                //请求失败
                e.printStackTrace();
                Toast.makeText(mActivity, "" + s, Toast.LENGTH_SHORT).show();
                //收起下拉控件
                listView.onRefreshComplete(false);
            }
        });
    }

    private void processData(String result,boolean isMore) {
        Gson gson = new Gson();
        XinWenTabBean xinWenTabBean = gson.fromJson(result, XinWenTabBean.class);
        System.out.println(xinWenTabBean);

//        加载 更多
//        String moreUrl = xinWenTabBean.result.下一页地址;
//        有可能是空字符串
//        if (TextUtils.isEmpty(moreUrl)){
//          String mMoreUrl = Url.下一页数据连接
//        }else {
//            mMoreUrl = null;
//        }

        if (!isMore){
            mNewsList = xinWenTabBean.result.data;
            if (mNewsList != null) {
                mNewsAdapter = new NewsAdapter();
                listView.setAdapter(mNewsAdapter);
            }
        }else {
//            ArrayList<XinWenMenu.XinWenTabData> moreXinWen = xinWenTabBean.result.data;
//            mNewsList.addAll(moreXinWen);
//            mNewsAdapter.notifyDataSetChanged();
        }




    }

    /**
     * 加载下一页数据
     */
    public void getMoreDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, mUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                System.out.println(result);
                processData(result,true);
                //收起下拉控件
                listView.onRefreshComplete(true);
            }


            @Override
            public void onFailure(HttpException e, String s) {
                //请求失败
                e.printStackTrace();
                Toast.makeText(mActivity, "" + s, Toast.LENGTH_SHORT).show();
                //收起下拉控件
                listView.onRefreshComplete(false);
            }
        });
    }

    class NewsAdapter extends BaseAdapter {

        private final BitmapUtils mBitmapUtils;

        public NewsAdapter() {
            mBitmapUtils = new BitmapUtils(mActivity);
            mBitmapUtils.configDefaultLoadingImage(R.drawable.news_pic_default);
        }

        @Override
        public int getCount() {
            return mNewsList.size();
        }

        @Override
        public XinWenTabBean.NewsData getItem(int i) {
            return mNewsList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = View.inflate(mActivity, R.layout.list_item_news, null);
                holder = new ViewHolder();
                holder.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
                holder.tvTitle = (TextView) view.findViewById(R.id.tv_title);
                holder.tvname = (TextView) view.findViewById(R.id.tv_name);
                holder.tvDate = (TextView) view.findViewById(R.id.tv_date);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();

            }
            XinWenTabBean.NewsData news = getItem(i);
            holder.tvTitle.setText(news.title);
            holder.tvname.setText(news.author_name);
            holder.tvDate.setText(news.date);

            //更改已读颜色,根据标记
            String readIds = PrefUtils.getString(mActivity,"read_ids","");
            if (readIds.contains(news.thumbnail_pic_s + "")){
                holder.tvTitle.setTextColor(Color.GRAY);
            }else {
                holder.tvTitle.setTextColor(Color.BLACK);
            }

            mBitmapUtils.display(holder.ivIcon, news.thumbnail_pic_s);
            return view;
        }
    }

    static class ViewHolder {
        public ImageView ivIcon;
        public TextView tvTitle;
        public TextView tvDate;
        public TextView tvname;
    }
}

//头条新闻是撇弃


