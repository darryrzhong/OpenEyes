package openeyes.dr.openeyes.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;

/**
 * Created by darryrzhong on 2018/9/7.
 * APP声明activity
 */

public class StatementActivity extends SwipeBackActivity {
   @BindView(R.id.user_agreement)
    TextView userAgreement;
   @BindView(R.id.copyright_notic)
   TextView copyrightNotic;
   @BindView(R.id.function_notic)
   TextView functionNotic;
   @BindView(R.id.versions_update)
   TextView versionsUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statement);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.user_agreement,R.id.copyright_notic,R.id.function_notic,R.id.versions_update})
    public void onClock(View v){
        switch (v.getId()){
            case R.id.user_agreement:
                Intent intent = new Intent(this, WebViewrActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url","http://www.eyepetizer.net/agreement.html");
                bundle.putString("title","用户协议");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.copyright_notic:
                Intent intent1 = new Intent(this, WebViewrActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("url","http://www.eyepetizer.net/right.html");
                bundle1.putString("title","版权声明");
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
            case R.id.function_notic:
                Intent intent2 = new Intent(this, WebViewrActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("url","http://www.eyepetizer.net/right.html");
                bundle2.putString("title","视频声明");
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.versions_update:
                Intent intent3 = new Intent(this, WebViewrActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString("url","http://www.eyepetizer.net/about_us.html");
                bundle3.putString("title","关于我们");
                intent3.putExtras(bundle3);
                startActivity(intent3);
                break;

        }
    }
}
