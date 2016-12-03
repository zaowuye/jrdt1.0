package app.yy.cn.jrdt.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 禁止滑动
 * Created by Administrator on 2016/11/17 0017.
 */
public class NoSccrollViewpager extends ViewPager {
    public NoSccrollViewpager(Context context) {
        super(context);
    }

    public NoSccrollViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //重写方法，触摸不滑动
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
