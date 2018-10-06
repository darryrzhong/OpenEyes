package openeyes.dr.openeyes.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import io.vov.vitamio.utils.Log;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.HotVideo;
import openeyes.dr.openeyes.model.VideoDescription;
import openeyes.dr.openeyes.networks.API;
import openeyes.dr.openeyes.slide.ItemConfig;
import openeyes.dr.openeyes.slide.ItemTouchHelperCallback;
import openeyes.dr.openeyes.slide.OnSlideListener;
import openeyes.dr.openeyes.slide.SlideLayoutManager;
import openeyes.dr.openeyes.widget.SmileView;

/**
 * Created by darryrzhong on 2018/9/12.
 */

public class HotVideoActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.smile_view)
    SmileView mSmileView;
    private SlideLayoutManager mSlideLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelperCallback mItemTouchHelperCallback;
    private MyAdapter mAdapter;

    private int mLikeCount = 0;
    private int mDislikeCount = 0;

    private  RequestQueue queue;
    private Gson gson;
    private List<HotVideo.SubjectsBean> subjectsBeans = new ArrayList<>();
    private AlertDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_video);
        ButterKnife.bind(this);
        initView();



    }

    private void initView() {

        mSmileView.setLike(mLikeCount);
        mSmileView.setDisLike(mDislikeCount);

        initNetData();


    }

    private void initListener() {
        mItemTouchHelperCallback.setOnSlideListener(new OnSlideListener() {
            @Override
            public void onSliding(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                if (direction == ItemConfig.SLIDING_LEFT) {
                } else if (direction == ItemConfig.SLIDING_RIGHT) {
                }
            }

            @Override
            public void onSlided(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                if (direction == ItemConfig.SLIDED_LEFT) {
                    mDislikeCount--;
                    mSmileView.setDisLike(mDislikeCount);
                    mSmileView.disLikeAnimation();
                } else if (direction == ItemConfig.SLIDED_RIGHT) {
                    mLikeCount++;
                    mSmileView.setLike(mLikeCount);
                    mSmileView.likeAnimation();
                }
                int position = viewHolder.getAdapterPosition();
                //android.util.Log.e(TAG, "onSlided--position:" + position);
            }

            @Override
            public void onClear() {
                //addData();
            }
        });
    }

    private void initNetData() {
        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("加载中")
                .setCancelable(true)
                .setTheme(R.style.Custom)
                .build();
        dialog.show();
     queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(API.HOT_VIDEO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                android.util.Log.e("11111111111111",response);
                parseJson(response);//解析json数据
                Log.e("hotVideo>","获取数据成功");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("------>","获取数据失败");

            }
        }) ;







        queue.add(request);
        queue.start();
    }

    /**
    * 解析json数据
    * */
    private void parseJson(String response) {
        gson = new Gson();
       HotVideo hotVideo = gson.fromJson(response,HotVideo.class);
       subjectsBeans = hotVideo.getSubjects();
       setAdapter();
    }

    private void setAdapter() {

        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mItemTouchHelperCallback = new ItemTouchHelperCallback(mRecyclerView.getAdapter(), subjectsBeans);
        mItemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
        mSlideLayoutManager = new SlideLayoutManager(mRecyclerView, mItemTouchHelper);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setLayoutManager(mSlideLayoutManager);
       dialog.dismiss();
        initListener();
    }





    /**
     * 适配器
     */
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(HotVideoActivity.this).inflate(R.layout.item_slide, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.imgBg.setImageURI(Uri.parse(subjectsBeans.get(position).getImages().getSmall()));
            holder.date.setText(subjectsBeans.get(position).getMainland_pubdate());
            holder.tags.setText(subjectsBeans.get(position).getGenres().get(0));
            holder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playVedio(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return subjectsBeans.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            SimpleDraweeView imgBg;
            TextView date;
            TextView tags;
            ImageView play;


            public ViewHolder(View itemView) {
                super(itemView);
                imgBg = itemView.findViewById(R.id.img_bg);
                date = itemView.findViewById(R.id.tv_date);
                tags=itemView.findViewById(R.id.tv_tags);
                play =itemView.findViewById(R.id.play);

            }
        }
    }

    private void playVedio(int position) {

                String videoId = URLEncoder.encode(subjectsBeans.get(position).getId());
                StringRequest request1 = new StringRequest(API.VIDEO + videoId + API.VIDEO_ID, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        VideoDescription description = gson.fromJson(response,VideoDescription.class);
                        String videoUrl = description.getTrailers().get(0).getResource_url();
                        String title = description.getTrailers().get(0).getTitle();
                        Intent intent = new Intent(HotVideoActivity.this,showVideoActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("video",videoUrl);
                        bundle.putString("title",title);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                queue.add(request1);
                queue.start();
            }

}
