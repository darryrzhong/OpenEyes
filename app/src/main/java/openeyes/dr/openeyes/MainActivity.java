package openeyes.dr.openeyes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.StatFs;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import openeyes.dr.openeyes.config.MyApplication;
import openeyes.dr.openeyes.customdialog.ShareDialog;
import openeyes.dr.openeyes.model.Weather;
import openeyes.dr.openeyes.networks.API;
import openeyes.dr.openeyes.utils.MyUtils;
import openeyes.dr.openeyes.utils.NetConnectedUtils;
import openeyes.dr.openeyes.utils.ToastUtil;
import openeyes.dr.openeyes.view.activity.HotVideoActivity;
import openeyes.dr.openeyes.view.activity.SearchActivity;
import openeyes.dr.openeyes.view.activity.StatementActivity;
import openeyes.dr.openeyes.widget.CustomTextView;
import openeyes.dr.openeyes.view.activity.DownloadsActivity;
import openeyes.dr.openeyes.view.activity.HistoryActivity;
import openeyes.dr.openeyes.view.activity.MenuActivity;
import openeyes.dr.openeyes.view.activity.MoreActivity;
import openeyes.dr.openeyes.view.activity.WebViewrActivity;
import openeyes.dr.openeyes.view.fragment.DailyFragment;
import openeyes.dr.openeyes.view.fragment.FindMoreFragment;
import openeyes.dr.openeyes.view.fragment.HotFragment;

