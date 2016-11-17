package app.yy.cn.jrdt.base.impl;

import android.app.Activity;
import android.view.Gravity;
import android.widget.TextView;

import app.yy.cn.jrdt.base.BasePager;

/**
 * 新闻
 * Created by Administrator on 2016/11/17 0017.
 */
public class GuShiPager extends BasePager {

    public GuShiPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        System.out.println("股市初始化");

        //给针布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("今日股市");
        view.setTextSize(25);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);

        tvTitle.setText("股市行情");
    }
}
