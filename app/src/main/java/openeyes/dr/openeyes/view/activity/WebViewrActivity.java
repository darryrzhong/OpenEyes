package openeyes.dr.openeyes.view.activity;

import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;

/**
 * Created by darryrzhong on 2018/7/29.
 */

public class WebViewrActivity extends SwipeBackActivity {

    @BindView(R.id.weather_webview)
    WebView webView;
    @BindView(R.id.find_back)
    ImageButton back;
    @BindView(R.id.type_title)
    TextView titleType;
    private WebSettings webSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置JavaScript可用
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDatabaseEnabled(true);
        String dir = this.getApplicationContext().getDir("database", this.MODE_PRIVATE).getPath();
        webSettings.setGeolocationDatabasePath(dir);
        //处理各种通知和请求事件,如果不使用,将使用内置浏览器访问网页
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
               callback.invoke(origin,true,false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });//JavaScript对话框
    }

    private void initData() {
        String title = getIntent().getStringExtra("title");
        String url =getIntent().getStringExtra("url");
        titleType.setText(title);
        webView.loadUrl(url);

    }



    @OnClick(R.id.find_back)
    public void onClickBack(){
        finish();
    }
}
