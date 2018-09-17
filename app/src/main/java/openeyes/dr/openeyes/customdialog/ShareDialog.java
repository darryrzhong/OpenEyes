package openeyes.dr.openeyes.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import openeyes.dr.openeyes.R;

/**
 * Created by darryrzhong on 2018/9/3.
 */

public class ShareDialog extends Dialog {

    private LayoutInflater inflater;
    private Context context;
    private shareListener shareListener;
    private int layoutId;

    public interface shareListener{
        void shareName(String fromname,Context context);
    }

    public ShareDialog(@NonNull Context context,int layoutId, int themeResId,shareListener shareListener) {
        super(context, themeResId);
        this.inflater = LayoutInflater.from(context);
        this.context= context;
        this.layoutId =layoutId;
        this.shareListener = shareListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(layoutId,null);
        setContentView(view);
        ButterKnife.bind(this,view);
        init();
    }

    private void init() {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.width = context.getResources().getDisplayMetrics().widthPixels;
        dialogWindow.setAttributes(params);
        dialogWindow.setGravity(Gravity.BOTTOM);
    }

    /**
    * 监听第三方分享
    * */
    @OnClick({R.id.share_wechat,R.id.share_moments,R.id.share_weibo,R.id.share_qq,R.id.share_qzone,R.id.share_more})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.share_wechat:
                shareListener.shareName(Wechat.NAME,context);
                dismiss();
                break;
            case R.id.share_moments:
                shareListener.shareName(WechatMoments.NAME,context);
                dismiss();
                break;
            case R.id.share_weibo:
                shareListener.shareName(SinaWeibo.NAME,context);
                dismiss();
                break;
            case R.id.share_qq:
                shareListener.shareName(QQ.NAME,context);
                dismiss();
                break;
            case R.id.share_qzone:
                shareListener.shareName(QZone.NAME,context);
                dismiss();
                break;
            case R.id.share_more:
                shareListener.shareName(Email.NAME,context);
                dismiss();
                break;

        }
    }

}
