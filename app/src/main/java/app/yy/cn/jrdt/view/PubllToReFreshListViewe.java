package app.yy.cn.jrdt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

import app.yy.cn.jrdt.R;

/**
 * Created by Administrator on 2016/11/29 0029.
 */
public class PubllToReFreshListViewe extends ListView implements AbsListView.OnScrollListener{

    private static final int STATE_PULL_TO_REFRESH = 1;
    private static final int STATE_RELEASE_TO_REFRESH = 2;
    private static final int STATE_REFRESHING = 3;

    private int mCurrentState = STATE_PULL_TO_REFRESH;//当前状态

    private View mHeaderView;
    private int mHeaderViewHeight;
    private int startY = -1;
    private TextView tvTitle;
    private TextView tvTime;
    private ImageView ivArrow;
    private RotateAnimation animUp;
    private RotateAnimation animDown;
    private ProgressBar progressBar;
    private View mFooterView;
    private int mFooterViewHeight;

    public PubllToReFreshListViewe(Context context) {
        super(context);
        initHeaderView();
//        initFooterView();
    }

    public PubllToReFreshListViewe(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
//        initFooterView();
    }

    public PubllToReFreshListViewe(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderView();
//        initFooterView();
    }

    /**
     * 初始化头布局
     */
    private void initHeaderView() {
        mHeaderView = View.inflate(getContext(), R.layout.pull_to_refresh_header, null);
        this.addHeaderView(mHeaderView);

        tvTitle = (TextView) mHeaderView.findViewById(R.id.tv_title);
        tvTime = (TextView) mHeaderView.findViewById(R.id.tv_time);
        ivArrow = (ImageView) mHeaderView.findViewById(R.id.iv_arrow);
        progressBar = (ProgressBar) mHeaderView.findViewById(R.id.pb_loading);

        //隐藏头布局
        mHeaderView.measure(0, 0);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);

        //初始化动画
        initAnim();
        setCurrentTime();
    }

    //脚布局,下拉刷新
    private void initFooterView(){
        mFooterView = View.inflate(getContext(), R.layout.pull_to_refresh_footer,null);
        this.addFooterView(mFooterView);

        mFooterView.measure(0,0);
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        mFooterView.setPadding(0,-mFooterViewHeight,0,0);
        this.setOnScrollListener(this);
    }

    private void setCurrentTime(){
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());
        tvTime.setText(time);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                //当用户按住头条新闻的下拉动作是,DOWN会被viewpager消费掉,导致startY没有被赋值,
                // 所以这里要重新获取一下(预防后期扩展)
                if (startY == -1) {
                    startY = (int) ev.getY();
                }

                if (mCurrentState == STATE_REFRESHING){
                    //如果正在刷新,跳出循环
                    break;
                }

                int endY = (int) ev.getY();

                int dy = endY - startY;
                //当前显示的第一个item位置
                int firstVisiblePosition = getFirstVisiblePosition();
                //必须下拉,而且是第一个的时候才有效
                if (dy > 0 && firstVisiblePosition == 0) {
                    //计算当前下拉控件的padding值
                    int padding = dy - mHeaderViewHeight;
                    mHeaderView.setPadding(0, padding, 0, 0);

                    if (padding > 0 && mCurrentState != STATE_RELEASE_TO_REFRESH) {
                        //改成松开刷新的状态
                        mCurrentState = STATE_RELEASE_TO_REFRESH;
                        refreshState();
                    } else if (padding < 0 && mCurrentState != STATE_PULL_TO_REFRESH) {
                        //改成下拉刷新
                        mCurrentState = STATE_PULL_TO_REFRESH;
                        refreshState();
                    }

                    return true;
                }
                break;

            case MotionEvent.ACTION_UP:
                startY = -1;
                if (mCurrentState == STATE_RELEASE_TO_REFRESH) {
                    mCurrentState = STATE_REFRESHING;
                    refreshState();
                    //完整展示头布局(加载框)
                    mHeaderView.setPadding(0, 0, 0, 0);

                    //进行回调
                    /**
                     * 4.进行回调方法
                     */
                if (mListener != null){
                    mListener.onRefresh();
                }

                } else if (mCurrentState == STATE_PULL_TO_REFRESH) {
                    //隐藏头布局
                    mHeaderView.setPadding(0, -mHeaderViewHeight, 0, 0);
                }

                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 初始化动画
     */
    private void initAnim() {
        animUp = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animUp.setDuration(200);
        animUp.setFillAfter(true);

        animDown = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animDown.setDuration(200);
        animDown.setFillAfter(true);
    }

    private void refreshState() {
        switch (mCurrentState) {
            case STATE_PULL_TO_REFRESH:
                tvTitle.setText("下拉刷新");
                ivArrow.startAnimation(animDown);
                progressBar.setVisibility(View.INVISIBLE);
                ivArrow.setVisibility(View.VISIBLE);
                break;
            case STATE_RELEASE_TO_REFRESH:
                tvTitle.setText("松开刷新");
                ivArrow.startAnimation(animUp);
                progressBar.setVisibility(View.INVISIBLE);
                ivArrow.setVisibility(View.VISIBLE);
                break;
            case STATE_REFRESHING:
                tvTitle.setText("正在刷新...");
                //清楚箭头动画
                ivArrow.clearAnimation();
                progressBar.setVisibility(View.VISIBLE);
                ivArrow.setVisibility(View.INVISIBLE);
                break;
            default:
                break;
        }
    }

    /**
     * 刷新结束,收起控件
     */
    public void onRefreshComplete(boolean succes){
        mHeaderView.setPadding(0,-mHeaderViewHeight,0,0);
        mCurrentState = STATE_PULL_TO_REFRESH;
        tvTitle.setText("下拉刷新");
        progressBar.setVisibility(View.INVISIBLE);
        ivArrow.setVisibility(View.VISIBLE);
        if (succes){
            setCurrentTime();
        }
    }

    /**
     * 3.定义成员变量,来监听对象
     */
    private OnRefreshListener mListener;

    /**
     * 2.暴露接口,设置接听
     */
    public void setOnRefreshListener(OnRefreshListener listener){
        mListener = listener;
    }



    /**
     * 1.下拉刷新的回调接口
     */
    public interface OnRefreshListener {
        public void onRefresh();

        //下拉加载更多
//        public void onLoadMore();
    }

    //脚布局下拉刷新,监听事件
    private boolean isLoadMore;
    //滑动状态发生变换
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == SCROLL_STATE_IDLE){//空闲状态
            int lastVisiblePosition = getLastVisiblePosition();
            //显示的是最后一个ITEM,并且没有进行加载更多的时候才会进来
            if (lastVisiblePosition == getCount()-1 && !isLoadMore){
                //到底部
                System.out.println("加载更多");
                //显示加载更多的布局
                mFooterView.setPadding(0,0,0,0);
                //滑动到最后一个item的时候自动出现加载更多
                setSelection(getCount()-1);
                //通知主界面加载下一面
//                if (mListener != null){
//                    mListener.onLoadMore();
//                }

            }
        }
    }


    //滑动过程回调
    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
}