/**
 * Created by darryrzhong on 2018/6/7.
 * 主界面
 */

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.main_toolbar_tv_time)
    CustomTextView mainToolbarTvTime;
    @BindView(R.id.main_toolbar_iv_right)
    ImageButton mainToolbarIvRight;
    @BindView(R.id.tv_daily)
    TextView tvDaily;
    @BindView(R.id.tv_find)
    TextView tvFind;
    @BindView(R.id.tv_hot)
    TextView tvHot;
    @BindView(R.id.desc)
    TextView userName;
    @BindView(R.id.temp)
    TextView temp;
    @BindView(R.id.main_menu)
    LinearLayout mainMenu;
    @BindView(R.id.weather_rl)
    RelativeLayout weatherRl;
    @BindView(R.id.profile_image)
    SimpleDraweeView avatar;
    private long mExitTime = 0;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private DailyFragment dailyFragment;
    private HotFragment hotFragment;
    private FindMoreFragment moreFragment;
    public static ViewGroup viewGroup;
    private SharedPreferences sharedPreferences = MyApplication.sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        ButterKnife.bind(this);
        //获取fragment管理器
        fragmentManager = getSupportFragmentManager();
        initView();
        initWeather();

    }

    private void initWeather() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(API.WEATHER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Weather weather = gson.fromJson(response,Weather.class);
                String tmp = weather.getHeWeather6().get(0).getNow().getTmp();
               temp.setText(tmp);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) ;

        queue.add(request);
        queue.start();
    }

    /*
    * 初始化界面
    * */
    private void initView() {
        viewGroup=findViewById(R.id.menu);
        MyUtils.setBackground(viewGroup,this);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);//设置显示左侧程序图标
        getSupportActionBar().setDisplayShowTitleEnabled(false);//设置不显示标题
        mainToolbar.setNavigationIcon(R.drawable.ic_action_menu);
       initUserInfo();//初始化用户数据
        //设置默认第一个菜单按钮为选中状态
        setChoice(1);

    }

    /**
    * 初始化用户数据
    * */
    private void initUserInfo() {
        String userId = sharedPreferences.getString("userId","");
        if ("".equals(userId)){
            avatar.setImageURI(Uri.parse("https://upload-images.jianshu.io/upload_images/5549640-4629d12c7a58b729.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240"));
            userName.setText("登录或注册");
        }else {
            avatar.setImageURI(Uri.parse(sharedPreferences.getString("userIcon","")));
            userName.setText(sharedPreferences.getString("userName",""));
        }

    }


    /*
    * 设置监听事件
    * */
  @OnClick({R.id.tv_collect,R.id.tv_mydown,R.id.tv_fuli,R.id.tv_share,R.id.tv_feedback,R.id.tv_setting,R.id.about,R.id.theme,R.id.weather_rl,R.id.profile_image})
  public void  onClick1(View v) {
      switch (v.getId()) {
          case R.id.weather_rl:
              Intent intent = new Intent(this, WebViewrActivity.class);
              Bundle bundle = new Bundle();
              bundle.putString("title","天气查询");
              bundle.putString("url","http://m.weather.com.cn/mweather/101190201.shtml");
              intent.putExtras(bundle);
              startActivity(intent);
              break;
          case R.id.theme:
              startMenuActivity("主题", 8);
              break;
          case R.id.about:
              startMenuActivity("关于",7);
              break;
          case R.id.tv_feedback:
              startActivity(new Intent(this, StatementActivity.class));
              break;
          case R.id.tv_mydown:
              startActivity(new Intent(this, HistoryActivity.class));
              break;
          case R.id.tv_collect:
              startActivity(new Intent(this,DownloadsActivity.class));
              break;
          case R.id.tv_share:
              showShare();
              break;
          case R.id.profile_image:
              startActivity(new Intent(this, MoreActivity.class));
             break;
          case R.id.tv_fuli:
              startActivity(new Intent(this, HotVideoActivity.class));
              break;
      }
  }


    public void startMenuActivity(String name,int id){
      Intent intent = new Intent(this, MenuActivity.class);
      intent.putExtra("theme",name);
      intent.putExtra("id",id);
      startActivity(intent);
  }

    /*
    * 底部菜单栏切换界面
    *
    * */

    private void  setChoice(int currentItem){
        transaction = fragmentManager.beginTransaction();//开启一个fragment管理事务
        HideFragment(transaction);//隐藏Fragment
        clearChioce();
        switch (currentItem){
            case 1:
                mainToolbarTvTime.setVisibility(View.VISIBLE);
                mainToolbarIvRight.setImageResource(R.drawable.main_toolbar_eye_selector);
                tvDaily.setTextColor(getResources().getColor(R.color.colorBlack));
                if ( dailyFragment== null) {
                    dailyFragment = new DailyFragment();
                    transaction.add(R.id.main_ll_fragment, dailyFragment);

                } else {
                    transaction.show(dailyFragment);
                }

                break;

            case 2://发现更多
                mainToolbarIvRight.setImageResource(R.drawable.ic_action_search);
                mainToolbarIvRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, SearchActivity.class));
                    }
                });
                mainToolbarTvTime.setVisibility(View.GONE);
                tvFind.setTextColor(getResources().getColor(R.color.colorBlack));
                if (moreFragment == null) {
                    moreFragment = new FindMoreFragment();
                    transaction.add(R.id.main_ll_fragment, moreFragment);
                } else {
                    transaction.show(moreFragment);

                }
                break;

            case 3://热门排行
                mainToolbarIvRight.setImageResource(R.drawable.main_toolbar_eye_selector);
                mainToolbarTvTime.setVisibility(View.GONE);
                tvHot.setTextColor(getResources().getColor(R.color.colorBlack));
                if (hotFragment == null) {
                    hotFragment = new HotFragment();
                    transaction.add(R.id.main_ll_fragment, hotFragment);
                } else {
                    transaction.show(hotFragment);
                }
                break;
        }
        transaction.commit();//提交事务
    }

    private void clearChioce() {
        //还原默认选项
        tvDaily.setTextColor(getResources().getColor(R.color.colorGray));
        tvFind.setTextColor(getResources().getColor(R.color.colorGray));
        tvHot.setTextColor(getResources().getColor(R.color.colorGray));
    }


    /*
    * 隐藏所有Fragment,避免混淆
    * */
    private void HideFragment(FragmentTransaction transaction) {
        if (dailyFragment != null) {
            transaction.hide(dailyFragment);
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
        if (hotFragment != null) {
            transaction.hide(hotFragment);
        }


    }

    // 按两次退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtil.showToast(MainActivity.this, "再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.tv_daily, R.id.tv_find, R.id.tv_hot})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_daily:
                setChoice(1);
                break;
            case R.id.tv_find:
                setChoice(2);
                break;
            case R.id.tv_hot:
                setChoice(3);
                break;
        }
    }



    /**
     * 第三方Mob分享
     * */
    private void showShare() {




        final ShareDialog shareDialog = new ShareDialog(this, R.layout.bottomshare_dialog_layout,R.style.ActionSheetDialogStyle, new ShareDialog.shareListener() {
            @Override
            public void shareName(final String fromname, Context context) {

                final OnekeyShare oks = new OnekeyShare();
                //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
                if (fromname!=null){
                    oks.setPlatform(fromname);
                }

                //关闭sso授权
                oks.disableSSOWhenAuthorize();
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle("开眼 Eyepetizer");
                // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
                oks.setTitleUrl("http://www.darryrzhong.site");
                // text是分享文本，所有平台都需要这个字段
                oks.setText("开眼 Eyepetizer");
                //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
                oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://www.darryrzhong.site");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("开眼 Eyepetizer");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite("开眼 Eyepetizer");
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://www.darryrzhong.site");

                //启动分享
                oks.show(context);
            }
        });
        shareDialog.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initUserInfo();
    }


}
