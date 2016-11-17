package app.yy.cn.jrdt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import app.yy.cn.jrdt.tool.PrefUtils;

public class WelcomeActivity extends AppCompatActivity {

    private RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        rlRoot = (RelativeLayout) findViewById(R.id.rlRoot);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);


        AnimationSet set = new AnimationSet(true);
        set.addAnimation(alphaAnimation);
        set.addAnimation(scaleAnimation);

        rlRoot.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束进行跳转
                //如果是第一次进入,跳入新手引导
                //否则进入主页面

//                boolean one = PrefUtils.getBoolean(WelcomeActivity.this, "one", true);
//                Intent intent;
//                if(one){
//                    //跳新手引导
//                    intent  = new Intent(getApplicationContext(),GuideActivity.class);
//
//                }else {
//                    //跳主页面
//                    intent = new Intent(getApplicationContext(),MainActivity.class);
//                }
//                startActivity(intent);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                //结束当前页面
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

}
