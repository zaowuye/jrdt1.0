package app.yy.cn.jrdt.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import org.w3c.dom.Text;

import java.util.ArrayList;

import app.yy.cn.jrdt.MainActivity;
import app.yy.cn.jrdt.R;
import app.yy.cn.jrdt.base.impl.XinWenPager;
import app.yy.cn.jrdt.bean.XinWenMenu;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class LeftMenuFragment extends BaseFragment {
    @ViewInject(R.id.lv_list)
    private ListView listView;

    private ArrayList<XinWenMenu.XinWenMenuData> mXinWenMenuData;//获取侧边栏数据

    private int mCurrentPos;//选中的item的位置~
    private LeftMenuAdapter mAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
//        listView = (ListView) view.findViewById(R.id.lv_list);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    //设置数据侧边栏
    public void setMenuData(ArrayList<XinWenMenu.XinWenMenuData> data) {
        //当前选中的位置归零,跳转之后重置侧边栏选中状态
        mCurrentPos = 0;
        //更新页面
        mXinWenMenuData = data;

        mAdapter = new LeftMenuAdapter();
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCurrentPos = i;//更新选中的位置
                mAdapter.notifyDataSetChanged();//刷新listview

                //收起侧边栏
                toggle();

                //更换侧边栏选项内容区域
                setCurrentDetailPager(i);
            }


        });

    }

    protected void setCurrentDetailPager(int i) {
        //获取新闻对象
        MainActivity mainUI = (MainActivity) mActivity;

        //获取Fragment对象
        ContentFragment fragment = mainUI.getContentFragment();
        //获取XinWen
        XinWenPager xinWenPager = fragment.getXinWenCenterPager();
        //修改新闻的FrameLayout布局
        xinWenPager.setCurrentDetailPager(i);

    }

    /**
     * 打开关闭侧边栏
     */
    private void toggle() {
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();//如果当前状态是开,调用后就关闭,反之亦然


    }

    class LeftMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mXinWenMenuData.size();
        }

        @Override
        public XinWenMenu.XinWenMenuData getItem(int i) {
            return mXinWenMenuData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = View.inflate(mActivity, R.layout.list_item_left_menu, null);
            TextView tvMenu = (TextView) v.findViewById(R.id.tv_menu);

            XinWenMenu.XinWenMenuData item = getItem(i);
            tvMenu.setText(item.title);

            //判断选中哪一个
            if (i == mCurrentPos) {
                //被选中
                tvMenu.setEnabled(true);//文字变为红色
            } else {
                tvMenu.setEnabled(false);//文字变成白色
            }
            return v;
        }
    }
}
