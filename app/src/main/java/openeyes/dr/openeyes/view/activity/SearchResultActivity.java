package openeyes.dr.openeyes.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.net.URL;
import java.net.URLEncoder;
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
import openeyes.dr.openeyes.adapter.SearchResultAdapter;
import openeyes.dr.openeyes.model.SearchResult;
import openeyes.dr.openeyes.networks.API;

/**
 * Created by darryrzhong on 2018/9/11.
 */

public class SearchResultActivity extends SwipeBackActivity {

    @BindView(R.id.cancle_result)
    ImageView cancle;
    @BindView(R.id.search_keyword)
    TextView searchKeyWord;
    @BindView(R.id.lv_search_result)
    ListView searchResultlv;
    @BindView(R.id.ptr_search_result)
    PtrClassicFrameLayout ptrSearchResult;
    private RequestQueue queue;
    private List<SearchResult.ItemListBean.DataBean> searchResults = new ArrayList<>();
    private boolean isRefresh;
    private boolean isRun;
    private boolean cache;
    private boolean isFirst = true;
    private String nextPageUrl;
    private String url;
    private Gson gson;
    private SearchResultAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        initData();
        initNet(url);
        setListItemClickListener();
    }


    private void initData() {
        url=API.KEYWORD_SEARCH+URLEncoder.encode(getIntent().getStringExtra("keyWord"));
        searchKeyWord.setText("'"+getIntent().getStringExtra("keyWord")+"'"+"相关 ");
        android.util.Log.e("0000000000", url);

    }


    /**
     * 获取网络数据
    * */
    private void initNet(String url) {
        queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                android.util.Log.e("11111111111111",response);
                parseJson(response);//解析json数据
                Log.e("----------->","获取数据成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("------>","获取数据失败");
                isRun = false;
                if (isRefresh) {
                    ptrSearchResult.refreshComplete();
                }
            }
        }) ;

        queue.add(request);
        queue.start();
    }


    /**
    * 解析json数据集
    * */
    private void parseJson(String response) {
        gson = new Gson();
        SearchResult searchResult = gson.fromJson(response,SearchResult.class);
        List<SearchResult.ItemListBean> itemListBeans = searchResult.getItemList();

        isRun = false;
        if (isRefresh){
            searchResults.clear();
            ptrSearchResult.refreshComplete();
            isRefresh=false;
        }

        for (SearchResult.ItemListBean itemListBean:itemListBeans){
            searchResults.add(itemListBean.getData());
        }

       nextPageUrl = searchResult.getNextPageUrl();

        if (nextPageUrl == null) {
            isRun=true;
            android.util.Log.e("000000","空的");
//            findListview.addFooterView(footview, null, false);
//            isLoad = true;
        }else {
            android.util.Log.e("000000",nextPageUrl);

        }

        Notify(searchResults);

    }


    /**
     * 初始化设配器
     * */

    private void Notify(List<SearchResult.ItemListBean.DataBean> searchResults) {
        if (adapter != null){
            adapter.notifyDataSetChanged();
            android.util.Log.e("000000","刷新adapter");

        }else {
            adapter = new SearchResultAdapter(SearchResultActivity.this,searchResults, R.layout.list_search_result_item);
            searchResultlv.setAdapter(adapter);
        }

    }



    private void setListItemClickListener() {

       /* ptrSearchResult.post(new Runnable() {
            @Override
            public void run() {
                ptrSearchResult.autoRefresh();
            }

        });*/

        //下拉刷新
        ptrSearchResult.setPtrHandler(new PtrHandler() {
            //检查是否可以执行下拉刷新，比如列表为空或者列表第一项在最上面时
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                return !canChildScrollUp();
            }

            //需要加载数据时触发
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                isRefresh=true;
                initNet(url);
                android.util.Log.e("-------------","我在刷新加载数据");
            }
        });

        //上滑刷新
        searchResultlv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    if (!isRun) {
                        isRun = true;
                        initNet(nextPageUrl);
                    }
                }

                View c = searchResultlv.getChildAt(0);
                if (c == null) {
                    return;
                }
                int firstVisiblePosition = searchResultlv.getFirstVisiblePosition();
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
            if (searchResultlv instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) searchResultlv;
                return absListView.getChildCount() > 0 &&//当前所加载的最大item数量
                        (absListView.getFirstVisiblePosition() > 0 ||
                                absListView.getChildAt(0).getTop() < absListView.getPaddingTop());

            } else {
                return ViewCompat.canScrollVertically(searchResultlv, -1) || searchResultlv.getScrollY() > 0;
            }

        } else {

            return ViewCompat.canScrollVertically(searchResultlv, -1);

        }

    }

    @OnClick(R.id.cancle_result)
    public void onClick(){
        finish();
    }

}
