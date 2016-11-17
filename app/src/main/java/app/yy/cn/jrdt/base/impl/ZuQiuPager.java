package app.yy.cn.jrdt.base.impl;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

import app.yy.cn.jrdt.base.BasePager;

/**
 * 新闻
 * Created by Administrator on 2016/11/17 0017.
 */
public class ZuQiuPager extends BasePager {

    public ZuQiuPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        System.out.println("足球初始化");

        //给针布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("今日足球");
        view.setTextSize(25);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);

        tvTitle.setText("足球联赛");
    }
}
