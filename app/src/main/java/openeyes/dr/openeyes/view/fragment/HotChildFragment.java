package openeyes.dr.openeyes.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.HotTopEntity;
import openeyes.dr.openeyes.networks.API;
import openeyes.dr.openeyes.utils.AdapterUtils;
import openeyes.dr.openeyes.utils.ViewHolderUtils;
import openeyes.dr.openeyes.view.activity.VideoDetailActivity;


/**
 * Created by darryrzhong on 2018/6/13.
 * 热门排行子fragment
 */

public class HotChildFragment extends Fragment {

    private List<HotTopEntity.ItemListBean> itemListBeans = new ArrayList<>();
    private Unbinder unbinder;
    @BindView(R.id.hot_listview)
    ListView hotListview;
    //排行 周排行 月排行 总排行
    private static final String[] STRATEGY = new String[]{
            "weekly", "monthly", "historical"
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot__child_fragment, container, false);
        unbinder = ButterKnife.bind(this,view);
        initView();
        initData();
        setListener();
        return view;
    }

    private void initView() {
        //添加底部布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_footer, null);
       hotListview.addFooterView(view,null,false);
    }

    /*
    * 获取排行数据
    * */
    private void initData() {
       int position = FragmentPagerItem.getPosition(getArguments());
        Log.e("---->position",position+"");
        String stretary = STRATEGY[position];
        //获取到排行请求地址
        String url = String.format(API.HOT_STRATEGY,stretary);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pareJson(response);
                Log.e("----------","获取数据成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("----------","获取数据失败");
            }
        });
        requestQueue.add(request);
        requestQueue.start();
    }

    private void pareJson(String response) {
            HotTopEntity hotTopEntity = new Gson().fromJson(response,HotTopEntity.class);
            itemListBeans.addAll(hotTopEntity.getItemList());
            //设置适配器
           setAdapter(itemListBeans);
    }

    /*
    * 设置适配器
    * */
    private void setAdapter(final List<HotTopEntity.ItemListBean> itemListBeans) {
        final int[] i = {0};//设置数据的序号
        hotListview.setAdapter(new AdapterUtils<HotTopEntity.ItemListBean>(getContext(), itemListBeans, R.layout.list_hot_item) {
            @Override
            public void convert(ViewHolderUtils viewHolderUtils, HotTopEntity.ItemListBean itemListBean) {
                viewHolderUtils.setText(R.id.tv_title, itemListBean.getData().getTitle());
                //获取时间
                int duration = itemListBean.getData().getDuration();
                int mm = duration / 60;//分
                int ss = duration % 60;//秒
                String second = "";//秒
                String minute = "";//分
                if (ss < 10) {
                    second = "0" + String.valueOf(ss);
                } else {
                    second = String.valueOf(ss);
                }
                if (mm < 10) {
                    minute = "0" + String.valueOf(mm);
                } else {
                    minute = String.valueOf(mm);//分钟
                }
                viewHolderUtils.setText(R.id.tv_time, "#" + itemListBean.getData().getCategory() + " / " + minute + "'" + second + '"');
                viewHolderUtils.setImageResourcewithFresco(R.id.iv, Uri.parse(itemListBean.getData().getCover().getFeed()));

                if (i[0]<itemListBeans.size()) {
                    viewHolderUtils.setText(R.id.hot_tv_textnumber, ++i[0] +".");
                }
            }
        });
    }

    private void setListener() {
           hotListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Intent intent=new Intent(getContext(), VideoDetailActivity.class);
                   Bundle bundle = new Bundle();
                   Log.i("--->",position+"");
                   HotTopEntity.ItemListBean.DataBean data = itemListBeans.get(position).getData();
                   bundle.putString("title",data.getTitle());
                   //获取到时间
                   int duration = data.getDuration();
                   int mm = duration / 60;//分
                   int ss = duration % 60;//秒
                   String second = "";//秒
                   String minute = "";//分
                   if (ss < 10) {
                       second = "0" + String.valueOf(ss);
                   } else {
                       second = String.valueOf(ss);
                   }
                   if (mm < 10) {
                       minute = "0" + String.valueOf(mm);
                   } else {
                       minute = String.valueOf(mm);//分钟
                   }
                   bundle.putString("time", "#" + data.getCategory()+ " / " + minute + "'" + second + '"');
                   bundle.putString("desc",data.getDescription());//视频描述
                   bundle.putString("blurred",data.getCover().getBlurred());//模糊图片地址
                   bundle.putString("feed",data.getCover().getFeed());//图片地址
                   bundle.putString("video",data.getPlayUrl());//视频播放地址
                   bundle.putInt("collect",data.getConsumption().getCollectionCount());//收藏量
                   bundle.putInt("share",data.getConsumption().getShareCount());//分享量
                   bundle.putInt("reply",data.getConsumption().getReplyCount());//回复数量
                   intent.putExtras(bundle);
                   startActivity(intent);
               }
           });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
