package openeyes.dr.openeyes.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.model.FindMoreDetails;
import openeyes.dr.openeyes.model.FindMoreEntity;
import openeyes.dr.openeyes.utils.AdapterUtils;
import openeyes.dr.openeyes.utils.ViewHolderUtils;
import openeyes.dr.openeyes.view.activity.FinddetailActivity;

/**
 * Created by darryrzhong on 2018/9/6.
 */

public class AttentionAdapter extends AdapterUtils<FindMoreEntity> {

    private Context context;

    public AttentionAdapter(Context context, List<FindMoreEntity> mDatas, int layoutId) {
        super(context, mDatas, layoutId);
        this.context=context;
    }

    @Override
    public void convert(ViewHolderUtils viewHolderUtils, final FindMoreEntity findMoreEntity) {
        viewHolderUtils.setText(R.id.attention_title,"开眼"+findMoreEntity.getName()+"精选");
        viewHolderUtils.setText(R.id.desc,findMoreEntity.getDescription());
        viewHolderUtils.setImageResourcewithFresco(R.id.attention_icon,Uri.parse(findMoreEntity.getHeaderImage()));
        viewHolderUtils.setOnClickListener(R.id.ll_attention, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,FinddetailActivity.class);
                intent.putExtra("name",findMoreEntity.getName());
                context.startActivity(intent);
            }
        });
    }
}
