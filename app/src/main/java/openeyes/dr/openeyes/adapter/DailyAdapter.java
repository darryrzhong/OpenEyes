package openeyes.dr.openeyes.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.FindDailyEntity;

/**
 * Created by darryrzhong on 2018/6/11.
 * 每日精选Adapter
 */

public class DailyAdapter extends BaseAdapter {
     private Context context;
     private List<FindDailyEntity.IssueListEntity.ItemListEntity> mitemBeans= new ArrayList<>();
    public static final int VIDEO = 1;
    public static final int TEXT = 2;



    public DailyAdapter(Context context, List<FindDailyEntity.IssueListEntity.ItemListEntity> itemListBeans) {
        this.context = context;
        this.mitemBeans = itemListBeans;

    }


    /*
    * 为item设置不同的数据和布局
    * */
    @Override
    public int getItemViewType(int position) {
        FindDailyEntity.IssueListEntity.ItemListEntity itemListBean = mitemBeans.get(position);
        if ("video".equals(itemListBean.getType())){
            return VIDEO;
        }
        return  TEXT;
    }

    @Override
    public int getCount() {
        return mitemBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mitemBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FindDailyEntity.IssueListEntity.ItemListEntity listBean = mitemBeans.get(position);
        int type = getItemViewType(position);

        String feed = "1";
        String title = "1";
        String category = "1";
        int duration = 0;
        String text = "1";

        mHolder = new ViewHolder();
        mHolder2 = new ViewHolder2();

        switch (type){
            case VIDEO:
                //获取相关信息
                feed=listBean.getData().getCover().getFeed();
                title = listBean.getData().getTitle();
                category =listBean.getData().getCategory();
                category = "#" + category + "  /  ";
                duration = listBean.getData().getDuration();

                int last = duration % 60;
                String stringLast;
                if (last <= 9) {
                    stringLast = "0" + last;
                } else {
                    stringLast = last + "";
                }

                //获取视频时间
                String durationString;
                int minit = duration / 60;
                if (minit < 10) {
                    durationString = "0" + minit;

                } else {
                    durationString = "" + minit;

                }
                String stringTime = durationString + "' " + stringLast + '"';

                //设置布局
                View view = LayoutInflater.from(context).inflate(R.layout.daily_list_view_item,parent,false);
                convertView=view;
                if (convertView==null){

                    mHolder.imageView = (ImageView) convertView.findViewById(R.id.iv);
                    mHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                    mHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                    convertView.setTag(mHolder);
                }else {
                    if (convertView.getTag() instanceof ViewHolder){
                        mHolder = (ViewHolder) convertView.getTag();
                    }else {
                        convertView = view;
                        mHolder.imageView = (ImageView) convertView.findViewById(R.id.iv);
                        mHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                        mHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);

                        convertView.setTag(mHolder);
                    }
                }

                //设置数据
                Uri uri = Uri.parse(feed);
                SimpleDraweeView draweeView = convertView.findViewById(R.id.iv);
                draweeView.setImageURI(uri);
                mHolder.tvTitle.setText(title);
                mHolder.tvTime.setText(category + stringTime);
                return  convertView;

            case TEXT:
                convertView=LayoutInflater.from(context).inflate(R.layout.daily_list_text_item, parent, false);
                TextView textView = (TextView) convertView.findViewById(R.id.tv_home_text);
                text = listBean.getData().getText();
                textView.setText(text);

                String image = mitemBeans.get(position).getData().getImage();

                if (!TextUtils.isEmpty(image)) {
                    textView.setTextSize(20);
                    textView.setText("-Weekend  special-");
                }


                return convertView;
            default:
                return null;
        }

    }

    private ViewHolder mHolder;
    private ViewHolder2 mHolder2;

    static class ViewHolder {
        ImageView imageView;
        TextView tvTitle;
        TextView tvTime;

    }

    static class ViewHolder2 {
        TextView tvTime;

    }
}
