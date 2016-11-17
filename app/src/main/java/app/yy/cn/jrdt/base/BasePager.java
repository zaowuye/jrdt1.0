package app.yy.cn.jrdt.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import app.yy.cn.jrdt.R;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class BasePager {
    public Activity mActivity;


    public TextView tvTitle;
    public ImageButton btnMenu;
    public FrameLayout flContent;

    public View mRootView;//当前页面对象

    public BasePager(Activity activity){
        mActivity = activity;
        mRootView = initView();
    }

    //初始化布局
    public View initView(){
        View view = View.inflate(mActivity, R.layout.base_page, null);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        btnMenu = (ImageButton) view.findViewById(R.id.btn_menu);
        flContent = (FrameLayout) view.findViewById(R.id.fl_content);

        return view;
    }

    //初始化数据
    public void initData(){

    }
}
