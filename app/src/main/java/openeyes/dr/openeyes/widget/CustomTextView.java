package openeyes.dr.openeyes.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by darryrzhong on 2018/6/3.
 * 自定义TextView
 */

@SuppressLint("AppCompatCustomView")
public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        init(context);
    }


    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /*
    * 定制字体
    * */
    private void init(Context context) {
        AssetManager assets = context.getAssets();//获取资源文件
        Typeface font = Typeface.createFromAsset(assets,"fonts/Lobster-1.4.otf");
        setTypeface(font);
    }

}
