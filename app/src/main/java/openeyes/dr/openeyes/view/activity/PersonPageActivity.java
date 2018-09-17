package openeyes.dr.openeyes.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.sql.BatchUpdateException;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.adapter.DynamicStateRecyclerAdapter;
import openeyes.dr.openeyes.config.MyApplication;
import openeyes.dr.openeyes.database.HistoryDB;
import openeyes.dr.openeyes.model.HistoryDetails;
import openeyes.dr.openeyes.widget.CustomImageTextView;

/**
 * Created by darryrzhong on 2018/9/5.
 */

public class PersonPageActivity extends SwipeBackActivity {

    @BindView(R.id.avatar_max)
    SimpleDraweeView avatarBackground;
    @BindView(R.id.avatar_min)
    SimpleDraweeView avatarUser;
    @BindView(R.id.date)
    TextView registerDate;
    @BindView(R.id.works)
    CustomImageTextView works;
    @BindView(R.id.attention)
    CustomImageTextView attention;
    @BindView(R.id.fans)
    CustomImageTextView fans;
    @BindView(R.id.center_appbar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.collapsing_toobar)
    CollapsingToolbarLayout collToobar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycle_state)
    RecyclerView recyclerView;
    private SharedPreferences sharedPreferences = MyApplication.sharedPreferences;
    private HistoryDB db;
    private List<HistoryDetails> details  = null;
    private DynamicStateRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_page);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        db = new HistoryDB();
        details= db.loadHistoryByUserId(sharedPreferences.getString("userId","000"));
        if (details==null||details.size()==0){

        }else {
            Collections.reverse(details);
            adapter = new DynamicStateRecyclerAdapter(details,this);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());//添加默认动画
            //设置RecyclerView的item监听事件
            setOnItemClickListener();
        }


    }

    private void setOnItemClickListener() {

        adapter.setOnItemClickListener(new DynamicStateRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemview, DynamicStateRecyclerAdapter.MyViewHolder childview, int position) {
                initVideoDetail( position);//初始化视频详情界面数据并实现跳转
            }
        });
    }

    /**
     * 初始化视频详情界面数据,跳转至视频详情界面
     * */
    private void initVideoDetail(int position) {
        Intent intent = new Intent(PersonPageActivity.this, VideoDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title",details.get(position).getTitle());//获取标题
        bundle.putString("time", details.get(position).getDetail());
        bundle.putString("desc", details.get(position).getDesc());//视频描述
        bundle.putString("blurred", details.get(position).getBlurred());//模糊图片地址
        bundle.putString("feed", details.get(position).getPhoto());//图片地址
        bundle.putString("video", details.get(position).getVideo());//视频播放地址
        bundle.putInt("collect", details.get(position).getCollect());//收藏量
        bundle.putInt("share", details.get(position).getShare());//分享量
        bundle.putInt("reply", details.get(position).getReply());//回复数量
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void initView() {
        avatarBackground.setImageURI(Uri.parse(sharedPreferences.getString("userIcon","")));
        avatarUser.setImageURI(Uri.parse(sharedPreferences.getString("userIcon","")));
        toolbar.setTitle(sharedPreferences.getString("userName",""));
        collToobar.setExpandedTitleColor(getResources().getColor(R.color.colorBlack));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick(R.id.attention)
    public void onClick(){
        startActivity(new Intent(this,MyAttentionActivity.class));
    }


}
