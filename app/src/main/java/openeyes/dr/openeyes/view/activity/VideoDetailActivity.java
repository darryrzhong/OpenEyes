package openeyes.dr.openeyes.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.io.File;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.config.MyApplication;
import openeyes.dr.openeyes.database.DownloadDB;
import openeyes.dr.openeyes.database.HistoryDB;
import openeyes.dr.openeyes.customdialog.AlertDialog;
import openeyes.dr.openeyes.model.DownloadDetails;
import openeyes.dr.openeyes.model.HistoryDetails;
import openeyes.dr.openeyes.utils.NetConnectedUtils;
import openeyes.dr.openeyes.utils.ToastUtil;
import openeyes.dr.openeyes.utils.VideoDownloadUtil;

/**
 * Created by darryrzhong on 2018/6/12.
 * 视频详情界面
 */

public class VideoDetailActivity extends SwipeBackActivity {

    @BindView(R.id.video_toolbar_iv_right)
    ImageButton videoToolbarIvRight;
    @BindView(R.id.find_back)
    ImageButton finhdback;
    @BindView(R.id.video_toolbar)
    Toolbar videoToolbar;
    @BindView(R.id.video_detail_iv)
    SimpleDraweeView videoDetailIv;
    @BindView(R.id.video_paly)
    ImageView videoPaly;
    @BindView(R.id.video_detail_ivmo)
    SimpleDraweeView videoDetailIvmo;
    @BindView(R.id.video_detail_title)
    TextView videoDetailTitle;
    @BindView(R.id.video_detail_time)
    TextView videoDetailTime;
    @BindView(R.id.video_detail_desc)
    TextView videoDetailDesc;
    @BindView(R.id.video_detail_iv_fav)
    ImageView videoDetailIvFav;
    @BindView(R.id.video_detail_tv_fav)
    TextView videoDetailTvFav;
    @BindView(R.id.video_detail_iv_share)
    ImageView videoDetailIvShare;
    @BindView(R.id.video_detail_tv_share)
    TextView videoDetailTvShare;
    @BindView(R.id.video_detail_iv_reply)
    ImageView videoDetailIvReply;
    @BindView(R.id.video_detail_tv_reply)
    TextView videoDetailTvReply;
    @BindView(R.id.video_detail_iv_down)
    ImageView videoDetailIvDown;
    @BindView(R.id.video_detail_tv_down)
    TextView videoDetailTvDown;
    @BindView(R.id.video_detail_ll_share)
    LinearLayout vedioLlShare;
    @BindView(R.id.video_detail_ll_down)
    LinearLayout viedioLlDown;
    private String video;
    private String title;
    private HistoryDB hdb;
    private DownloadDB db;
    private String feed;
    private String time;
    private String desc;
    private String blurred;
    private int collect;
    private int share;
    private int reply;
    private String sdpath;//手机内置sdcard存储路径
    private List<DownloadDetails> templist;
    private SharedPreferences sharedPreferences = MyApplication.sharedPreferences;
    private SharedPreferences.Editor editor = MyApplication.editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        initData();
        hdb = new HistoryDB();
        db = new DownloadDB();
    }

    private void initData() {
        //获取数据
        //背景图片
        feed = getIntent().getStringExtra("feed");
        title = getIntent().getStringExtra("title");
        //时间
        time = getIntent().getStringExtra("time");
        //视频详情
        desc = getIntent().getStringExtra("desc");
        //模糊图片
        blurred = getIntent().getStringExtra("blurred");
        video = getIntent().getStringExtra("video");//视频播放地址
        //收藏量
        collect = getIntent().getIntExtra("collect", 0);
        //分享量
        share = getIntent().getIntExtra("share", 0);
        //回复量
        reply = getIntent().getIntExtra("reply", 0);
        //初始化界面数据
        videoDetailIv.setImageURI(Uri.parse(feed));
        videoDetailTitle.setText(title);
        videoDetailTime.setText(time);
        videoDetailDesc.setText(desc);
        videoDetailIvmo.setImageURI(Uri.parse(blurred));
        videoDetailTvFav.setText(String.valueOf(collect));
        videoDetailTvShare.setText(String.valueOf(share));
        videoDetailTvReply.setText(String.valueOf(reply));
    }

    @OnClick({R.id.video_toolbar_iv_right,R.id.find_back})
    public void onClick(){
        finish();
    }

    @OnClick({R.id.video_paly, R.id.video_detail_iv_fav, R.id.video_detail_ll_share, R.id.video_detail_iv_reply, R.id.video_detail_ll_down})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.video_paly://播放视频
               if (NetConnectedUtils.isNetConnected(this)){
                   Intent intent=new Intent(this,showVideoActivity.class);
                        Bundle bundle = new Bundle();
                   bundle.putString("video",video);
                   bundle.putString("title",title);
                   intent.putExtras(bundle);
                   startActivity(intent);
                   String userId = sharedPreferences.getString("userId","000");
                   HistoryDetails historyDetails = new HistoryDetails(feed,title,time,getDate(),desc,blurred,video,collect,share,reply,userId);
                   List<HistoryDetails> temp = hdb.loadHistoryByTitle(title);//同步默认账户观看记录
                   List<HistoryDetails> temp2 = hdb.loadHistoryByUserId(userId);//同步userid观看记录
                   if (temp==null||temp2==null){
                       hdb.saveOrUpdate(historyDetails);
                   }else if (temp.size()==0||temp2.size()==0){
                       hdb.saveOrUpdate(historyDetails);
                   }


                   //Log.e("------------","我在播放视频");

               }else {

                   new AlertDialog(VideoDetailActivity.this).builder()
                           .setMsg("网络异常,请连接网络后重试")
                           .setNegativeButton("确定", new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {

                               }
                           }).show();
               }

                break;
            case R.id.video_detail_iv_fav://收藏
                break;
            case R.id.video_detail_ll_share://分享
                shareVedio();
                break;
            case R.id.video_detail_iv_reply://评论
                break;
            case R.id.video_detail_ll_down://下载
                downloadVideo();
                break;
        }
    }

    /**
    * 分享视频界面
    * */
    private void shareVedio() {
        Intent intent = new Intent(VideoDetailActivity.this,ShareActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("cover",feed);//封面
        bundle.putString("vague",blurred);//模糊图片
        bundle.putString("title",title);//视频title
        bundle.putString("video",video);
        bundle.putString("desc",desc);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    /*
   * 下载视频至本地
   * */
    private void downloadVideo() {
        templist = db.loadDownByTitle(title);
       if (templist !=null&&templist.size()!=0&& "已缓存".equals(templist.get(0).getState())){
           ToastUtil.showToast(VideoDetailActivity.this,"该视频已缓存");
            return;
       }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            ToastUtil.showToast(VideoDetailActivity.this,"已加入缓存队列");
            setOutdownload();
        }
    }

    /**
    * 准备下载
    * */
    private void setOutdownload() {
        sdpath = Environment.getExternalStorageDirectory().getPath()+ File.separator+"eyepetizers";//I自定义视频保存路径
        String destFileName = title+".mp4";
        VideoDownloadUtil.getInstance().download(video, sdpath, destFileName, new VideoDownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                String path = file+"";
                DownloadDetails downloadDetails = new DownloadDetails(feed,title,time,path,"已缓存");
                if (templist==null){
                    db.saveOrUpdate(downloadDetails);
                }else if (templist.size()==0){
                    db.saveOrUpdate(downloadDetails);
                }else if ("未缓存".equals(templist.get(0).getState())){
                    db.delete(templist.get(0));
                    db.saveOrUpdate(downloadDetails);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(VideoDetailActivity.this,"下载成功");
                    }
                });

            }

            @Override
            public void onDownloading(int progress) {

            }

            @Override
            public void onDownloadFailed(Exception e) {
                ToastUtil.showToast(VideoDetailActivity.this,"下载失败");
            }
        });
    }


    public String getDate(){
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return month+"月"+day+"日";
    }


}
