package openeyes.dr.openeyes.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.SearchResult;
import openeyes.dr.openeyes.utils.AdapterUtils;
import openeyes.dr.openeyes.utils.ViewHolderUtils;
import openeyes.dr.openeyes.view.activity.ShareActivity;
import openeyes.dr.openeyes.view.activity.VideoDetailActivity;

/**
 * Created by darryrzhong on 2018/9/11.
 */

public class SearchResultAdapter extends AdapterUtils<SearchResult.ItemListBean.DataBean> {
    private Context context;
    private String time;
    private String second;
    private String minute;

    public SearchResultAdapter(Context context, List<SearchResult.ItemListBean.DataBean> mDatas, int layoutId) {
        super(context, mDatas, layoutId);
        this.context= context;
    }

    @Override
    public void convert(ViewHolderUtils viewHolderUtils, final SearchResult.ItemListBean.DataBean dataBean) {
          initData(dataBean);
         viewHolderUtils.setImageResourcewithFresco(R.id.iv_photo, Uri.parse(dataBean.getCover().getFeed()));
         viewHolderUtils.setCustomTextView(R.id.video_time,time);
         viewHolderUtils.setImageResourcewithFresco(R.id.avatar_author,Uri.parse(dataBean.getAuthor().getIcon()));
         viewHolderUtils.setText(R.id.video_title,dataBean.getTitle());
         viewHolderUtils.setText(R.id.author_category,dataBean.getAuthor().getName()+" / "+"#"+dataBean.getCategory());
         viewHolderUtils.setOnClickListener(R.id.share_iv, new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context,ShareActivity.class);
                 Bundle bundle = new Bundle();
                 bundle.putString("cover",dataBean.getCover().getFeed());//封面
                 bundle.putString("vague",dataBean.getCover().getBlurred());//模糊图片
                 bundle.putString("title",dataBean.getTitle());//视频title
                 bundle.putString("video",dataBean.getPlayUrl());
                 bundle.putString("desc",dataBean.getDescription());
                 intent.putExtras(bundle);
                 context.startActivity(intent);

             }
         });
         viewHolderUtils.setOnClickListener(R.id.iv_photo, new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(context, VideoDetailActivity.class);
                 Bundle bundle = new Bundle();
                 bundle.putString("title",dataBean.getTitle());
                 bundle.putString("time", dataBean.getCategory() +" / "+'#'+ initData(dataBean));
                 bundle.putString("desc", dataBean.getDescription());//视频描述
                 bundle.putString("blurred", dataBean.getCover().getBlurred());//模糊图片地址
                 bundle.putString("feed", dataBean.getCover().getFeed());//图片地址
                 bundle.putString("video", dataBean.getPlayUrl());//视频播放地址
                 bundle.putInt("collect", dataBean.getConsumption().getCollectionCount());//收藏量
                 bundle.putInt("share", dataBean.getConsumption().getShareCount());//分享量
                 bundle.putInt("reply", dataBean.getConsumption().getReplyCount());//回复数量
                 intent.putExtras(bundle);
                 context.startActivity(intent);
             }
         });
    }

    private String initData(SearchResult.ItemListBean.DataBean dataBean) {
        //获取到时间
        int duration = dataBean.getDuration();
        int mm = duration / 60;//分
        int ss = duration % 60;//秒
        //秒
        second = "";
        //分
        minute = "";
        if (ss < 10) {
            second = "0" + String.valueOf(ss);
        } else {
            second = String.valueOf(ss);
        }
        if (mm < 10) {
            minute = "0" + String.valueOf(mm);
        } else {
            minute = String.valueOf(mm);//分钟
        }
        time = minute +":"+ second;

        return time;


    }

}
