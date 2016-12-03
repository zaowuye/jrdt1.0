package app.yy.cn.jrdt.base.impl.menu;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

import app.yy.cn.jrdt.MainActivity;
import app.yy.cn.jrdt.R;
import app.yy.cn.jrdt.base.BaseMenuDetailPager;
import app.yy.cn.jrdt.bean.XinWenMenu;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class XinWenMenuDetailPager extends BaseMenuDetailPager implements ViewPager.OnPageChangeListener{
    @ViewInject(R.id.vp_xinwen_menu_detail)
    private ViewPager mViewPager;
    @ViewInject(R.id.indicator)
    private TabPageIndicator mIndicator;


    //页签数据
    private ArrayList<XinWenMenu.XinWenTabData> mXinWenTabData;

    private ArrayList<TabDetailPager> mPagers;//页签数据集合

    public XinWenMenuDetailPager(Activity activity, ArrayList<XinWenMenu.XinWenTabData> children) {
        super(activity);
        mXinWenTabData = children;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity,R.layout.pager_xinwen_menu_detail,null);
        ViewUtils.inject(this,view);

        return view;
    }

    @Override
    public void initData() {
        mPagers = new ArrayList<>();
        for (int i = 0; i < mXinWenTabData.size(); i++) {
            TabDetailPager pager = new TabDetailPager(mActivity,mXinWenTabData.get(i));
            mPagers.add(pager);
        }

        mViewPager.setAdapter(new XinWenDetailAdapter());
        mIndicator.setViewPager(mViewPager);//绑定VIewPager和指示器,必须在ViewPager设置完数据之后再绑定

//        mViewPager.setOnPageChangeListener(this);
        mIndicator.setOnPageChangeListener(this);//必须给指示器设置监听,不能给ViewPager设置监听
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        System.out.println(position);
        if (position == 0){
            //开启侧边栏
            setSlidingMenuEnable(true);
        }else {
            //禁用侧边栏
            setSlidingMenuEnable(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 开启或禁用侧边栏
     *
     */
    protected void setSlidingMenuEnable(boolean enable){
        //获取侧边栏
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        if (enable){
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        }else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    class XinWenDetailAdapter extends PagerAdapter{
        //指示器的变体
        @Override
        public CharSequence getPageTitle(int position) {
            XinWenMenu.XinWenTabData data = mXinWenTabData.get(position);
            return data.title;
        }

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager pager = mPagers.get(position);
            View view = pager.mRootView;
            container.addView(view);
            pager.initData();
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    @OnClick(R.id.btn_next)
    public void nextPage(View view){
        //跳转下个页面
        int currentItem = mViewPager.getCurrentItem();
        currentItem ++;
        mViewPager.setCurrentItem(currentItem);
    }
}
