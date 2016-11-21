package app.yy.cn.jrdt;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";

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
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_left_menu,new LeftMenuFragment(),TAG_LEFT_MENU);//用Fragment替换针布局1参是布局ID，2是要替换的对象
        transaction.replace(R.id.fl_main,new ContentFragment(),TAG_CONTENT);
        transaction.commit();//提交事务

//        Fragment fragment =
//        fm.findFragmentByTag(TAG_LEFT_MENU);//根据标记找到对应的Fragment
    }

    /**
     * 获取侧边栏Fragment
     * @return
     */
    public LeftMenuFragment getLeftMenuFragment(){
        FragmentManager fm = getSupportFragmentManager();
        LeftMenuFragment fragment = (LeftMenuFragment) fm.findFragmentByTag(TAG_LEFT_MENU);//根据标记找到对应的Fragment
        return fragment;
    }

    /**
     * 获取主页Fragment
     * @return
     */
    public ContentFragment getContentFragment(){
        FragmentManager fm = getSupportFragmentManager();
        ContentFragment fragment = (ContentFragment) fm.findFragmentByTag(TAG_CONTENT);//根据标记找到对应的Fragment
        return fragment;
    }
}
