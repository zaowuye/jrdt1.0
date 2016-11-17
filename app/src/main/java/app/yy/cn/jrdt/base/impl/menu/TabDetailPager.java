package app.yy.cn.jrdt.base.impl.menu;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import app.yy.cn.jrdt.base.BaseMenuDetailPager;

/**页签
 * Created by Administrator on 2016/11/17 0017.
 */
public class TabDetailPager extends BaseMenuDetailPager{


    public TabDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        TextView view = new TextView(mActivity);
        view.setText("今日新闻");
        view.setTextSize(25);
        view.setGravity(Gravity.CENTER);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
