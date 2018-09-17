package openeyes.dr.openeyes.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by darryrzhong on 2018/6/15.
 * 不能滑动的Viewpager
 */

public class CustomViewPager extends ViewPager {
    //是否可以进行滑动
    private boolean isSlide = false;
    public CustomViewPager(Context context) {
        super(context);
    }

    public void setSlide(boolean slide) {
        isSlide = slide;
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSlide;
    }
}
