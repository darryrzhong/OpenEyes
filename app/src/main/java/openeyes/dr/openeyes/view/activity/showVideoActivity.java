package openeyes.dr.openeyes.view.activity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.widget.CustomMediaController;

/**
 * 视频播放界面
 * Created by darryrzhong on 2018/6/13.
 */

public class showVideoActivity extends AppCompatActivity implements MediaPlayer.OnInfoListener,MediaPlayer.OnBufferingUpdateListener {

    private Uri uri;
    private ProgressBar pb;
    private TextView downloadRateView, loadRateView;
    private MediaController mMediaController;//媒体控制器
    private VideoView mVideoView;
    private CustomMediaController mCustomMediaController;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window = showVideoActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag,flag);
        //初始化加载文件
        Vitamio.initialize(this);
        setContentView(R.layout.activity_show_video);
        initView();
        initData();
    }

    private void initView() {
        mVideoView = (VideoView) findViewById(R.id.buffer);
        mMediaController = new MediaController(this);
        mCustomMediaController = new CustomMediaController(this,mVideoView,this);
        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);

    }

    /*
    * 初始化数据
    * */
    private void initData() {
        String video = getIntent().getStringExtra("video");//视频播放地址
        String title = getIntent().getStringExtra("title");//视频标题
        mCustomMediaController.setVideoName(title);
        if (getIntent().getBooleanExtra("download",false)){
           mVideoView.setVideoPath(video);
        }else
        {
            uri = Uri.parse(video);
            mVideoView.setVideoURI(uri);//设置视频播放地址
        }

        mVideoView.setMediaController(mCustomMediaController);//设置视频控制器
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mMediaController.show(5000);//控制器显示5秒
        mVideoView.requestFocus();
        mVideoView.setOnInfoListener(this);//设置警告信息监听
        mVideoView.setOnBufferingUpdateListener(this);//设置媒体缓存状态监听
        /*
        * 加载完毕准备播放
        * */
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);//播放速度
            }
        });

    }

    /*
    * 更新流媒体缓存状态。
    * */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }

    /*
    *信息或警告。
    * */
    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START://暂停播放,缓存数据
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    pb.setVisibility(View.VISIBLE);
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);

                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END://缓冲完成开始播放
                mVideoView.start();
                pb.setVisibility(View.GONE);
                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED://缓冲速度
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }
    /*
    * 屏幕切换时设置全屏
    * */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //屏幕切换时，设置全屏
        if (mVideoView != null){
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }
}
