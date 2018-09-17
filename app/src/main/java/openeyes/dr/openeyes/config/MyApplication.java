package openeyes.dr.openeyes.config;

import android.app.Application;
import android.content.SharedPreferences;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;

import org.xutils.x;

/**
 * Created by darryrzhong on 2018/6/7.
 * 程序初始化
 */

public class MyApplication extends Application {
    public static SharedPreferences sharedPreferences ;
    public static SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);//初始化Fresco类
        x.Ext.init(this);//初始化xUtils3工具类
        MobSDK.init(this);//初始化Mobsdk
        sharedPreferences = getSharedPreferences("userTag",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}
