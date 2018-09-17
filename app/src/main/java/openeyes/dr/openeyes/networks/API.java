package openeyes.dr.openeyes.networks;

import openeyes.dr.openeyes.view.activity.StatementActivity;

/**
 * Created by darryrzhong on 2018/6/7.
 * 网络请求地址API
 */

public interface API {

    //每日精选
    public static final String DAILY="http://baobab.wandoujia.com/api/v2/feed?num=2&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
    //发现更多
    public static final String FIND_MORE="http://baobab.wandoujia.com/api/v2/categories?udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
    //热门排行
    public static final String HOT_STRATEGY="http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
    //发现更多详情接口
    public static final String FIND_DETAIL="http://baobab.kaiyanapp.com/api/v4/discovery/category";
    //默认头像地址
    public static final String USER_ICON="https://upload-images.jianshu.io/upload_images/5549640-bc72cbbac2138c94.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240";
    //关键字搜索接口
    //http://baobab.kaiyanapp.com/api/v1/search?num=10&query=小姐姐
    public static final String KEYWORD_SEARCH="http://baobab.kaiyanapp.com/api/v1/search?start=1&num=10&query=";
    //public static final String FIND_DETAIL="http://baobab.wandoujia.com/api/v3/videos?categoryName=%s&strategy=%s&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83";
    //http://baobab.wandoujia.com/api/v3/ranklist?num=10&strategy=monthly&udid=26868b32e808498db32fd51fb422d00175e179df&vc=83
    //热映电影接口
    public static final  String HOT_VIDEO="https://api.douban.com/v2/movie/in_theaters?apikey=0b2bdeda43b5688921839c8ecb20399b&city=%E5%8C%97%E4%BA%AC&start=0&count=100&client=&udid=";
   //电影介绍接口,id来自热映电影中对于id;
    public static final String VIDEO = "http://api.douban.com/v2/movie/subject/";
    public static final String VIDEO_ID ="?apikey=0b2bdeda43b5688921839c8ecb20399b&city=%E5%8C%97%E4%BA%AC&client=&udid=";



}
