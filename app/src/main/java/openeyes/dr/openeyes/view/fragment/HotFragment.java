package openeyes.dr.openeyes.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.utils.NetConnectedUtils;

/**
 * Created by darryrzhong on 2018/6/13.
 * 热门排行
 */

public class HotFragment extends Fragment {
    //标题
    private static final String[] TITLE = new String[]{"周排行", "月排行", "总排行"};
    @BindView(R.id.hot_viewpager)
    ViewPager hotViewpager;
    @BindView(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @BindView(R.id.llayout_hint)
    LinearLayout layout;
    private Unbinder unbinder;
    private List<Fragment> fragments = new ArrayList<>();

    public HotFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        unbinder = ButterKnife.bind(this,view);
        initView();
        isNetConnected();
        return view;
    }


    private void isNetConnected() {
        if (NetConnectedUtils.isNetConnected(getContext())){
            layout.setVisibility(View.GONE);
            initData();
            setAdapter();
        }else {
            layout.setVisibility(View.VISIBLE);
        }
    }
    private void initView() {
       layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               isNetConnected();
           }
       });
    }

    private void initData() {
        //创建三个fragment
        for (int i=0;i<TITLE.length;i++){
            HotChildFragment hotChildFragment = new HotChildFragment();
            fragments.add(hotChildFragment);
        }
        hotViewpager.setOffscreenPageLimit(3);//设置缓冲的页面数量
    }

    //设置设配器
    private void setAdapter() {
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getChildFragmentManager(), FragmentPagerItems.with(getContext())
                .add(TITLE[0], fragments.get(0).getClass())
                .add(TITLE[1], fragments.get(1).getClass())
                .add(TITLE[2], fragments.get(2).getClass())
                .create());
         hotViewpager.setAdapter(adapter);
         viewpagertab.setViewPager(hotViewpager);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
       unbinder.unbind();
    }
}
