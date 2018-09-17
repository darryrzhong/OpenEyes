package openeyes.dr.openeyes.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.utils.MyUtils;
import openeyes.dr.openeyes.view.activity.WebViewrActivity;

/**
 * Created by darryrzhong on 2018/6/22.
 * 关于App
 */

public class AboutFragment extends Fragment {

    private Unbinder unbinder;
    @BindView(R.id.about_layout)
    ViewGroup viewGroup;
    @BindView(R.id.github)
    TextView github;
    @BindView(R.id.blog)
    TextView blog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment,container,false);
        unbinder = ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        //MyUtils.setBackground(viewGroup,getActivity());
    }

    @OnClick(R.id.github)
    public void webGithub(){
        Intent intent = new Intent(getContext(), WebViewrActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url","https://github.com/darryrzhong");
        bundle.putString("title","Github");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.blog)
    public void webBlog(){
        Intent intent = new Intent(getContext(), WebViewrActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title","darryrzhong");
        bundle.putString("url","http://www.darryrzhong.site");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
