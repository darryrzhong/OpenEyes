package openeyes.dr.openeyes.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.vov.vitamio.activity.InitActivity;

/**
 * Created by darryrzhong on 2018/9/2.
 * 自定义imageview加TextView组合控件
 */

public class CustomImageTextView extends LinearLayout {
    private ImageView mImageView = null;
    private TextView mTextView = null;
    private int imageId;
    private int textId, textColorId;


    public CustomImageTextView(Context context) {
        this(context,null);
    }

    public CustomImageTextView(Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0);
    }

    public CustomImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(LinearLayout.VERTICAL);//处置排列
        this.setGravity(Gravity.CENTER);//设置居中
        if(mImageView==null){
            mImageView = new ImageView(context);
        }
        if (mTextView==null){
            mTextView = new TextView(context);
        }
        if (attrs==null){
            return;
        }
        int count = attrs.getAttributeCount();
        for (int i=0;i<count;i++){
            String arrtName = attrs.getAttributeName(i);//获取属性名称
            //根据属性获取资源ID
            switch (arrtName){
                case "image":
                    imageId = attrs.getAttributeResourceValue(i, 0);
                    break;
                case "text":
                    textId = attrs.getAttributeResourceValue(i, 0);
                    break;
                case "textColor":
                    textColorId = attrs.getAttributeResourceValue(i, 0);
                    break;

            }
        }
        init();

    }

    /**
    * 初始化状态
    * */
    private void init() {
        this.setText(textId);
        mTextView.setTextSize(10f);
        mTextView.setGravity(Gravity.CENTER);//字体居中
       this.setTextColor(textColorId);
        this.setImgResource(imageId);
        addView(mImageView);
        addView(mTextView);//
    }

    /**
    * 设置显示文字
     * @param text
     * */
    public void setText(int text) {
        this.mTextView.setText(text);

    }

    /**
     * 设置显示图片
     * @param resourceID
     * */
    private void setImgResource(int resourceID) {
        if (resourceID == 0) {
            this.mImageView.setImageResource(0);
        } else {
            this.mImageView.setImageResource(resourceID);
        }
    }




    /**
     * 设置字体颜色
     * @param color
     * */
    private void setTextColor(int color) {
        if (color == 0) {
            this.mTextView.setTextColor(Color.BLACK);
        } else {
            this.mTextView.setTextColor(getResources().getColor(color));
        }
    }
}
