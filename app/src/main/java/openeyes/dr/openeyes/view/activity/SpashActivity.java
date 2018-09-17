package openeyes.dr.openeyes.view.activity;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import openeyes.dr.openeyes.MainActivity;
import openeyes.dr.openeyes.R;

/**
 * Created by darryrzhong on 2018/6/3.
 * 欢迎界面
 */

public class SpashActivity extends AppCompatActivity {
    @BindView(R.id.iv_bg)
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spash);
        ButterKnife.bind(this);
        initBackground();
        startAnimation();//开启动画效果
    }

    private void initBackground() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK)-1;
        Log.e("!!!!!!!!",day+"");
        switch (day){
            case 0:
                imageView.setBackgroundResource(R.drawable.wallpaper_6);
                break;
            case 1:
                imageView.setBackgroundResource(R.drawable.wallpaper_12);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.wallpaper_7);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.wallpaper_10);
                break;
            case 4:
                imageView.setBackgroundResource(R.drawable.wallpaper_8);
                break;
            case 5:
                imageView.setBackgroundResource(R.drawable.wallpaper_11);
                break;
            case 6:
                imageView.setBackgroundResource(R.drawable.wallpaper_9);
                break;
                default:
                    imageView.setBackgroundResource(R.drawable.wallpaper_9);
        }
    }

    private void startAnimation() {
        final View splashIv = findViewById(R.id.iv_bg);
        ValueAnimator animator = ValueAnimator.ofObject(new FloatEvaluator(),1.0f,1.2f);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                if (value != 1.2f) {
                    splashIv.setScaleX(value);
                    splashIv.setScaleY(value);
                } else {
                    goToActivity();
                }
            }

                private void goToActivity() {
                    Intent intent = new Intent(SpashActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, android.R.anim.fade_out);
                    finish();
                }

        });
        animator.start();
    }

}
