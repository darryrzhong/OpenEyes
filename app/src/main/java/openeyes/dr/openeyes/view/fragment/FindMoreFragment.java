package openeyes.dr.openeyes.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.vov.vitamio.utils.Log;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.FindMoreEntity;
import openeyes.dr.openeyes.networks.API;
import openeyes.dr.openeyes.utils.AdapterUtils;
import openeyes.dr.openeyes.utils.JsonParseUtils;
import openeyes.dr.openeyes.utils.NetConnectedUtils;
import openeyes.dr.openeyes.utils.ViewHolderUtils;
import openeyes.dr.openeyes.view.activity.FinddetailActivity;

/**
 * Created by darryrzhong on 2018/6/14.
 * 发现更多
 *
 */

public class FindMoreFragment extends Fragment {

    private View view;
    private Unbinder unbinder;
    @BindView(R.id.find_grid)
    GridView findGrid;
    @BindView(R.id.llayout_hint)
    LinearLayout layout;
    private List<FindMoreEntity>  moreEntities = new ArrayList<>();
    private List<FindMoreEntity> adapter;

    public FindMoreFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find_more, container, false);
        unbinder = ButterKnife.bind(this,view);
        isNetConnected();
        setListener();
        return view;
    }

    private void isNetConnected() {
        if (NetConnectedUtils.isNetConnected(getContext())){
            layout.setVisibility(View.GONE);
            initData();
        }else {
            layout.setVisibility(View.VISIBLE);
        }
    }

    /*
    * 获取数据
    * */
    private void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(API.FIND_MORE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parseJson(response);//解析json数据
                Log.e("------>","获取数据成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("------>","获取数据失败");

            }
        });
        requestQueue.add(request);
        requestQueue.start();
    }

    private void parseJson(String response) {
        List<FindMoreEntity> moreEntitiess = JsonParseUtils.parseFromJson(response);
        moreEntities.addAll(moreEntitiess);
        setAdapter(moreEntities);
    }

    private void setListener() {
       findGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               FindMoreEntity findMoreEntity = moreEntities.get(position);
               Intent intent = new Intent(getContext(),FinddetailActivity.class);
               intent.putExtra("name",findMoreEntity.getName());
               startActivity(intent);

           }
       });

       layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               isNetConnected();
           }
       });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*
    * 设置设配器
    * */
    public void setAdapter(List<FindMoreEntity> findMoreEntity) {
        findGrid.setAdapter(new AdapterUtils<FindMoreEntity>(getContext(),findMoreEntity,R.layout.grid_item) {
            @Override
            public void convert(ViewHolderUtils viewHolderUtils, FindMoreEntity findMoreEntity) {
                viewHolderUtils.setText(R.id.grid_tv,findMoreEntity.getName());
                viewHolderUtils.setImageResourcewithFresco(R.id.grid_iv, Uri.parse(findMoreEntity.getBgPicture()));
            }
        });
    }
}
