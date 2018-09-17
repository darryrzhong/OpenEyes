package openeyes.dr.openeyes.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.adapter.DownloadRecyclerAdapter;
import openeyes.dr.openeyes.database.DownloadDB;
import openeyes.dr.openeyes.customdialog.ActionSheetDialog;
import openeyes.dr.openeyes.customdialog.AlertDialog;
import openeyes.dr.openeyes.model.DownloadDetails;
import openeyes.dr.openeyes.utils.MyUtils;
import openeyes.dr.openeyes.utils.SdcardOrFileSizeUtil;
import openeyes.dr.openeyes.widget.CustomTextView;

/**
 * 离线缓存
 * Created by darryrzhong on 2018/7/28.
 */

public class DownloadsActivity extends SwipeBackActivity {

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
    @BindView(R.id.clean_downloads)
    TextView cleanDownloads;
    @BindView(R.id.clean)
    TextView clean;
    @BindView(R.id.download_bg)
    ViewGroup viewGroup;
    @BindView(R.id.sd_freespace)
    TextView sdFreeSpace;
    private List<DownloadDetails> details  = null;
    private DownloadDB db;
    private DownloadRecyclerAdapter adapter;
    private boolean isFirstClean=true;
    private boolean itemCheck=false;//item点击效果tag
    private boolean banItem=false;//禁止item点击tag
    private boolean cleanAll=false;//是否清除全部记录tag
    private int temp;//item选中数量
    private List<DownloadDetails> tempList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);
        ButterKnife.bind(this);
        MyUtils.setBackground(viewGroup,this);
        initData();
    }

    private void initData() {
       db = new DownloadDB();
       details = db.loadDownAll();
        if (details==null||details.size()==0){
            tvHint.setVisibility(View.VISIBLE);
            compile.setVisibility(View.GONE);
            cleanHint.setVisibility(View.GONE);
            sdFreeSpace.setVisibility(View.GONE);
        }else {
            tvHint.setVisibility(View.GONE);
            compile.setVisibility(View.VISIBLE);
            cleanHint.setVisibility(View.VISIBLE);
            sdFreeSpace.setText(getDownLoadSpaceState());
            Collections.reverse(details);
            adapter = new DownloadRecyclerAdapter(details,this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());//添加默认动画
            //设置RecyclerView的item监听事件
            setOnItemClickListener();
        }
    }


    /**
    * 设置RecyclerView的item监听事件(两种类型)
    * */
    private void setOnItemClickListener() {
       adapter.setOnItemClickListener(new DownloadRecyclerAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(View itemview, View childview, int position) {
              if (!itemCheck){
                  startVideo(position);
              }else {
                  itemClickType(itemview,childview,position);//item点击控制check选中状态

              }

           }
       });
    }

    private void startVideo(int position) {
        Intent intent = new Intent(DownloadsActivity.this, showVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("video",details.get(position).getVideo());
        bundle.putString("title",details.get(position).getTitle());
        bundle.putBoolean("download",true);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @OnClick(R.id.find_back)
    public void onClick(){
        finish();
    }

    @OnClick(R.id.compile)
    public void onCompileClick(){

        checkHintOrShow();//控制item的check显示或隐藏
    }

    @OnClick(R.id.clean_downloads)
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

        new ActionSheetDialog(DownloadsActivity.this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("从列表中删除", ActionSheetDialog.SheetItemColor.Red
                        , new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                for (DownloadDetails downloadDetails:tempList){

                                    db.delete(downloadDetails);
                                }

                                refreshActivity();
                            }
                        }, new ActionSheetDialog.OnCancelItemClickListener() {
                            @Override
                            public void onClick() {
                                refreshActivity();
                            }
                        })
                .addSheetItem("删除下载文件", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        for (DownloadDetails downloadDetails:tempList){
                            File file = new File(downloadDetails.getVideo());
                            if (file.exists()){
                                file.delete();
                                db.updateDownloadState( downloadDetails);
                            }else {

                            }
                        }
                        refreshActivity();

                    }
                },null)
                .show();
    }

    /**
     * 无效的清除提示
     * */
    private void notClean() {
        new AlertDialog(DownloadsActivity.this).builder()
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

        new ActionSheetDialog(DownloadsActivity.this)
                .builder()
                //.setTitle("清空缓存记录后，将不可恢复，确定要清空观看记录？")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("从列表中删除", ActionSheetDialog.SheetItemColor.Red
                        , new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                for (DownloadDetails downloadDetails: details) {

                                    db.delete(downloadDetails);
                                }
                                //android.util.Log.e("`````````", "清空全部记录");
                                refreshActivity();
                            }
                        }, new ActionSheetDialog.OnCancelItemClickListener() {
                            @Override
                            public void onClick() {
                                adapter.setOnCheckListener(new DownloadRecyclerAdapter.OnCheckListener() {
                                    @Override
                                    public boolean onCheckHint(View view) {
                                        view.setBackground(DownloadsActivity.this.getResources().getDrawable(R.drawable.uncheck));
                                        return false;
                                    }

                                });
                                clean.setTextColor(DownloadsActivity.this.getResources().getColor(R.color.colorGray));
                                isFirstClean=true;
                                itemCheck=false;
                                banItem=false;
                                cleanAll=false;
                                adapter.notifyDataSetChanged();
                                compile.setEnabled(true);
                            }
                        })
                .addSheetItem("删除下载文件", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        for (DownloadDetails downloadDetails:details){
                            File file = new File(downloadDetails.getVideo());
                            if (file.exists()){
                                file.delete();
                                db.updateDownloadState(downloadDetails);
                            }else {

                            }
                        }
                        refreshActivity();
                    }
                },null)
                .show();
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
                childview.setBackground(DownloadsActivity.this.getResources().getDrawable(R.drawable.check));
                clean.setTextColor(DownloadsActivity.this.getResources().getColor(R.color.colorAniBtn));

            }else {
                temp--;
                tempList.remove(details.get(position));
                itemview.setSelected(false);
                childview.setBackground(DownloadsActivity.this.getResources().getDrawable(R.drawable.uncheck));
                if (temp==0){
                    clean.setTextColor(DownloadsActivity.this.getResources().getColor(R.color.colorGray));
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
            adapter.setOnCheckListener(new DownloadRecyclerAdapter.OnCheckListener() {
                @Override
                public boolean onCheckHint(View view) {
                    return true;
                }

            });
            itemCheck=true;
            temp=0;
            compile.setText("取消");
            compile.setTextColor(DownloadsActivity.this.getResources().getColor(R.color.colorAniBtn));
        }else {
            if (temp!=0){//此处不知名bug
                refreshActivity();
            }
            compile.setSelected(false);
            adapter.setOnCheckListener(new DownloadRecyclerAdapter.OnCheckListener() {
                @Override
                public boolean onCheckHint(View view) {
                    return false;
                }

            });
            itemCheck=false;
            temp=0;
            compile.setText("编辑");
            compile.setTextColor(DownloadsActivity.this.getResources().getColor(R.color.colorGray));
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
            adapter.setOnCheckListener(new DownloadRecyclerAdapter.OnCheckListener() {
                @Override
                public boolean onCheckHint(View view) {
                    view.setBackground(DownloadsActivity.this.getResources().getDrawable(R.drawable.check));
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

    /**
    * 刷新activity
    * */
     public void refreshActivity(){
         startActivity(new Intent(DownloadsActivity.this, DownloadsActivity.class));
         finish();//关闭自己
         overridePendingTransition(0, 0);//屏蔽掉原有的默认动画效果.
     }



    /*
    * 获取缓存视频占用空间状态
    **/
    public String getDownLoadSpaceState(){
        String state="";
           String filesPath =  Environment.getExternalStorageDirectory().getPath()+ File.separator+"eyepetizers";//I视频保存路径
        //判断下载文件的目录是否存在
        File dir = new File(filesPath);
        if (!dir.exists()){
          }
        try {
            state = "已缓存"+ SdcardOrFileSizeUtil.formetFileSize(SdcardOrFileSizeUtil.getFileSizes(dir))+" | "+"剩余"+SdcardOrFileSizeUtil.getSdcardtFreeSpace(this)+"可用";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isFirstClean=true;
        itemCheck=false;
    }
}
