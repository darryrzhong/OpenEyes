package openeyes.dr.openeyes.view.activity;




import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.utils.Log;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.utils.MyUtils;
import openeyes.dr.openeyes.view.fragment.AboutFragment;
import openeyes.dr.openeyes.view.fragment.AppraiseFragment;
import openeyes.dr.openeyes.view.fragment.DailyFragment;
import openeyes.dr.openeyes.view.fragment.ThemFragment;

/**
 * Created by darryrzhong on 2018/6/19.
 */

public class MenuActivity extends SwipeBackActivity {

    @BindView(R.id.find_back)
    ImageButton findBack;
    @BindView(R.id.type_title)
    TextView typeTitle;
    @BindView(R.id.find_right)
    ImageButton findRight;
    @BindView(R.id.type_toolbar)
    Toolbar typeToolbar;
    @BindView(R.id.menubg)
    ViewGroup viewGroup;
    private String name;
    private int type;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ThemFragment themFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        MyUtils.setBackground(viewGroup,this);
        fragmentManager = getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        initView();
        initData();

    }

    private void initView() {
     type=getIntent().getIntExtra("id",0);
     typeTitle.setText(getIntent().getStringExtra("theme"));
        switch (type){
            case 8:
               transaction.replace(R.id.content,new ThemFragment()).commit();
                break;
            case 7:
                transaction.replace(R.id.content,new AboutFragment()).commit();
                break;
            case 5:
                transaction.replace(R.id.content,new AppraiseFragment()).commit();
                break;
            default:
                    break;
        }
    }

    private void initData() {

    }

    @OnClick({R.id.find_back, R.id.find_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_back://返回上一级
                finish();
                break;
            case R.id.find_right:

                break;
        }
    }




}


