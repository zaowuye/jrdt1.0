package app.yy.cn.jrdt;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import app.yy.cn.jrdt.fragment.ContentFragment;
import app.yy.cn.jrdt.fragment.LeftMenuFragment;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class MainActivity extends SlidingFragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.left_menu);

        SlidingMenu slidingMenu = getSlidingMenu();
        //全屏触摸
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //预留屏幕宽度
        slidingMenu.setBehindOffset(300);

        initFragment();
    }

    /**
     * 初始化fragment
     *
     */
    private void initFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_left_menu,new LeftMenuFragment());//用Fragment替换针布局1参是布局ID，2是要替换的对象
        transaction.replace(R.id.fl_main,new ContentFragment());
        transaction.commit();//提交事务
    }
}
