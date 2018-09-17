package openeyes.dr.openeyes.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.xutils.x;

import openeyes.dr.openeyes.widget.CustomTextView;

/**
 * Created by darryrzhong on 2018/6/13.
 * ViewHolder工具类
 */

public class ViewHolderUtils {
    //使用SparseArray是因为当键值对映射是    Integer--Object 时 SparseArray的效率要比Map高
    private SparseArray<View> mViews;
    private View mConvertView;
    private int mPosition;
    private Context mContext;
    private ViewHolderUtils(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        mContext=context;
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        //设置tag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolderUtils对象
     *
     * @param context     上下文
     * @param convertView 缓存布局
     * @param parent      父控件
     * @param layoutId    布局id
     * @param position    位置
     * @return
     */
    public static ViewHolderUtils get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolderUtils(context, parent, layoutId, position);
        }else {
            ViewHolderUtils holder = (ViewHolderUtils) convertView.getTag();
            //更新position
            holder.mPosition=position;
            return holder;
        }
    }

    /**
     * 通过控件的viewId获取对应的控件，如果没有则加入views
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 给TextView设置值
     * @param viewId 控件id
     * @param text   值
     * @return
     */
    public ViewHolderUtils setText(int viewId,String text){
        TextView tv =getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 给CustomTextView设置内容
     * */

    public ViewHolderUtils setCustomTextView(int viewId,String text){
        CustomTextView textView = getView(viewId);
        textView.setText(text);
        return  this;
    }

    /**
     * 给TextView设置字体颜色
     * @param viewId
     * @param textColor
     * @return
     */
    public ViewHolderUtils setTextColor(int viewId, int textColor)
    {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * 给TextView设置颜色
     * @param viewId
     * @param textColorRes  颜色id
     * @return
     */
    public ViewHolderUtils setTextColorRes(int viewId, int textColorRes)
    {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    /**
     * 给TextView设置链接
     * @param viewId
     * @return
     */
    public ViewHolderUtils linkify(int viewId)
    {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * 给ImageView设置ImageResource
     * @param viewId  控件id
     * @param resId   资源id
     * @return
     */
    public ViewHolderUtils setImageResource(int viewId,int resId){
        ImageView iv= getView(viewId);
        iv.setImageResource(resId);
        return this;
    }
    /**
     * 给ImageView设置ImageResource使用Frsesco
     * @param uri   资源uri
     * @return
     */
    public ViewHolderUtils setImageResourcewithFresco(int viewId,Uri uri){
        SimpleDraweeView draweeView=getView(viewId);
        draweeView.setImageURI(uri);
        return this;
    }



    /**
     * 给ImageView设置bitmap
     * @param viewId
     * @param bitmap
     * @return
     */
    public ViewHolderUtils setImageBitmap(int viewId,Bitmap bitmap){
        ImageView iv= getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 加载网络图片使用xutils
     * @param viewId
     * @param uri
     * @return
     */
    public ViewHolderUtils setImageURIWithXutils(int viewId,String uri){
        ImageView iv= getView(viewId);
        x.image().bind(iv,uri);
        return this;
    }

    /**
     * 给ImageView设置drawable
     * @param viewId
     * @param drawable
     * @return
     */
    public ViewHolderUtils setImageDrawable(int viewId, Drawable drawable)
    {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 给控件设置背景颜色
     * @param viewId
     * @param color
     * @return
     */
    public ViewHolderUtils setBackgroundColor(int viewId, int color)
    {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 给控件设置背景图片
     * @param viewId
     * @param backgroundRes
     * @return
     */
    public ViewHolderUtils setBackgroundRes(int viewId, int backgroundRes)
    {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * 设置透明度
     * @param viewId
     * @param value
     * @return
     */
    @SuppressLint("NewApi")
    public ViewHolderUtils setAlpha(int viewId, float value)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            getView(viewId).setAlpha(value);
        } else
        {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * 设置控件是否可见
     * @param viewId
     * @param visible
     * @return
     */
    public ViewHolderUtils setVisible(int viewId, boolean visible)
    {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }


    /**
     *设置字体类型
     * @param typeface
     * @param viewIds
     * @return
     */
    public ViewHolderUtils setTypeface(Typeface typeface, int... viewIds)
    {
        for (int viewId : viewIds)
        {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     *点击事件
     */
    public ViewHolderUtils setOnClickListener(int viewId,
                                         View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置进度条
     * @param viewId
     * @param progress
     * @return
     */
    public ViewHolderUtils setProgress(int viewId, int progress)
    {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * 设置进度条
     * @param viewId
     * @param progress
     * @param max
     * @return
     */
    public ViewHolderUtils setProgress(int viewId, int progress, int max)
    {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public ViewHolderUtils setMax(int viewId, int max)
    {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public ViewHolderUtils setRating(int viewId, float rating)
    {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public ViewHolderUtils setRating(int viewId, float rating, int max)
    {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * 设置标签
     * @param viewId
     * @param tag
     * @return
     */
    public ViewHolderUtils setTag(int viewId, Object tag)
    {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 设置标签
     * @param viewId
     * @param key
     * @param tag
     * @return
     */
    public ViewHolderUtils setTag(int viewId, int key, Object tag)
    {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }


    /**
     * 设置是否选中
     * @param viewId
     * @param checked
     * @return
     */
    public ViewHolderUtils setChecked(int viewId, boolean checked)
    {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }



    /**
     * 触摸事件
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolderUtils setOnTouchListener(int viewId,
                                         View.OnTouchListener listener)
    {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * 长按事件
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolderUtils setOnLongClickListener(int viewId,
                                             View.OnLongClickListener listener)
    {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}
