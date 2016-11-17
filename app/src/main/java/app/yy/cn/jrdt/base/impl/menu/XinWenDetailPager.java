package app.yy.cn.jrdt.base.impl.menu;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import app.yy.cn.jrdt.R;
import app.yy.cn.jrdt.base.BaseMenuDetailPager;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class XinWenDetailPager extends BaseMenuDetailPager {
    @ViewInject(R.id.vp_xinwen_menu)
    private ViewPager mViewPager;



    public XinWenDetailPager(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_xinwen_menu,null);
        ViewUtils.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        //初始化页签

    }

    class XinWenDetailAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
