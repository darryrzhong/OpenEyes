package openeyes.dr.openeyes.config;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import openeyes.dr.openeyes.utils.NetConnectedUtils;
import openeyes.dr.openeyes.utils.ToastUtil;

/**
 * Created by darryrzhong on 2018/6/7.
 * 网络状态监听广播
 */

public class NetStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //判断过来的广播，是否是我们想要的
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            if (NetConnectedUtils.isWifiNetConnected(context)) {
                ToastUtil.showToast(context,"正在使用WiFi");
                //Toast.makeText(context, "正在使用WiFi", Toast.LENGTH_SHORT).show();

            } else if (NetConnectedUtils.isPhoneNetConnected(context)) {
                ToastUtil.showToast(context,"正在使用运营商数据");

                //Toast.makeText(context, "正在使用运营商数据", Toast.LENGTH_SHORT).show();
            } else {
                ToastUtil.showToast(context,"无可用网络");

                //Toast.makeText(context, "无可用网络", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
