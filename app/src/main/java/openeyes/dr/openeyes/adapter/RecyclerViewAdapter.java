package openeyes.dr.openeyes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.Appraise;

/**
 * Created by darryrzhong on 2018/6/26.
 * RecyclerView适配器
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

   private List<Appraise> mDatas;
   public interface OnItemClickListener{
       void setOnItemClick(View view);
   }

   private OnItemClickListener ItemClickListener;


    public RecyclerViewAdapter(List<Appraise> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item,null);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
       holder.bindData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView contentView;

         public MyViewHolder(View itemView) {
             super(itemView);
             //初始化View
             contentView = itemView.findViewById(R.id.content);
         }

         public void bindData(Appraise appraise){
             contentView.setText(appraise.getAppraise());
             contentView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     ItemClickListener.setOnItemClick(view);
                 }
             });

         }
     }

     public void setOnItemClickListener(OnItemClickListener listener){
          ItemClickListener=listener;
     }
}
