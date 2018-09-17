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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.FindMoreDetails;
import openeyes.dr.openeyes.networks.API;
import openeyes.dr.openeyes.utils.AdapterUtils;
import openeyes.dr.openeyes.utils.ViewHolderUtils;
import openeyes.dr.openeyes.view.activity.VideoDetailActivity;

/**
 * Created by darryrzhong on 2018/6/14.
 * 发现更多子Fragment
 */

public class FindChildFragment extends Fragment {

    private Unbinder unbinder;
    private View footview;
    @BindView(R.id.find_listview)
    ListView findListview;
    private List<FindMoreDetails.ItemListBeanX.DataBeanX.ItemListBean> itemListEntities = new ArrayList<FindMoreDetails.ItemListBeanX.DataBeanX.ItemListBean>();
    private static final String[] RANK = new String[]{"date", "shareCount"};
    private boolean isLoad=false;//判断是否在加载数据
    private String nextPageUrl;//下一页数据请求地址
    private List<FindMoreDetails.ItemListBeanX.DataBeanX.ItemListBean> adapter;

    public FindChildFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_find, container, false);
        unbinder = ButterKnife.bind(this,view);
        initData();
        initview();
        setListener();
        return view;
    }

    private void initData() {
        String name = getArguments().getString("name");//分类名称
        int position = getArguments().getInt("position");
        Log.e("====rank", position + "-----" + RANK[position]);
        String rank = RANK[position];
        String encode = URLEncoder.encode(name);//必须将中文进行URL编码才能加到接口中
        String url = String.format(API.FIND_DETAIL);//请求地址
        downloadData(url);

    }

    /*
    * 获取数据
    * */
    private void downloadData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJson(response);
                Log.e("--------->","获取数据成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("--------->","获取数据失败");

            }
        });
        requestQueue.add(request);
        requestQueue.start();
    }

    private void parseJson(String response) {
        FindMoreDetails details = new Gson().fromJson(response, FindMoreDetails.class);

        for (FindMoreDetails.ItemListBeanX itemListBeanXES:details.getItemList()){
           itemListEntities.addAll(itemListBeanXES.getData().getItemList());
       }

        isLoad = false;//数据下载完之后设置为false
        nextPageUrl = (String) details.getNextPageUrl();
        //如果下一页数据的请求地址为null，则加载底部布局
        if (nextPageUrl == null) {
            findListview.addFooterView(footview, null, false);
            isLoad = true;
        }
        //设置适配器
        setAdapter(itemListEntities);
    }


    private void initview() {
        footview = LayoutInflater.from(getContext()).inflate(R.layout.list_footer, null);
    }

    private void setListener() {
        //listview的滑动监听
        findListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    if (!isLoad) {
                        downloadData(nextPageUrl);

                    }
                }
            }
        });

        findListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), VideoDetailActivity.class);
                 Bundle bundle = new Bundle();
                Log.e("--->", position + "");
                FindMoreDetails.ItemListBeanX.DataBeanX.ItemListBean.DataBean data =  itemListEntities.get(position).getData();
                bundle.putString("title", data.getTitle());
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
                bundle.putString("time", "#" + data.getCategory() + " / " + minute + "'" + second + '"');
                bundle.putString("desc", data.getDescription());//视频描述
                bundle.putString("blurred", data.getCover().getBlurred());//模糊图片地址
                bundle.putString("feed", data.getCover().getFeed());//图片地址
                bundle.putString("video", data.getPlayUrl());//视频播放地址
                bundle.putInt("collect", data.getConsumption().getCollectionCount());//收藏量
                bundle.putInt("share", data.getConsumption().getShareCount());//分享量
                bundle.putInt("reply", data.getConsumption().getReplyCount());//回复数量
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

    public void setAdapter(List<FindMoreDetails.ItemListBeanX.DataBeanX.ItemListBean> itemListEntities) {
        AdapterUtils<FindMoreDetails.ItemListBeanX.DataBeanX.ItemListBean> adapter = new AdapterUtils<FindMoreDetails.ItemListBeanX.DataBeanX.ItemListBean>(getContext(),itemListEntities,R.layout.list_home_vedio_item) {
            @Override
            public void convert(ViewHolderUtils viewHolderUtils, FindMoreDetails.ItemListBeanX.DataBeanX.ItemListBean itemListBean) {
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
            }
        };

        findListview.setAdapter(adapter);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }
}
