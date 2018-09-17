package openeyes.dr.openeyes.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.DownloadDetails;

/**
 * DownloadRecyclerAdapter
 * Created by darryrzhong on 2018/7/28.
 */

public class DownloadRecyclerAdapter extends RecyclerView.Adapter<DownloadRecyclerAdapter.MyViewHolder> implements View.OnClickListener {

    private List<DownloadDetails> mDatas;
    private DownloadRecyclerAdapter.OnItemClickListener onItemClickListener;
    private DownloadRecyclerAdapter.OnCheckListener onCheckListener;
    private Context mContext;

    public DownloadRecyclerAdapter(List<DownloadDetails> mDatas,Context context) {
        this.mDatas = mDatas;
        this.mContext=context;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener!=null){
            DownloadRecyclerAdapter.MyViewHolder myViewHolder = new DownloadRecyclerAdapter.MyViewHolder(view);
            onItemClickListener.onItemClick(view,myViewHolder.check, (int) view.getTag());//这里使用getTag方法获取positio
        }
    }

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

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_fragment_recy_item,null);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        itemView.setOnClickListener(this);//为item设置监听
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
         holder.bindData(mDatas.get(position));
        holder.itemView.setTag(position);////将position保存在itemView的tag中，一边点击时获取
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView simpleDraweeView;
        private TextView title;
        private TextView detail;
        private TextView date;
        public ImageView check;

        public MyViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.iv_photo);
            title = itemView.findViewById(R.id.tv_title);
            detail = itemView.findViewById(R.id.tv_detail);
            date=itemView.findViewById(R.id.date);
            check=itemView.findViewById(R.id.ischeck);
        }

        public void bindData(DownloadDetails downloadDetails){
            simpleDraweeView.setImageURI(Uri.parse(downloadDetails.getPhoto()));
            title.setText(downloadDetails.getTitle());
            detail.setText(downloadDetails.getDetail());
            date.setText(downloadDetails.getState());
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

    //暴露接口给外面调用者

    public void setOnItemClickListener(DownloadRecyclerAdapter.OnItemClickListener itemClickListener){
        onItemClickListener=itemClickListener;
    }

    public void setOnCheckListener(DownloadRecyclerAdapter.OnCheckListener onCheckListener){
        this.onCheckListener=onCheckListener;
    }
}
