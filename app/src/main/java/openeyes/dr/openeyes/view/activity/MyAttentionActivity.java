package openeyes.dr.openeyes.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.vov.vitamio.utils.Log;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.adapter.AttentionAdapter;
import openeyes.dr.openeyes.model.FindMoreEntity;
import openeyes.dr.openeyes.networks.API;
import openeyes.dr.openeyes.utils.JsonParseUtils;

/**
 * Created by darryrzhong on 2018/9/6.
 */

public class MyAttentionActivity extends SwipeBackActivity {

    @BindView(R.id.lv_attention)
    ListView lvAttention;
    @BindView(R.id.ptr_attention)
    PtrClassicFrameLayout ptr;
    @BindView(R.id.cancle)
    ImageView cancleView;
    private View mview;
    private RequestQueue queue;
    private List<FindMoreEntity> moreEntities = new ArrayList<>();
    private boolean isRefresh;
    private boolean isRun;
    private boolean cache;
    private boolean isFirst = true;
    private AttentionAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myattention);
        ButterKnife.bind(this);
        initView();
        initNet();
        setListItemClickListener();
    }

    private void setListItemClickListener() {

        lvAttention.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("------------","start");

            }
        });

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
                initNet();
                android.util.Log.e("-------------","我在刷新加载数据");
            }
        });


        //上滑刷新
        lvAttention.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    if (!isRun) {
                        isRun = true;
                    }
                }

                View c = lvAttention.getChildAt(0);
                if (c == null) {
                    return;
                }
                int firstVisiblePosition = lvAttention.getFirstVisiblePosition();
                int top = c.getTop();
                int height = -top + firstVisiblePosition * c.getHeight();

                android.util.Log.i("===>" , "===height===>" + height);
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
            if (lvAttention instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) lvAttention;
                return absListView.getChildCount() > 0 &&//当前所加载的最大item数量
                        (absListView.getFirstVisiblePosition() > 0 ||
                                absListView.getChildAt(0).getTop() < absListView.getPaddingTop());

            } else {
                return ViewCompat.canScrollVertically(lvAttention, -1) || lvAttention.getScrollY() > 0;
            }

        } else {

            return ViewCompat.canScrollVertically(lvAttention, -1);

        }

    }



    /**
    * 获取网络数据
    * */



    private void initNet(){

        moreEntities.clear();
        queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(API.FIND_MORE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJson(response);//解析json数据
                Log.e("------>","获取数据成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("------>","获取数据失败");

            }
        });

        queue.add(request);
        queue.start();

    }



    /**
    * 解析json数据
    * */
    private void parseJson(String response) {
        List<FindMoreEntity> moreEntitiess = JsonParseUtils.parseFromJson(response);
        moreEntities.addAll(moreEntitiess);
        setAdapter(moreEntities);
    }

    private void initView() {

    }

    /**
    * 设置适配器
    * */
    public void setAdapter(List<FindMoreEntity> findMoreEntities) {
        adapter = new AttentionAdapter(this,findMoreEntities, R.layout.list_myattention_layout);
        lvAttention.setAdapter(adapter);
        ptr.refreshComplete();
    }

    @OnClick(R.id.cancle)
    public void onClick(){
        finish();
    }
}
