package openeyes.dr.openeyes.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.config.MyApplication;
import openeyes.dr.openeyes.model.HistoryDetails;

/**
 * Created by darryrzhong on 2018/9/5.
 * user动态数据设配器
 */

public class DynamicStateRecyclerAdapter extends RecyclerView.Adapter<DynamicStateRecyclerAdapter.MyViewHolder> implements View.OnClickListener {

    private List<HistoryDetails> mDatas;

    private DynamicStateRecyclerAdapter.OnItemClickListener onItemClickListener;
    private Context mContext;



    /**
     *  RecyclerView的item监听回调接口
     * */
    public interface OnItemClickListener{
        void onItemClick(View itemview, MyViewHolder childview, int position);
    }



    public DynamicStateRecyclerAdapter (List<HistoryDetails> mDatas, Context context){
        this.mDatas = mDatas;
        this.mContext=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_dynamic_state_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        itemView.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          holder.bindData(mDatas.get(position));//数据绑定
           holder.itemView.setTag(position);//将position保存在itemView的tag中，一边点击时获取
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
         @BindView(R.id.iv_photo)
        SimpleDraweeView simpleDraweeView;
         @BindView(R.id.tv_title)
        TextView tvTitle;
         @BindView(R.id.tv_detail)
         TextView tvDetail;
         @BindView(R.id.date)
         TextView date;
         @BindView(R.id.avatar_user)
         SimpleDraweeView avatarUser;
         @BindView(R.id.username)
         TextView userName;

         public MyViewHolder(View itemView) {
             super(itemView);
             ButterKnife.bind(this,itemView);
         }

        public void bindData(HistoryDetails historyDetails){
            simpleDraweeView.setImageURI(Uri.parse(historyDetails.getPhoto()));
            tvTitle.setText(historyDetails.getTitle());
            tvDetail.setText(historyDetails.getDetail());
            date.setText(historyDetails.getDate());
            avatarUser.setImageURI(Uri.parse(MyApplication.sharedPreferences.getString("userIcon","")));
            userName.setText(MyApplication.sharedPreferences.getString("userName",""));
        }
     }

    @Override
    public void onClick(View view) {
        if (onItemClickListener!=null){
            MyViewHolder myViewHolder = new MyViewHolder(view);
            onItemClickListener.onItemClick(view,myViewHolder,(int)view.getTag());//这里使用getTag方法获取positio
        }
    }

    //暴露接口给外面调用者

    public void setOnItemClickListener(DynamicStateRecyclerAdapter.OnItemClickListener itemClickListener){
        onItemClickListener=itemClickListener;
    }
}
