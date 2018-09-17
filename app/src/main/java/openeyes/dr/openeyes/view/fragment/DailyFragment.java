package openeyes.dr.openeyes.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.adapter.DailyAdapter;
import openeyes.dr.openeyes.model.FindDailyEntity;
import openeyes.dr.openeyes.networks.API;
import openeyes.dr.openeyes.view.activity.VideoDetailActivity;

/**
 * Created by darryrzhong on 2018/6/7.
 * 每日精选
 */

public class DailyFragment extends Fragment {

    private View mview;
    private Unbinder unbinder;
    @BindView(R.id.lv_home)
    ListView lvHome;
    @BindView(R.id.ptr)
    PtrClassicFrameLayout ptr;
    private Context mContext;
    private RequestQueue queue;
    private List<FindDailyEntity.IssueListEntity.ItemListEntity> listAll = new ArrayList<>();
    private DailyAdapter adapter;

    private boolean isRefresh;
    private boolean isRun;
    private boolean cache;
    private boolean isFirst = true;
    private String nextUrl;

    public DailyFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_daily,container,false);
        unbinder=ButterKnife.bind(this,mview);//绑定activity
        initView();
        initNet();
        setLvAdapter();
        downLoad(API.DAILY);
        setListener();
        return mview;
    }


    private void initView() {

    }


    private void initNet() {
        mContext = getContext();
        queue = Volley.newRequestQueue(mContext);
    }

    private void setLvAdapter() {
        adapter = new DailyAdapter(mContext,listAll);
        lvHome.setAdapter(adapter);

    }

    //获取数据
    private void  downLoad(String url) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {

            private Gson mGson;
            @Override
            public void onResponse(String response) {
                mGson = new Gson();
                FindDailyEntity findDailyEntity = mGson.fromJson(response,FindDailyEntity.class);
                List<FindDailyEntity.IssueListEntity> issueList = findDailyEntity.getIssueList();
                FindDailyEntity.IssueListEntity issueListEntity = issueList.get(0);
                List<FindDailyEntity.IssueListEntity.ItemListEntity> itemList = issueListEntity.getItemList();
                FindDailyEntity.IssueListEntity issueListEntity2 = issueList.get(0);
                List<FindDailyEntity.IssueListEntity.ItemListEntity> itemList1 = issueListEntity2.getItemList();
                Log.e("===>" , "===获取数据完成===>");

                isRun = false;
               if (isRefresh){
                   listAll.removeAll(listAll);
                   ptr.refreshComplete();
                   isRefresh=false;
               }

               listAll.addAll(itemList);
               listAll.addAll(itemList1);

               nextUrl=findDailyEntity.getNextPageUrl();

               Notify();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("===>" , "===获取数据失败===>");
                isRun = false;
                if (isRefresh) {
                    ptr.refreshComplete();
                }
            }
        });

        queue.add(request);
        queue.start();

    }

    private void Notify() {
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    private void setListener() {
        ptr.post(new Runnable() {
            @Override
            public void run() {
                ptr.autoRefresh();
            }
        });

        //下拉刷新
        ptr.setPtrHandler(new PtrHandler() {
            //检查是否可以执行下拉刷新，比如列表为空或者列表第一项在最上面时
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                return !canChildScrollUp();
            }

            //需要加载数据时触发
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
             isRefresh=true;
             downLoad(API.DAILY);
             Log.e("-------------","我在刷新加载数据");
            }
        });


        lvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                Log.i("--->", position + "");
                FindDailyEntity.IssueListEntity.ItemListEntity.DataEntity data = listAll.get(position).getData();
                if (!"video".equals(listAll.get(position).getType())){
                    return;
                }
                //获取标题
                bundle.putString("title",data.getTitle());
                //获取视频时间
                int duration = data.getDuration();
                int mm =duration/60;
                int ss = duration%60;
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

        //上滑刷新
        lvHome.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    if (!isRun) {
                        isRun = true;
                        downLoad(nextUrl);
                    }
                }

                View c = lvHome.getChildAt(0);
                if (c == null) {
                    return;
                }
                int firstVisiblePosition = lvHome.getFirstVisiblePosition();
                int top = c.getTop();
                int height = -top + firstVisiblePosition * c.getHeight();

                Log.i("===>" , "===height===>" + height);
            }
        });
    }


    /*
判断能否向上滚动刷新
* @param AbsListView的item数量由屏幕能显示的最大数量决定
* @param ViewCompat类主要是用来提供兼容性
* */
    private boolean canChildScrollUp() {

        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (lvHome instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) lvHome;
                return absListView.getChildCount() > 0 &&//当前所加载的最大item数量
                        (absListView.getFirstVisiblePosition() > 0 ||
                                absListView.getChildAt(0).getTop() < absListView.getPaddingTop());

            } else {
                return ViewCompat.canScrollVertically(lvHome, -1) || lvHome.getScrollY() > 0;
            }

        } else {

            return ViewCompat.canScrollVertically(lvHome, -1);

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();//解除绑定
    }
}
