package openeyes.dr.openeyes.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.adapter.SearchRecyclerAdapter;
import openeyes.dr.openeyes.database.SearchDB;
import openeyes.dr.openeyes.model.SearchHistory;
import openeyes.dr.openeyes.utils.ToastUtil;
import openeyes.dr.openeyes.widget.SearchEditText;

/**
 * Created by darryrzhong on 2018/9/10.
 */

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_editext)
    SearchEditText searchEditText;
    @BindView(R.id.cancle_main)
    TextView cancleMain;
    @BindView(R.id.history_fl)
    FrameLayout hintLayout;
    @BindView(R.id.delete_history)
    TextView deleteHistory;
    @BindView(R.id.search_history_rv)
    RecyclerView recyclerSearch;
    @BindView(R.id.hot_search_rv)
    RecyclerView recyclerHot;
    private String [] hotKeyWord = {"美食","旅行","生活小技巧","健身","汽车","广告","动画",
            "创意灵感","当下乱码","一条","日食记","视知TV"};
    private List<SearchHistory> searchHistories;
    private List<SearchHistory> hotKeyWords = new ArrayList<>();;
    private SearchRecyclerAdapter adapter;
    private SearchDB db;
    private SearchRecyclerAdapter adapter1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initData();
        setListener();
    }

    private void initData() {
        db = new SearchDB();
        hotKeyWords.clear();
        for (String keyWord:hotKeyWord){
            hotKeyWords.add(new SearchHistory(keyWord));
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayout.VERTICAL);
        recyclerHot.setLayoutManager(manager);
        adapter = new SearchRecyclerAdapter(hotKeyWords);
        recyclerHot.setAdapter(adapter);
        recyclerHot.setItemAnimator(new DefaultItemAnimator());

        searchHistories = db.loadSearchHistoryAll();
        if (searchHistories==null||searchHistories.size()==0){
            hintLayout.setVisibility(View.GONE);
            recyclerSearch.setVisibility(View.GONE);
        }else {
            hintLayout.setVisibility(View.VISIBLE);
            Collections.reverse(searchHistories);
            LinearLayoutManager manager1 = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayout.VERTICAL);
            recyclerSearch.setLayoutManager(manager1);
            adapter1 = new SearchRecyclerAdapter(searchHistories);
            recyclerSearch.setAdapter(adapter1);
            recyclerSearch.setItemAnimator(new DefaultItemAnimator());//添加默认动画
            adapter1.setOnItemClickListener(new SearchRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemview, SearchRecyclerAdapter.MyViewHolder childview, int position) {
                    Intent intent = new Intent(SearchActivity.this,SearchResultActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("keyWord",searchHistories.get(position).getKeyWord());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }

    }

    private void setListener() {
        searchEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode==KeyEvent.KEYCODE_ENTER&&keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                       String keyWord = searchEditText.getText().toString().trim();
                       if ("".equals(keyWord)){
                           ToastUtil.showToast(SearchActivity.this,"请输入搜索内容");
                       }else {
                           SearchHistory searchHistory = new SearchHistory(keyWord);
                           List<SearchHistory> temp = db.loadSearchByKeyWord(keyWord);
                           if (temp==null){
                               db.saveOrUpdate(searchHistory);
                           }else if (temp.size()==0){
                               db.saveOrUpdate(searchHistory);
                           }
                           Intent intent = new Intent(SearchActivity.this,SearchResultActivity.class);
                           Bundle bundle = new Bundle();
                           bundle.putString("keyWord",keyWord);
                           intent.putExtras(bundle);
                           startActivity(intent);
                       }
                }
                return false;
            }
        });

        adapter.setOnItemClickListener(new SearchRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemview, SearchRecyclerAdapter.MyViewHolder childview, int position) {
                Intent intent = new Intent(SearchActivity.this,SearchResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("keyWord",hotKeyWords.get(position).getKeyWord());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    @OnClick({R.id.cancle_main,R.id.delete_history,R.id.search_editext})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.cancle_main:
                finish();
                break;
            case R.id.delete_history:
                try {
                    db.delTable();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                initData();

                break;
            case R.id.search_editext:
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
