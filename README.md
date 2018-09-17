# OpenEyes
仿【开眼App】开源项目，一款提供在线精彩短视频观看的App.


# 开眼短视频(OpenEyes)
>仿照(开眼视频)Android端(`旧版UI,新版UI已改变`)做的一个App，每天更新一个精美短视频应用，一个非常美的短视频应用，UI界面基本上是参照开眼视频Android端来做的。 在该项目中，我采用的是Vitamio的视频播放器框架，下面具体介绍一下项目结构及实现功能和第三方引用.

## 项目结构
1.主界面主要分【每日精选】 【发现更多】 【热门排行】三个模块.
效果图如下:


![每日精选.jpeg](https://upload-images.jianshu.io/upload_images/5549640-ef22c0941a6f9fdf.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![发现更多.jpeg](https://upload-images.jianshu.io/upload_images/5549640-72381624c9e2d456.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![热门排行.jpeg](https://upload-images.jianshu.io/upload_images/5549640-ea4e23af94e8b695.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

2.侧滑菜单界面:主要分【离线缓存】 【观看记录】 【热映电影】【分享】 【更多】 【天气】【关于】 【主题】 【个人中心】九个模块.
侧滑菜单界面如下:


![侧滑.jpeg](https://upload-images.jianshu.io/upload_images/5549640-f25802fff596cf38.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![侧滑.jpeg](https://upload-images.jianshu.io/upload_images/5549640-e73fa825ffc5e8ba.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

## UI效果展示:
1.【离线缓存】:支持视频下载、存储空间提示、文件删除等功能

![无下载记录.jpeg](https://upload-images.jianshu.io/upload_images/5549640-e6f0aa7c95496414.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![已下载.jpeg](https://upload-images.jianshu.io/upload_images/5549640-22502ad67bd27a00.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![删除.jpeg](https://upload-images.jianshu.io/upload_images/5549640-6df934bdec5af60f.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

2.【观看记录】:实现账号同步观看记录、清空记录等功能

![观看记录.jpeg](https://upload-images.jianshu.io/upload_images/5549640-758ce1156da9ebf9.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![观看记录.jpeg](https://upload-images.jianshu.io/upload_images/5549640-3e1ff91fa7453947.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

3.【热映电影】:实现最近影院热播电影介绍及预告片观看


![电影介绍.jpeg](https://upload-images.jianshu.io/upload_images/5549640-35a505ad49db12ef.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![预告观看.jpeg](https://upload-images.jianshu.io/upload_images/5549640-394b659973461ca5.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/480)

4.【分享】:实现微信、朋友圈、QQ空间、微博的第三方分享


![分享.jpeg](https://upload-images.jianshu.io/upload_images/5549640-62bf4c03c3bad879.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![视频分享.jpeg](https://upload-images.jianshu.io/upload_images/5549640-7dda606a6448a767.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![朋友圈分享.jpeg](https://upload-images.jianshu.io/upload_images/5549640-63ba699c652a2db7.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

5.【个人中心】 实现实名制手机注册登录,第三方qq登录,同步用户观看动态等功能



![Screenshot_2018-09-17-12-10-45.jpeg](https://upload-images.jianshu.io/upload_images/5549640-c5dab18383a13bc0.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![Screenshot_2018-09-17-12-10-56.jpeg](https://upload-images.jianshu.io/upload_images/5549640-f74b79b7053f6f34.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

6.【关键词搜索】:实现关键词搜索相关视频,同步搜索历史记录

![关键词搜索.jpeg](https://upload-images.jianshu.io/upload_images/5549640-200de7d533d9df94.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)

![搜索结果.jpeg](https://upload-images.jianshu.io/upload_images/5549640-b91201187b55d9b6.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)



# 主要技术介绍:
1.使用到的第三方开源框架有：
*   [Smarttablayout](https://github.com/ogaclejapan/SmartTabLayout) `自定义ViewPager标题条，在滚动时为用户提供持续反馈`

*   [Butterknife](https://github.com/JakeWharton/butterknife)  `Android视图的字段和方法绑定`

*   [Volley](https://github.com/mcxiaoke/android-volley)  `android 网络加载框架`
*   [Ultra-Pull-To-Refresh](https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh) `下拉刷新框架`
*   [Fresco](https://github.com/facebook/fresco) `图片加载框架`
*   [Vitamio](https://github.com/yixia/VitamioBundle) `视频播放器框架`
*   [MaterialEditText](https://github.com/rengwuxian/MaterialEditText) `集成MaterialDesign风格的自定义EditText`
*   [Android-Debug-Database](https://github.com/amitshekhariitbhu/Android-Debug-Database)`Debug版本下 实现网页查询数据库`
*  [SwipeBackLayout](https://github.com/ikew0ng/SwipeBackLayout) `ios右滑返回效果`
*  [okhttp](https://github.com/square/okhttp) `android 网络加载框架`
*  [xUtils3](https://github.com/wyouflf/xUtils3) `Android 集成工具类`
* [MobSDK](http://www.mob.com/) `第三方sdk集成支持`

# APK下载地址:
 [点击下载](https://fir.im/8psl)

# 声明
> 【开眼短视频】是一款提供每日精选视频应用的app，非官方版本，仅作学习交流之用，数据来源于开眼视频App，数据接口均属于非正常渠道获取，请勿用于商业用途，原作公司拥有所有权利。如涉及侵权问题,请及时联系作者,感谢支持.

`此项目属于入门级练手项目,不涉及复杂开发架构,如常用Retrfit+Rxjava+Okhttp+Mvp等,适合刚入门级别独立开发,项目中还存在少数bug,若发现,欢迎指正交流`

欢迎关注作者[darryrzhong](http://www.darryrzhong.site),更多干货等你来拿哟.

### 请赏个小红心！因为你的鼓励是我写作的最大动力！
>更多精彩文章请关注
- [个人博客:darryrzhong](http://www.darryrzhong.site)
- [掘金](https://juejin.im/user/5a6c3b19f265da3e49804988)
- [简书](https://www.jianshu.com/users/b7fdf53ec0b9/timeline)
- [SegmentFault](https://segmentfault.com/u/darryrzhong_5ac59892a5882/articles)
- [慕课网手记](https://www.imooc.com/u/6733207)
