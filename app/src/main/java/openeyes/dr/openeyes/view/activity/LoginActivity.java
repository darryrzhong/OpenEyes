package openeyes.dr.openeyes.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import dmax.dialog.SpotsDialog;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.config.MyApplication;
import openeyes.dr.openeyes.database.UserDB;
import openeyes.dr.openeyes.model.UserInfoEntity;
import openeyes.dr.openeyes.networks.API;
import openeyes.dr.openeyes.utils.MyUtils;
import openeyes.dr.openeyes.utils.ToastUtil;

/**
 * Created by darryrzhong on 2018/9/1.
 * 用户登录activity
 */

public class LoginActivity extends SwipeBackActivity {

    @BindView(R.id.ll_background)
    ViewGroup viewGroup;
    @BindView(R.id.cancel)
    ImageView imageCancel;
    @BindView(R.id.login_account)
    MaterialEditText userAccount;
    @BindView(R.id.login_password)
    MaterialEditText userPass;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.weibo)
    ImageView weibo;
    @BindView(R.id.wechat)
    ImageView weChat;
    @BindView(R.id.qq)
    ImageView qq;
    @BindView(R.id.agreement)
    TextView useragreement;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.hint_code)
    LinearLayout hintCode;
    @BindView(R.id.login_code)
    MaterialEditText loginCode;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.share_login)
    LinearLayout shareLogin;
    private String userId;
    private String userName;
    private String userIcon;
    private String gender;
    private Boolean isSuccessful=false;
    private SharedPreferences.Editor editor;
    private EventHandler eventHandler;
    private boolean isFinish=true;
    //总时长
    private long millisinfuture=60000;
    //间隔时长
    private long countdowninterva=1000;
    private CountDownTimer timer;
    private boolean isregister=false;//标识是注册还是登录操作
    private boolean isPassCode=false;
    private String phone;
    private AlertDialog dialog;
    private UserDB userDB;
    private String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        MyUtils.setBackground(viewGroup,this);//加载主题
        initView();
        initData();
    }

    private void initView() {

    }

    private void initData() {
        editor = MyApplication.editor;
        userDB = new UserDB();

        timer = new CountDownTimer(millisinfuture,countdowninterva) {
            @Override
            public void onTick(long millisUntilFinished) {
                isFinish=false;
                getCode.setText(Math.round(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                isFinish=true;
                getCode.setText("重发验证码");
            }
        };



        // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
        eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                new Handler(Looper.getMainLooper(), new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                        int event = message.arg1;
                        int result = message.arg2;
                        Object data = message.obj;
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                            if (result == SMSSDK.RESULT_COMPLETE) {
                                // TODO 处理成功得到验证码的结果
                                Log.e("//////////","成功发送验证码");
                                // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                            } else {
                                // TODO 处理错误的结果
                                ((Throwable) data).printStackTrace();
                            }
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            if (result == SMSSDK.RESULT_COMPLETE) {
                                // TODO 处理验证码验证通过的结果
                                isPassCode=true;
                                codePassResult(isPassCode);

                                Log.e("//////////","验证码已通过");

                            } else {
                                // TODO 处理错误的结果
                                isPassCode=false;
                                codePassResult(isPassCode);

                                Log.e("//////////","验证码错误");
                                ((Throwable) data).printStackTrace();
                            }
                        }
                        // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                        return false;
                    }
                }).sendMessage(msg);
            }
        };

        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);

    }

   /**
   * 处理验证码返回结果
   * */
    private void codePassResult(boolean isPassCode) {
        if (isPassCode){
            UserInfoEntity userInfo = new UserInfoEntity(phone,passWord,phone,API.USER_ICON);
            List<UserInfoEntity> temp = userDB.loadUserInfoByUserId(phone);
            if (temp==null){
                userDB.saveOrUpdate(userInfo);
            }else if (temp.size()==0){
                userDB.saveOrUpdate(userInfo);
            }

            editor.putString("userId",phone);
            editor.putString("passWord",passWord);
            editor.putString("userIcon", API.USER_ICON);
            editor.putString("userName",phone);
            editor.apply();
            dialog.dismiss();
            ToastUtil.showToast(LoginActivity.this,"注册成功,登录中");
            startActivity(new Intent(LoginActivity.this,MoreActivity.class));
            finish();
        }else {
            dialog.dismiss();
            ToastUtil.showToast(LoginActivity.this,"验证码错误");
        }
    }

    /*
    * 监听事件
    * */
    @OnClick({R.id.cancel,R.id.login_btn,R.id.get_code, R.id.weibo,R.id.wechat,R.id.qq,R.id.agreement,R.id.register})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.cancel:
                finish();
                break;
            case R.id.qq:
                QQLogin();//第三方qq登录
                break;
            case R.id.register:
                registerOrLoginBtn();//切换登录与注册界面布局
                break;
            case R.id.get_code:
                getSmsCode();//获取短信验证码
                break;
            case R.id.login_btn:
                submitSmsCode(isregister);//提交验证码
                break;
        }
    }

    /**
    * 切换登录与注册界面布局
    * */
    private void registerOrLoginBtn() {
        boolean isSelected = !register.isSelected();
        if (isSelected){
            register.setSelected(true);
            hintCode.setVisibility(View.VISIBLE);
            loginBtn.setText("注册");
            isregister=true;
            register.setText("用户登录");
            shareLogin.setVisibility(View.GONE);
        }else {
            register.setSelected(false);
            hintCode.setVisibility(View.GONE);
            loginBtn.setText("登录");
            isregister=false;
            register.setText("用户注册");
            shareLogin.setVisibility(View.VISIBLE);
        }
    }


    /**
    * 提交验证码
    * */

    private void submitSmsCode(boolean isregister){
        if (isregister){//注册
            passWord = userPass.getText().toString().trim();
            String code = loginCode.getText().toString().trim();
            if (!"".equals(passWord)&&!"".equals(phone)&&!"".equals(code)){
                // 提交验证码，其中的code表示验证码，如“1357”
                Log.e("//////////",code);
                SMSSDK.submitVerificationCode("86", phone, code);

                dialog = new SpotsDialog.Builder()
                        .setContext(this)
                        .setMessage("安全验证中")
                        .setCancelable(true)
                        .setTheme(R.style.Custom)
                        .build();
                dialog.show();
            }else {
                ToastUtil.showToast(this,"请填写完整注册信息");
            }

        }else {
            phone = userAccount.getText().toString().trim();
            passWord = userPass.getText().toString().trim();
            if (!"".equals(phone)&&!"".equals(passWord)){
                dialog = new SpotsDialog.Builder()
                        .setContext(this)
                        .setMessage("安全验证中")
                        .setCancelable(true)
                        .setTheme(R.style.Custom)
                        .build();
                dialog.show();
                  List<UserInfoEntity> temp = userDB.loadUserInfoByUserId(phone);
                  if (temp==null||temp.size()==0){
                      dialog.dismiss();
                      ToastUtil.showToast(this,"该账户不存在");
                  }else {
                      if (temp.get(0).getPassWord().equals(passWord)){
                            dialog.dismiss();
                            editor.putString("userId",phone);
                            editor.putString("userName",phone);
                            editor.putString("userIcon",API.USER_ICON);
                            editor.apply();
                          ToastUtil.showToast(this,"正在登陆");
                          startActivity(new Intent(LoginActivity.this,MoreActivity.class));
                          finish();
                      }else {
                          dialog.dismiss();
                          ToastUtil.showToast(this,"密码错误");

                      }
                  }
            }else {
                ToastUtil.showToast(LoginActivity.this,"请输入正确的账户密码");
            }
        }
    }




    /**
    * 获取验证码
    * */
    private void getSmsCode() {

        if (isFinish){
            timer.start();
        }

       // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
        phone = userAccount.getText().toString().trim();
        Log.e("//////////", phone);
        SMSSDK.getVerificationCode("86", phone);

    }

    /**
    * 第三方qq登录
    * */
    private void QQLogin() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.SSOSetting(false);
        qq.showUser(null);
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int action, HashMap<String, Object> hashMap) {
                  if (action==Platform.ACTION_USER_INFOR){
                      PlatformDb plaDB = platform.getDb();//获取平台数据DB
                      String accessToken = plaDB.getToken(); //获取授权token
                      //获取用户Id
                      userId = plaDB.getUserId();
                      //获取用户名
                      userName = plaDB.getUserName();
                      //获取头像地址
                      userIcon = plaDB.getUserIcon();
                      //获取用户性别
                      gender = "";
                      if ("f".equals(plaDB.getUserGender())){
                          gender ="女";
                      }else if ("m".equals(plaDB.getUserGender())){
                          gender ="男";
                      }
                      Log.e("platform","我在获取数据----"+ userId +"--"+ userName +"--"+ gender +"---"+ userIcon);

                      editor.putString("userId", userId);//标识当前用户id
                      editor.putString("userName",userName);
                      editor.putString("userIcon",userIcon);
                      editor.putString("gender",gender);
                      editor.apply();
                      Log.e("platform","我在存入数据----"+ userId +"--"+ userName +"--"+ gender +"---"+ userIcon);

                      startActivity(new Intent(LoginActivity.this,MoreActivity.class));
                      finish();

                  }
            }

            @Override
            public void onError(Platform platform, int action, Throwable throwable) {
                       throwable.printStackTrace();
            }

            @Override
            public void onCancel(Platform platform, int action) {

            }
        });


    }

    // 使用完EventHandler需注销，否则可能出现内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
    }
}
