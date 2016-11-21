package app.yy.cn.jrdt.base.impl.menu;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import app.yy.cn.jrdt.base.BaseMenuDetailPager;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class XinWenMenuDetailPager extends BaseMenuDetailPager {
    public XinWenMenuDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        //给针布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("菜单详情页-新闻");
        view.setTextSize(25);
        view.setGravity(Gravity.CENTER);

        return view;
    }
}
