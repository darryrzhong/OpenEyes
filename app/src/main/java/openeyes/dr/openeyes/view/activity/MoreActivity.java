package openeyes.dr.openeyes.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.config.MyApplication;
import openeyes.dr.openeyes.customdialog.ActionSheetDialog;
import openeyes.dr.openeyes.customdialog.AlertDialog;
import openeyes.dr.openeyes.utils.ToastUtil;


/**
 * Created by darryrzhong on 2018/9/1.
 * 功能设置activity
 */

    public class MoreActivity extends SwipeBackActivity {
   @BindView(R.id.avatar_login)
    SimpleDraweeView loginImage;
   @BindView(R.id.tv_login)
    TextView tvLogin;
   @BindView(R.id.userid)
   TextView userID;
   @BindView(R.id.tv_save)
   TextView attention;
   @BindView(R.id.tv_watch)
   TextView tvAuthor;
   @BindView(R.id.tv_advise)
   TextView advise;
   @BindView(R.id.out_login)
   TextView outLogin;
    private SharedPreferences sharedPreferences = MyApplication.sharedPreferences;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        userId = sharedPreferences.getString("userId","");
        if ("".equals(userId)){
            loginImage.setImageURI(Uri.parse("https://upload-images.jianshu.io/upload_images/5549640-4629d12c7a58b729.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"));
            tvLogin.setText("点击登录即可发表评论 >");
            userID.setVisibility(View.GONE);

        }else {
            loginImage.setImageURI(Uri.parse(sharedPreferences.getString("userIcon","")));
            tvLogin.setText("查看个人主页 >");
            userID.setText(sharedPreferences.getString("userName",""));
            userID.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.avatar_login,R.id.tv_login,R.id.tv_save,R.id.cancle_back,R.id.tv_watch,R.id.tv_advise,R.id.out_login})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.avatar_login:
                if ("".equals(userId)){
                    startActivity(new Intent(this,LoginActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(this,PersonPageActivity.class));
                }
               break;
            case R.id.tv_login:
                if ("".equals(userId)){
                    startActivity(new Intent(this,LoginActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(this,PersonPageActivity.class));
                }
                break;
            case R.id.tv_save:
                startActivity(new Intent(this,MyAttentionActivity.class));
                break;
            case R.id.cancle_back:
                finish();
            break;
            case R.id.tv_watch:
                Intent intent = new Intent(this, WebViewrActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://open.eyepetizer.net/#!/landing");
                bundle.putString("title","我要投稿");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.tv_advise:
                Intent intent1 = new Intent(this, MenuActivity.class);
                intent1.putExtra("theme","意见反馈");
                intent1.putExtra("id",5);
                startActivity(intent1);
                break;
            case R.id.out_login:
                notifyDialog();
                break;
        }

    }

    private void notifyDialog() {
        if ("".equals(sharedPreferences.getString("userId",""))){
            new AlertDialog(this).builder()
                    .setMsg("尚未登录账户,请先登录")
                    .setNegativeButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).show();
        }else {
            new AlertDialog(this).builder()
                    .setTitle("退出登录")
                    .setMsg("是否退出登录")
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.editor.clear().commit();
                    startActivity(new Intent(MoreActivity.this,MoreActivity.class));
                    finish();
                }
            }).show();


        }
    }


}
