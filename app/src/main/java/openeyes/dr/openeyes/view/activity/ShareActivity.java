package openeyes.dr.openeyes.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.customdialog.ShareDialog;
import openeyes.dr.openeyes.widget.CustomImageTextView;

/**
 * Created by darryrzhong on 2018/9/2.
 */

public class ShareActivity extends SwipeBackActivity {

    @BindView(R.id.simdv_cover)
    SimpleDraweeView simDvCover;
    @BindView(R.id.simdv_vague)
    SimpleDraweeView simDvVague;
    @BindView(R.id.share_title)
    TextView shareVideoTitle;
    @BindView(R.id.share_wechat)
    CustomImageTextView shareWechat;
    @BindView(R.id.share_moments)
    CustomImageTextView shareMoments;
    @BindView(R.id.share_weibo)
    CustomImageTextView shareWeibo;
    @BindView(R.id.share_qq)
    CustomImageTextView shareQQ;
    @BindView(R.id.share_qzone)
    CustomImageTextView shareQzone;
    @BindView(R.id.share_more)
    CustomImageTextView shareMore;
    @BindView(R.id.share_cancel)
    TextView cancel;
    private String coverPath;
    private String vaguePath;
    private String videoTitle;
    private String videoPath;
    private String videoDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        coverPath = getIntent().getStringExtra("cover");
        vaguePath = getIntent().getStringExtra("vague");
        videoTitle = getIntent().getStringExtra("title");
        videoPath = getIntent().getStringExtra("video");
        videoDesc = getIntent().getStringExtra("desc");
    }

    private void initView() {
       shareVideoTitle.setText("『"+videoTitle+"』");
       simDvCover.setImageURI(Uri.parse(coverPath));
       simDvVague.setImageURI(Uri.parse(vaguePath));

    }

    @OnClick(R.id.share_cancel)
    public void onClickCancel(){
        finish();
    }


    @OnClick({R.id.share_wechat,R.id.share_moments,R.id.share_weibo,R.id.share_qq,R.id.share_qzone,R.id.share_more})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_wechat:
                sharePlatform(Wechat.NAME);
                break;
            case R.id.share_moments:
                sharePlatform(WechatMoments.NAME);
                break;
            case R.id.share_weibo:
                sharePlatform(SinaWeibo.NAME);
                break;
            case R.id.share_qq:
                sharePlatform(QQ.NAME);
                break;
            case R.id.share_qzone:
                sharePlatform(QZone.NAME);
                break;
            case R.id.share_more:
                sharePlatform(Email.NAME);
                break;
        }
    }

    private void sharePlatform(String fromname) {
        final OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (fromname!=null){
            oks.setPlatform(fromname);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(videoTitle);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(videoPath);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(videoDesc);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(coverPath);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://darryrzhong.site");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("Eyepetizer");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://darryrzhong.site");

        //启动分享
        oks.show(this);
    }

}
