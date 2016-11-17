package app.yy.cn.jrdt.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public abstract class BaseFragment extends Fragment {
    public Activity mActivity;
    //创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前Fragment所在的Activity
        mActivity = getActivity();
    }

    //初始化Frament布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = initView();
        return view;
    }

    //frament依赖的activity的onCreate方法执行结束


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData();
    }

    //初始化布局/数据,子类继承
    public abstract View initView();
    public abstract void initData();


}
