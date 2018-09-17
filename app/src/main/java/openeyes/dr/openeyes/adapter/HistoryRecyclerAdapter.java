package openeyes.dr.openeyes.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import io.vov.vitamio.utils.Log;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.HistoryDetails;
import openeyes.dr.openeyes.view.activity.HistoryActivity;

/**
 * HistoryRecyclerAdapter
 * Created by darryrzhong on 2018/7/23.
 */

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.MyViewHolder> implements View.OnClickListener {
   private List<HistoryDetails> mDatas;



  /**
  *  RecyclerView的item监听回调接口
  * */
    public interface OnItemClickListener{
       void onItemClick(View itemview,View childview,int position);
   }

   /**
   * 通过compile监听check的隐藏or显示
   * */

    public interface OnCheckListener{
        boolean onCheckHint(View view);
   }

   private OnItemClickListener onItemClickListener;
   private OnCheckListener onCheckListener;
   private Context mContext;


    public HistoryRecyclerAdapter(List<HistoryDetails> mDatas, Context context) {
        this.mDatas = mDatas;
        this.mContext=context;
    }

    @Override
    public HistoryRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_fragment_recy_item,null);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        itemView.setOnClickListener(this);//为item设置监听
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         holder.bindData(mDatas.get(position));//数据绑定
         holder.itemView.setTag(position);////将position保存在itemView的tag中，一边点击时获取
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
       private SimpleDraweeView simpleDraweeView;
       private TextView title;
       private TextView detail;
       private TextView date;
       public  ImageView check;

        public MyViewHolder(View itemView) {
            super(itemView);
            //初始化view;
            simpleDraweeView=itemView.findViewById(R.id.iv_photo);
            title = itemView.findViewById(R.id.tv_title);
            detail = itemView.findViewById(R.id.tv_detail);
            date=itemView.findViewById(R.id.date);
            check=itemView.findViewById(R.id.ischeck);
        }

        public void bindData(HistoryDetails historyDetails){
            simpleDraweeView.setImageURI(Uri.parse(historyDetails.getPhoto()));
            title.setText(historyDetails.getTitle());
            detail.setText(historyDetails.getDetail());
            date.setText(historyDetails.getDate());
            if (onCheckListener!=null){
                if (onCheckListener.onCheckHint(check)){
                    check.setVisibility(View.VISIBLE);
                }else {
                    check.setVisibility(View.GONE);
                    check.setBackground(mContext.getResources().getDrawable(R.drawable.uncheck));
                }
            }

        }

    }


    @Override
    public void onClick(View view) {
          if (onItemClickListener!=null){
              MyViewHolder myViewHolder = new MyViewHolder(view);
              onItemClickListener.onItemClick(view,myViewHolder.check, (int) view.getTag());//这里使用getTag方法获取positio
          }
    }

    //暴露接口给外面调用者

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
          onItemClickListener=itemClickListener;
    }

    public void setOnCheckListener(OnCheckListener onCheckListener){
        this.onCheckListener=onCheckListener;
    }

}
