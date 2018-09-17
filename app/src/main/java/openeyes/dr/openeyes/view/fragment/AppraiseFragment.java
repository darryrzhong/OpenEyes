package openeyes.dr.openeyes.view.fragment;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.adapter.RecyclerViewAdapter;
import openeyes.dr.openeyes.customdialog.AlertDialog;
import openeyes.dr.openeyes.model.Appraise;
import openeyes.dr.openeyes.utils.RecyItemDecoration;
import openeyes.dr.openeyes.widget.AnimationButton;

/**
 * 意见反馈
 * Created by darryrzhong on 2018/6/26.
 */

public class AppraiseFragment extends Fragment{

    private Unbinder unbinder;
    @BindView(R.id.appraise_bg)
    ViewGroup viewGroup;
    @BindView(R.id.nice_btn)
    ImageButton niceBtn;
    @BindView(R.id.bad_btn)
    ImageButton badBtn;
    @BindView(R.id.nice)
    TextView niceTv;
    @BindView(R.id.bad)
    TextView badTv;
    @BindView(R.id.recycle_view)
    RecyclerView mrecyclerView;
    @BindView(R.id.animation_btn)
    AnimationButton animationButton;
    private static final int MAX=3;
    int spanCount = 2 ; // 3 columns
    int spacing = 50; // 50px
    private boolean isEmpy=true;

    private List<Appraise>  appraises = new ArrayList<>();//Data
    private GridLayoutManager gridManager;//布局管理器
    String appraise [] = {"美观","时尚大气","方便快捷","放松娱乐","极好","喜欢","五星好评","内容丰富"
            ,"发现新大陆","大赞","赏个鸡腿"};
    private MyHandler myHandler = new MyHandler(this);
    private RecyclerViewAdapter adapter;


    /*
    *利用Handler实现动画更新效果
    * */
    public static class MyHandler extends Handler{
        private  final WeakReference<AppraiseFragment> fragmentWeakReference;
        private ObjectAnimator animator;
        private ObjectAnimator animator1;

        public MyHandler(AppraiseFragment fragment){
           fragmentWeakReference = new WeakReference<AppraiseFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final  AppraiseFragment fragment =fragmentWeakReference.get();
            if (fragment!=null){
             switch (msg.what){
                 case 0:
                   //开启动画效果
                     if (animator1!=null){
                         animator1.end();
                     }
                     if (animator!=null){
                         animator.end();
                     }
                     animator = tada((View) msg.obj);
                     animator.setRepeatCount(ValueAnimator.INFINITE);
                     animator.start();

                    /* ObjectAnimator nopeAnimator = nope((View) msg.obj);
                     nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
                     nopeAnimator.start();*/
                     break;
                 case 1:
                     if (animator!=null){
                         animator.end();
                     }
                     if (animator1!=null){
                         animator1.end();
                     }
                     animator1 = tada((View) msg.obj);
                     animator1.setRepeatCount(ValueAnimator.INFINITE);
                     animator1.start();
                     break;

             }
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_appraise,container,false);
        unbinder = ButterKnife.bind(this,view);
        //MyUtils.setBackground(viewGroup,getActivity());
        initView();
        initData();
        initListener();
        return view;
    }

    /*
    * 初始化监听事件
    * */
    private void initListener() {
        //设置RecyclerView的item点击事件(RecyclerView不支持item的点击事件,需自行定义回调)
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void setOnItemClick(View view) {

                TextView text = (TextView) view;
                boolean isSelected = !text.isSelected();
                if (isSelected){
                    text.setSelected(true);
                    isEmpy=false;
                    text.setTextColor(getResources().getColor(R.color.colorAccent));
                    text.setBackgroundResource(R.drawable.shape_card_check);
                }else {
                    text.setSelected(false);
                    isEmpy=true;
                    text.setTextColor(getResources().getColor(R.color.colorWhite));
                    text.setBackgroundResource(R.drawable.shape_card);
                }

            }
        });

       niceTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               niceBtn.setBackgroundResource(R.drawable.nice_check);
               niceTv.setTextColor(Color.RED);
               badBtn.setBackgroundResource(R.drawable.bad_uncheck);
               badTv.setTextColor(getResources().getColor(R.color.colorWhite));
               //开启动画效果
               Message message = new Message();
               message.obj=niceBtn;
               message.what=0;
               myHandler.sendMessage(message);


           }
       });

       badTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               badBtn.setBackgroundResource(R.drawable.bad_check);
               badTv.setTextColor(Color.RED);
               niceBtn.setBackgroundResource(R.drawable.nice_uncheck);
               niceTv.setTextColor(getResources().getColor(R.color.colorWhite));
               //开启动画效果
               Message message = Message.obtain();
               message.obj=badBtn;
               message.what=1;
               myHandler.sendMessage(message);
           }
       });

       /*
       * 提交回调事件
       * */
       animationButton.setAnimationButtonListener(new AnimationButton.AnimationButtonListener() {
           @Override
           public void onClickListener() {
           if (isEmpy){
               new AlertDialog(getContext()).builder()
                       .setMsg("请选择需要提交的反馈")
                       .setNegativeButton("确定", new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {

                           }
                       }).show();

           }else {
               animationButton.start();
           }

           }

           @Override
           public void animationFinish() {
               animationButton.reset();
               FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
               FragmentTransaction transaction = fragmentManager.beginTransaction();
               transaction.replace(R.id.content,new AppraiseFragment()).commit();
               Toast toast = new Toast(getContext());
               toast.setGravity(Gravity.CENTER, 0, 0);
               View view = LayoutInflater.from(getContext()).inflate(R.layout.toast_layout,null);
               TextView tvToast = view.findViewById(R.id.tv_toast);
               ImageView iv = view.findViewById(R.id.iv);
               iv.setVisibility(View.VISIBLE);
               tvToast.setText("提交成功");
               toast.setView(view);
               toast.setDuration(Toast.LENGTH_SHORT);
               toast.show();
           }
       });
    }

    private void initData() {

      for (int i=0;i<appraise.length;i++){
          Appraise appraise1 =  new Appraise(appraise[i]);
          appraises.add(appraise1);
      }

      /*
      * 动态设置column的数量
      * */
        gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return setSpanSize(position,appraises);
            }
        });
        adapter = new RecyclerViewAdapter(appraises);
        mrecyclerView.setAdapter(adapter);

    }

    private void initView() {
        gridManager = new GridLayoutManager(getContext(),spanCount);
         mrecyclerView.setLayoutManager(gridManager);//设置布局管理器
         mrecyclerView.addItemDecoration(new RecyItemDecoration(spanCount,spacing));//设置item间距
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*
    * 动态设置item的column
    * */
    private int setSpanSize(int posation,List<Appraise> appraises){
        int count;
        if (appraises.get(posation).getAppraise().length()>MAX){
            count=2;
        }else {
            count=1;
        }
        return count;
    }


    public static ObjectAnimator tada(View view) {
        return tada(view, 1f);
    }

    /*
    *缩放+原地抖动Animator
    * */
    public static ObjectAnimator tada(View view, float shakeFactor) {

        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.1f, -3f * shakeFactor),
                Keyframe.ofFloat(.2f, -3f * shakeFactor),
                Keyframe.ofFloat(.3f, 3f * shakeFactor),
                Keyframe.ofFloat(.4f, -3f * shakeFactor),
                Keyframe.ofFloat(.5f, 3f * shakeFactor),
                Keyframe.ofFloat(.6f, -3f * shakeFactor),
                Keyframe.ofFloat(.7f, 3f * shakeFactor),
                Keyframe.ofFloat(.8f, -3f * shakeFactor),
                Keyframe.ofFloat(.9f, 3f * shakeFactor),
                Keyframe.ofFloat(1f, 0)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY, pvhRotate).
                setDuration(1000);
    }


/*
* 左右抖动Animator
* */
   /* public static ObjectAnimator nope(View view) {
        int delta = view.getResources().getDimensionPixelOffset(R.dimen.spacing_medium);

        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, -delta),
                Keyframe.ofFloat(.26f, delta),
                Keyframe.ofFloat(.42f, -delta),
                Keyframe.ofFloat(.58f, delta),
                Keyframe.ofFloat(.74f, -delta),
                Keyframe.ofFloat(.90f, delta),
                Keyframe.ofFloat(1f, 0f)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX).
                setDuration(500);
    }*/

}
