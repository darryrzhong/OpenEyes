package openeyes.dr.openeyes.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.adapter.HistoryRecyclerAdapter;
import openeyes.dr.openeyes.config.MyApplication;
import openeyes.dr.openeyes.database.HistoryDB;
import openeyes.dr.openeyes.customdialog.ActionSheetDialog;
import openeyes.dr.openeyes.customdialog.AlertDialog;
import openeyes.dr.openeyes.model.HistoryDetails;
import openeyes.dr.openeyes.utils.MyUtils;
import openeyes.dr.openeyes.widget.CustomTextView;

/**
 * 观看历史
 * Created by darryrzhong on 2018/7/23.
 */

public class HistoryActivity extends SwipeBackActivity {
    @BindView(R.id.find_back)
    ImageButton back;
    @BindView(R.id.type_title)
    CustomTextView title;
    @BindView(R.id.compile)
    TextView compile;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.clean_hint)
    LinearLayout cleanHint;
    @BindView(R.id.clean_history)
    TextView cleanHistory;
    @BindView(R.id.clean)
    TextView clean;
    @BindView(R.id.historybg)
    ViewGroup viewGroup;
    private List<HistoryDetails> details  = null;
    private HistoryRecyclerAdapter adapter;
    private HistoryDB db;
    private boolean isFirstClean=true;
    private boolean itemCheck=false;//item点击效果tag
    private boolean banItem=false;//禁止item点击tag
    private boolean cleanAll=false;//是否清除全部记录tag
    private int temp;//item选中数量
    private List<HistoryDetails> tempList = new ArrayList<>();
    private SharedPreferences sharedPreferences = MyApplication.sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);//控件初始化
        MyUtils.setBackground(viewGroup,this);//加载主题背景
        initData();
    }


    /**
    * 初始化界面数据
    * */
    private void initData() {
        db = new HistoryDB();
        details= db.loadHistoryByUserId(sharedPreferences.getString("userId","000"));
       if (details==null||details.size()==0){
           tvHint.setVisibility(View.VISIBLE);
           compile.setVisibility(View.GONE);
           cleanHint.setVisibility(View.GONE);
       }else {
           tvHint.setVisibility(View.GONE);
           compile.setVisibility(View.VISIBLE);
           cleanHint.setVisibility(View.VISIBLE);
           Collections.reverse(details);
           adapter = new HistoryRecyclerAdapter(details,this);
           recyclerView.setLayoutManager(new LinearLayoutManager(this));
           recyclerView.setAdapter(adapter);
           recyclerView.setItemAnimator(new DefaultItemAnimator());//添加默认动画
           //设置RecyclerView的item监听事件
           setOnItemClickListener();
       }

    }

    @OnClick(R.id.find_back)
    public void onClick(){
        finish();
    }

    @OnClick(R.id.compile)
    public void onCompileClick(){

        checkHintOrShow();//控制item的check显示或隐藏
    }

    @OnClick(R.id.clean_history)
    public void onCleanAllClick(){

        checkAllHintOrShow();//监听是否选中全部记录
    }

    @OnClick(R.id.clean)
    public void onCleanClick() {
        if (cleanAll) {

            cleanAll=false;
            cleanHistoryAll();

        }else if (!cleanAll&&temp==0){

            notClean();

        }else if (!cleanAll&&temp!=0){

            cleanSelect();
        }
    }

    /**
     * 删除选中的观看记录
     * */
    private void cleanSelect() {
        new AlertDialog(HistoryActivity.this).builder()
                .setMsg("确定将选中记录从列表删除？")
                .setPositiveButton("确认删除", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (HistoryDetails historyDetails:tempList){

                            db.delete(historyDetails);
                        }
                        //android.util.Log.e("`````````", "删除选中的记录");
                       refreshActivity();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    /**
    * 无效的清除提示
    * */
    private void notClean() {
        new AlertDialog(HistoryActivity.this).builder()
                .setMsg("请选择需要删除的记录")
                .setNegativeButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
        //android.util.Log.e("`````````", "请选择需要删除的记录");
    }

    /**
   * 清空全部观看记录
   * */
    private void cleanHistoryAll() {

        new ActionSheetDialog(HistoryActivity.this)
                .builder()
                .setTitle("清空观看记录后，将不可恢复，确定要清空观看记录？")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("清空观看记录", ActionSheetDialog.SheetItemColor.Red
                        , new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                for (HistoryDetails historyDetails : details) {

                                    db.delete(historyDetails);
                                }
                                //android.util.Log.e("`````````", "清空全部记录");
                                refreshActivity();
                            }
                        }, new ActionSheetDialog.OnCancelItemClickListener() {
                            @Override
                            public void onClick() {
                                adapter.setOnCheckListener(new HistoryRecyclerAdapter.OnCheckListener() {
                                    @Override
                                    public boolean onCheckHint(View view) {
                                        view.setBackground(HistoryActivity.this.getResources().getDrawable(R.drawable.uncheck));
                                        return false;
                                    }

                                });
                                clean.setTextColor(HistoryActivity.this.getResources().getColor(R.color.colorGray));
                                isFirstClean=true;
                                itemCheck=false;
                                banItem=false;
                                cleanAll=false;
                                adapter.notifyDataSetChanged();
                                compile.setEnabled(true);
                            }
                        }).show();
    }


    /**
     * 设置RecyclerView的item监听事件(两种类型)
     * */
    private void setOnItemClickListener() {

        adapter.setOnItemClickListener(new HistoryRecyclerAdapter.OnItemClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onItemClick(View itemview, View childview, int position) {
                if (!itemCheck){

                    initVideoDetail( position);//初始化视频详情界面数据并实现跳转
                }else {

                    itemClickType(itemview,childview,position);//item点击控制check选中状态
                }
                android.util.Log.e("66666666",temp+"");
            }
        });
    }



    /**
     * 初始化视频详情界面数据,跳转至视频详情界面
     * */
    private void initVideoDetail(int position) {
        Intent intent = new Intent(HistoryActivity.this, VideoDetailActivity.class);
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


    /**
     * item点击控制check选中状态
     * */
    private void itemClickType(View itemview, View childview, int position) {
        if (banItem){
            return;
        }else {
            boolean isSelected=!itemview.isSelected();
            if (isSelected){
                temp++;
                tempList.add(details.get(position));
                itemview.setSelected(true);
                childview.setBackground(HistoryActivity.this.getResources().getDrawable(R.drawable.check));
                clean.setTextColor(HistoryActivity.this.getResources().getColor(R.color.colorAniBtn));

            }else {
                temp--;
                tempList.remove(details.get(position));
                itemview.setSelected(false);
                childview.setBackground(HistoryActivity.this.getResources().getDrawable(R.drawable.uncheck));
                if (temp==0){
                    clean.setTextColor(HistoryActivity.this.getResources().getColor(R.color.colorGray));
                }
            }
        }
    }



    /**
     * 控制item的check显示或隐藏
     * */
    private void checkHintOrShow() {
        boolean isisSelected=!compile.isSelected();
        if (isisSelected){
            compile.setSelected(true);
            adapter.setOnCheckListener(new HistoryRecyclerAdapter.OnCheckListener() {
                @Override
                public boolean onCheckHint(View view) {
                    return true;
                }

            });
            itemCheck=true;
            temp=0;
            compile.setText("取消");
            compile.setTextColor(HistoryActivity.this.getResources().getColor(R.color.colorAniBtn));
        }else {
            if (temp!=0){//此处不知名bug
                refreshActivity();
            }
            compile.setSelected(false);
            adapter.setOnCheckListener(new HistoryRecyclerAdapter.OnCheckListener() {
                @Override
                public boolean onCheckHint(View view) {
                    return false;
                }

            });
            itemCheck=false;
            temp=0;
            compile.setText("编辑");
            compile.setTextColor(HistoryActivity.this.getResources().getColor(R.color.colorGray));
        }

        if (banItem){
            banItem=false;
        }

        if (cleanAll){
            cleanAll=false;
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * 监听是否选中全部记录
     * */
    private void checkAllHintOrShow() {
        if (isFirstClean){
            adapter.setOnCheckListener(new HistoryRecyclerAdapter.OnCheckListener() {
                @Override
                public boolean onCheckHint(View view) {
                    view.setBackground(HistoryActivity.this.getResources().getDrawable(R.drawable.check));
                    return true;
                }

            });
            clean.setTextColor(this.getResources().getColor(R.color.colorAniBtn));
            isFirstClean=false;
            itemCheck=true;
            banItem=true;
            cleanAll=true;
            adapter.notifyDataSetChanged();
            compile.setEnabled(false);
        }


    }

    public void refreshActivity(){
        startActivity(new Intent(HistoryActivity.this, HistoryActivity.class));
        finish();//关闭自己
        overridePendingTransition(0, 0);//屏蔽掉原有的默认动画效果.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isFirstClean=true;
        itemCheck=false;
    }
}
