package openeyes.dr.openeyes.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by darryrzhong on 2018/6/13.
 * 通用的Adapter工具类
 */

public abstract class AdapterUtils<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    private int layoutId;

    public AdapterUtils(Context context, List<T> mDatas, int layoutId) {
        mInflater=LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
        this.layoutId=layoutId;
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderUtils viewHolder = ViewHolderUtils.get(mContext, convertView, parent,layoutId, position);
        //把viewHolder和当前Item对应的Bean对象给传出去
        convert(viewHolder, (T) getItem(position));
        return viewHolder.getConvertView();

    }

    /**
     * 对外公布了一个convert方法，把viewHolder和当前Item对于的Bean对象给传出去
     * @param viewHolderUtils
     * @param t
     */
    public abstract void  convert(ViewHolderUtils viewHolderUtils,T t);
}
