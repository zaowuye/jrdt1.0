package app.yy.cn.jrdt.base.impl.menu;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

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
import app.yy.cn.jrdt.bean.ZuQiuMenu;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class ZuQiuMenuDetailPager extends BaseMenuDetailPager implements ViewPager.OnPageChangeListener {
    @ViewInject(R.id.vp_zuqiu_menu_detail)
    private ViewPager mViewPager;
    private ArrayList<TabDetailPager_ZuQiu> mPagers_zq;

    @ViewInject(R.id.zuqiu_indicator)
    private TabPageIndicator mTabPageIndicator;

    //页签数据
    private ArrayList<ZuQiuMenu.ZuQiuTabData> mZuQiuTabData;

    private ArrayList<TabDetailPager_ZuQiu> mPagers;//页签数据集合
    public ZuQiuMenuDetailPager(Activity activity,ArrayList<ZuQiuMenu.ZuQiuTabData> children) {
        super(activity);
        mZuQiuTabData = children;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity,R.layout.pager_zuqiu_menu_detail,null);
        ViewUtils.inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        mPagers_zq = new ArrayList<>();
        for (int i = 0; i < mZuQiuTabData.size(); i++) {
            TabDetailPager_ZuQiu pager = new TabDetailPager_ZuQiu(mActivity,mZuQiuTabData.get(i));
            mPagers_zq.add(pager);
        }

        mViewPager.setAdapter(new ZuQiuMenuDetailAdapter());
        mTabPageIndicator.setViewPager(mViewPager);//绑定
        mTabPageIndicator.setOnPageChangeListener(this);//滑动监听
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
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

    class ZuQiuMenuDetailAdapter extends PagerAdapter{

        //指示器的标题
        @Override
        public CharSequence getPageTitle(int position) {
            ZuQiuMenu.ZuQiuTabData zuQiuTabData = mZuQiuTabData.get(position);

            return zuQiuTabData.title;
        }

        @Override
        public int getCount() {
            return mPagers_zq.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager_ZuQiu pager = mPagers_zq.get(position);
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

    @OnClick(R.id.zuqiu_btn_next)
    public void nextPage(View view){
        //跳转下个页面
        int currentItem = mViewPager.getCurrentItem();
        currentItem ++;
        mViewPager.setCurrentItem(currentItem);
    }
}
