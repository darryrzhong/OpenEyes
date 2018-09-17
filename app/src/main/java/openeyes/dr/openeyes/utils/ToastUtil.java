package openeyes.dr.openeyes.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import openeyes.dr.openeyes.R;

/**
 * Created by darryrzhong on 2016/3/1.
 */
public class ToastUtil {
    /**
     * 土司显示
     *
     * @param context 上下文
     * @param content 土司内容
     */
    public static void showToast(Context context, String content) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.toast_layout,null);
        TextView tvToast = view.findViewById(R.id.tv_toast);
        tvToast.setText(content);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();

    }
}
