package openeyes.dr.openeyes.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.view.fragment.FindChildFragment;

/**
 * Created by darryrzhong on 2018/6/14.
 * 发现更多Activity
 */

public class FinddetailActivity extends SwipeBackActivity {

    //标题
    private static final String[] TITLE = new String[]{"按时间排序", "分享排行"};
    private static  final String[] RANK=new String[]{"date","shareCount"};
    @BindView(R.id.find_back)
    ImageButton findBack;
    @BindView(R.id.find_detail_title)
    TextView findDetailTitle;
    @BindView(R.id.find_right)
    ImageButton findRight;
    @BindView(R.id.find_toolbar)
    Toolbar findToolbar;
    @BindView(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @BindView(R.id.find_detail_viewpager)
    ViewPager findDetailViewpager;
    private List<Fragment> fragments = new ArrayList<>();
    private String name;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_detail);
        ButterKnife.bind(this);
        initView();
        initData();
        setAdapter();
    }

    private void initView() {
        name = getIntent().getStringExtra("name");
        findDetailTitle.setText(name);
    }

    private void initData() {
        for (int i = 0; i < TITLE.length; i++) {
            FindChildFragment commonFindFragment = new FindChildFragment();
            fragments.add(commonFindFragment);
        }
    }

    private void setAdapter() {
        final Bundle bundle=new Bundle();
        bundle.putString("name", name);
        viewpagertab.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                bundle.putInt("position",position);
            }
        });

        //实例化设配器
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), FragmentPagerItems.with(this)
        .add(FragmentPagerItem.of(TITLE[0], FindChildFragment.class, bundle))
        .add(FragmentPagerItem.of(TITLE[1],FindChildFragment.class,bundle))
        .create());
        //设置适配器
        findDetailViewpager.setAdapter(adapter);
        viewpagertab.setViewPager(findDetailViewpager);
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
