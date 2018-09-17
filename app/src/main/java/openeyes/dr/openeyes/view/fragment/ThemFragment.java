package openeyes.dr.openeyes.view.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.xutils.common.util.LogUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import openeyes.dr.openeyes.MainActivity;
import openeyes.dr.openeyes.R;
import openeyes.dr.openeyes.adapter.ThemeAdapter;
import openeyes.dr.openeyes.model.Theme;
import openeyes.dr.openeyes.model.WallpaperEvent;
import openeyes.dr.openeyes.utils.MyUtils;
import openeyes.dr.openeyes.utils.PubConstants;

/**
 * Created by darryrzhong on 2018/6/19.
 * 主题Fragment
 */

public class ThemFragment extends Fragment {

   private Unbinder unbinder;
   @BindView(R.id.gv_change_theme)
   GridView gridView;
   @BindView(R.id.background)
   ViewGroup mBackground;



   /**
    * 壁纸资源的集合
    */
   private List<Theme> mList;

   /**
    * 保存主题壁纸的适配器
    */
   private ThemeAdapter mAdapter;

   /**
    * 壁纸名
    */
   private String mWallpaperName;

   /**
    * 当前壁纸图片名称
    */
   private String mCurrentWallpaper;
   private WallpaperEvent wallpaperEvent;


   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      initAdapter();
      // 初始化主题壁纸适配器
      mCurrentWallpaper = mWallpaperName;
   }


   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.teme,container,false);
      unbinder = ButterKnife.bind(this,view);//绑定Fragment
      initView();
      return view;
   }

   private void initView() {
      MyUtils.setBackgroundBlur(mBackground,getActivity());//设置模糊壁纸
      gridView.setAdapter(mAdapter);//设置适配器
      gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Theme theme = mList.get(position);
            String resName = theme.getResName();
            if (mCurrentWallpaper.equals(resName)) {
               return;
            }

            if (mCurrentWallpaper.equals(resName)) {
               return;
            }
            mCurrentWallpaper = resName;
            // 更新当前选中壁纸的名称
            mAdapter.updateSelection(resName);
            // 更新适配器刷新GridView显示
            mAdapter.notifyDataSetChanged();

            // 保存壁纸信息
            MyUtils.saveWallpaper(getActivity(), PubConstants.WALLPAPER_NAME, resName);
            wallpaperEvent = new WallpaperEvent(true);
               updateBackground();
         }
      });
   }

   private void updateBackground() {
      if (mBackground != null) {
         MyUtils.setBackgroundBlur(mBackground, getActivity());
         MyUtils.setBackground(MainActivity.viewGroup, getActivity());
         // 不是app自带壁纸
         if (mAdapter != null && !wallpaperEvent.isAppWallpaper()) {
            mAdapter.updateSelection("");
            mAdapter.notifyDataSetChanged();
         }
      }
   }



   private void initAdapter() {
      SharedPreferences share = getActivity().getSharedPreferences(
              PubConstants.EXTRA_WEAC_SHARE, Activity.MODE_PRIVATE);
      String wallpaperPath = share.getString(PubConstants.WALLPAPER_PATH, null);
      // 当为自定义主题壁纸，不显示壁纸标记
      if (wallpaperPath != null) {
         mWallpaperName = "";
      } else {
         // 取得使用中壁纸的位置
         mWallpaperName = share.getString(PubConstants.WALLPAPER_NAME,
                 PubConstants.DEFAULT_WALLPAPER_NAME);
      }
      mList = new ArrayList<>();
      // 资源文件集合
      Field[] fields = R.drawable.class.getDeclaredFields();
      // 遍历资源文件
      for (Field field : fields) {
         String name = field.getName();
         // 取得文件名以"wallpaper_"开始的图片
         if (name.startsWith("wallpaper_")) {
            try {
               Theme theme = new Theme();
               theme.setResName(name);
               theme.setResId(field.getInt(R.drawable.class));
               this.mList.add(theme);
            } catch (IllegalAccessException | IllegalArgumentException e) {
               LogUtil.e( "initAdapter(): " + e.toString());
            }
         }
      }

      this.mAdapter = new ThemeAdapter(getActivity(), mList, mWallpaperName);
   }

   @Override
   public void onDestroyView() {
      super.onDestroyView();
      unbinder.unbind();//解除绑定
   }
}
