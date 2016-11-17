package app.yy.cn.jrdt.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import app.yy.cn.jrdt.R;
import app.yy.cn.jrdt.base.BasePager;
import app.yy.cn.jrdt.base.impl.GuShiPager;
import app.yy.cn.jrdt.base.impl.LanQiuPager;
import app.yy.cn.jrdt.base.impl.XinWenPager;
import app.yy.cn.jrdt.base.impl.ZuQiuPager;
import app.yy.cn.jrdt.view.NoSccrollViewpager;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class ContentFragment extends BaseFragment {

    private NoSccrollViewpager mViewPager;
    private RadioGroup rgGroup;

    private ArrayList<BasePager> mPager;//标签集合

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        mViewPager = (NoSccrollViewpager) view.findViewById(R.id.vp_content);
        rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);

        return view;
    }

    @Override
    public void initData() {
        mPager = new ArrayList<>();

        //添加标签
        mPager.add(new XinWenPager(mActivity));
        mPager.add(new ZuQiuPager(mActivity));
        mPager.add(new LanQiuPager(mActivity));
        mPager.add(new GuShiPager(mActivity));

        mViewPager.setAdapter(new ContentAdapter());
        //标签切换
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {

                    case R.id.xinwen:
                        //新闻
                     //   mViewPager.setCurrentItem(0);//滑动动画
                        mViewPager.setCurrentItem(0,false);//没有滑动动画
                        break;
                    case R.id.zuqiu:
                        //足球
                        mViewPager.setCurrentItem(1,false);//没有滑动动画
                        break;
                    case R.id.lanqiu:
                        //篮球
                        mViewPager.setCurrentItem(2,false);//没有滑动动画
                        break;
                    case R.id.gushi:
                        //股市
                        mViewPager.setCurrentItem(3,false);//没有滑动动画
                        break;

                }
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                BasePager pager = mPager.get(position);
                pager.initData();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mPager.get(0).initData();
    }

    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPager.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPager.get(position);
            View view = pager.mRootView;//当前页面对象的布局
 //           pager.initData();//初始化数据 避免提前缓存
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
