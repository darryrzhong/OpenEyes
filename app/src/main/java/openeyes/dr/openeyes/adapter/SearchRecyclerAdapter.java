package openeyes.dr.openeyes.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.SearchHistory;

/**
 * Created by darryrzhong on 2018/9/10.
 */

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.MyViewHolder> implements View.OnClickListener {

    private List<SearchHistory> histories;
    private OnItemClickListener onItemClickListener;

    /**
     *  RecyclerView的item监听回调接口
     * */
    public interface OnItemClickListener{
        void onItemClick(View itemview, SearchRecyclerAdapter.MyViewHolder childview, int position);
    }



    public SearchRecyclerAdapter(List<SearchHistory> histories){
       this.histories= histories;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_search_item,null);
      MyViewHolder myViewHolder = new MyViewHolder(itemView);
      itemView.setOnClickListener(this);
      return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
              holder.bindData(histories.get(position));
              holder.itemView.setTag(position);//将position保存在itemView的tag中，一边点击时获取
      }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener!=null){
            SearchRecyclerAdapter.MyViewHolder myViewHolder = new SearchRecyclerAdapter.MyViewHolder(view);
            onItemClickListener.onItemClick(view,myViewHolder,(int)view.getTag());//这里使用getTag方法获取positio
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

      @BindView(R.id.tv_key)
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindData(SearchHistory searchHistory){
            textView.setText(searchHistory.getKeyWord());
        }

    }

    //暴露接口给外面调用者

    public void setOnItemClickListener(SearchRecyclerAdapter.OnItemClickListener itemClickListener){
        onItemClickListener=itemClickListener;
    }


}
