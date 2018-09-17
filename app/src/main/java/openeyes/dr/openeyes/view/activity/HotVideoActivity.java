package openeyes.dr.openeyes.view.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.vov.vitamio.utils.Log;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.HotVideo;
import openeyes.dr.openeyes.model.VideoDescription;
import openeyes.dr.openeyes.networks.API;
import openeyes.dr.openeyes.utils.Utils;
import openeyes.dr.openeyes.widget.FadeTransitionImageView;
import openeyes.dr.openeyes.widget.HorizontalTransitionLayout;
import openeyes.dr.openeyes.widget.PileLayout;
import openeyes.dr.openeyes.widget.VerticalTransitionLayout;

/**
 * Created by darryrzhong on 2018/9/12.
 */

public class HotVideoActivity extends AppCompatActivity {

    private View positionView;
    private PileLayout pileLayout;

    private int lastDisplay = -1;

    private ObjectAnimator transitionAnimator;
    private float transitionValue;
    private HorizontalTransitionLayout titleView, durationsView;
    private VerticalTransitionLayout dateView, genresView;
    private FadeTransitionImageView bottomView;
    private Animator.AnimatorListener animatorListener;
    private TextView descriptionView;
    private  RequestQueue queue;
    private Gson gson;
    private List<HotVideo.SubjectsBean> subjectsBeans = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_video);
        initView();


    }

    private void initView() {

        positionView = findViewById(R.id.positionView);
        titleView = (HorizontalTransitionLayout) findViewById(R.id.countryView);
        durationsView = (HorizontalTransitionLayout) findViewById(R.id.temperatureView);
        pileLayout = (PileLayout) findViewById(R.id.pileLayout);
        dateView = (VerticalTransitionLayout) findViewById(R.id.addressView);
        descriptionView = (TextView) findViewById(R.id.descriptionView);
        genresView = (VerticalTransitionLayout) findViewById(R.id.timeView);
        bottomView = (FadeTransitionImageView) findViewById(R.id.bottomImageView);

        // 1. 状态栏侵入
        boolean adjustStatusHeight = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            adjustStatusHeight = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }

        // 2. 状态栏占位View的高度调整
        final String brand = Build.BRAND;
        if (brand.contains("Xiaomi")) {
            Utils.setXiaomiDarkMode(this);
        } else if (brand.contains("Meizu")) {
            Utils.setMeizuDarkMode(this);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            adjustStatusHeight = false;
        }
        if (adjustStatusHeight) {
            adjustStatusBarHeight(); // 调整状态栏高度
        }

        animatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                titleView.onAnimationEnd();
                durationsView.onAnimationEnd();
                dateView.onAnimationEnd();
                bottomView.onAnimationEnd();
                genresView.onAnimationEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        // 3. PileLayout绑定Adapter

        initNetData();

        pileLayout.setAdapter(new PileLayout.Adapter() {
            @Override
            public int getLayoutId() {
                return R.layout.item_layout;
            }

            @Override
            public void bindView(View view, int position) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (viewHolder == null) {
                    viewHolder = new ViewHolder();
                    viewHolder.imageView = (SimpleDraweeView) view.findViewById(R.id.imageView);
                    view.setTag(viewHolder);
                }

                viewHolder.imageView.setImageURI(Uri.parse(subjectsBeans.get(position).getImages().getSmall()));
                android.util.Log.e("+-+-+-+","第一步"+position);
            }

            @Override
            public int getItemCount() {
                return subjectsBeans.size();
            }


            @Override
            public void displaying(int position) {
                descriptionView.setText( " Since the world is so beautiful, You have to believe me, and this index is " + position);

                if (lastDisplay < 0) {
                    initSecene(position);
                    lastDisplay = 0;
                } else if (lastDisplay != position) {
                    transitionSecene(position);
                    lastDisplay = position;
                }
            }

            @Override
            public void onItemClick(View view, int position) {
                super.onItemClick(view, position);
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
        });



    }

    private void initNetData() {
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
        android.util.Log.e("hotVideo",subjectsBeans.get(0).getImages().getSmall());

    }

    /**
     * 调整沉浸状态栏
     */
    private void adjustStatusBarHeight() {
        int statusBarHeight = Utils.getStatusBarHeight(this);
        ViewGroup.LayoutParams lp = positionView.getLayoutParams();
        lp.height = statusBarHeight;
        positionView.setLayoutParams(lp);
    }

    private void initSecene(int position) {
        titleView.firstInit(subjectsBeans.get(position).getTitle());
        android.util.Log.e("+-+-+-+","第二步"+position+subjectsBeans.get(position).getTitle());
        durationsView.firstInit(subjectsBeans.get(position).getDurations().get(0));
        dateView.firstInit("上映时间 "+subjectsBeans.get(position).getMainland_pubdate());
        bottomView.firstInit(subjectsBeans.get(position).getImages().getSmall());
        genresView.firstInit(subjectsBeans.get(position).getGenres().get(0));
    }


    private void transitionSecene(int position) {
        if (transitionAnimator != null) {
            transitionAnimator.cancel();
        }

        titleView.saveNextPosition(position, subjectsBeans.get(position).getTitle());
        android.util.Log.e("+-+-+-+","第三步"+position+subjectsBeans.get(position).getTitle());

        durationsView.saveNextPosition(position, subjectsBeans.get(position).getDurations().get(0));
        dateView.saveNextPosition(position, "上映时间 "+subjectsBeans.get(position).getMainland_pubdate());
        bottomView.saveNextPosition(position, subjectsBeans.get(position).getImages().getSmall());
        genresView.saveNextPosition(position,subjectsBeans.get(position).getGenres().get(0));

        transitionAnimator = ObjectAnimator.ofFloat(this, "transitionValue", 0.0f, 1.0f);
        transitionAnimator.setDuration(300);
        transitionAnimator.start();
        transitionAnimator.addListener(animatorListener);

    }


    /**
     * 属性动画
     */
    public void setTransitionValue(float transitionValue) {
        this.transitionValue = transitionValue;
        titleView.duringAnimation(transitionValue);
        durationsView.duringAnimation(transitionValue);
        dateView.duringAnimation(transitionValue);
        bottomView.duringAnimation(transitionValue);
        genresView.duringAnimation(transitionValue);
    }

    public float getTransitionValue() {
        return transitionValue;
    }


    class ViewHolder {
        SimpleDraweeView imageView;
    }

}
