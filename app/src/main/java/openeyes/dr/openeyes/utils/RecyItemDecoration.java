package openeyes.dr.openeyes.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by darryrzhong on 2018/6/27.
 * RecyclerView.Item设置间距工具类
 */

public class RecyItemDecoration extends RecyclerView.ItemDecoration {
    private int spanCount; //列数
    private int spacing; //间隔
    private boolean includeEdge; //是否包含边缘

    public RecyItemDecoration(int spanCount, int spacing) {
        this.spanCount = spanCount;
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);//item position
        int column = position%spanCount;// item column

       outRect.left=column*spacing/spanCount;
       outRect.right = spacing - (column + 1) * spacing / spanCount;
        if (position >= spanCount) {
            outRect.top = spacing; // item top
        }

    }

}
