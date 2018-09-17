package openeyes.dr.openeyes.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by darryrzhong on 2018/9/12.
 */

public class VideoDescription {


    /**
     * rating : {"max":10,"average":8.3,"details":{"1":69,"2":462,"3":6516,"4":20710,"5":13092},"stars":"45","min":0}
     * reviews_count : 661
     * videos : []
     * wish_count : 328528
     * original_title : Mission: Impossible - Fallout
     * blooper_urls : ["http://vt1.doubanio.com/201809121131/4a1617cec476dd678fdf8dc30eae0ad1/view/movie/M/302360050.mp4","http://vt1.doubanio.com/201809121131/10801217b0bf06bfb21809c5b6ad44c9/view/movie/M/302350941.mp4","http://vt1.doubanio.com/201809121131/fd295de1968f9c376440a1fa6ad6a010/view/movie/M/302350816.mp4","http://vt1.doubanio.com/201809121131/d32b8d00334996ebf012c4ccef4577d0/view/movie/M/302350386.mp4"]
     * collect_count : 311232
     * images : {"small":"http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2529365085.jpg","large":"http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2529365085.jpg","medium":"http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2529365085.jpg"}
     * douban_site :
     * year : 2018
     * popular_comments : [{"rating":{"max":5,"value":4,"min":0},"useful_count":3202,"author":{"uid":"53869175","avatar":"http://img3.doubanio.com/icon/u53869175-58.jpg","signature":"","alt":"http://www.douban.com/people/53869175/","id":"53869175","name":"妙可中国"},"subject_id":"26336252","content":"人脸面具、阿汤长镜头跑步、开场录音任务三大系列传统一个没丢，同时也成功打破系列一部一个风格的传统。最大限度开发阿汤的身体魅力和明星光环，而他这股劲头逐年呈现与年龄反比的趋势，但不好的是你会觉得剧本几乎成为了他搏命噱头的陪衬，两次计中计都是看过N遍的老把戏。相比邦德和伯恩，亨特无疑用活力跟上了这个新时代。","created_at":"2018-07-26 23:41:04","id":"1417890798"},{"rating":{"max":5,"value":4,"min":0},"useful_count":169,"author":{"uid":"conanemily","avatar":"http://img7.doubanio.com/icon/u4416375-53.jpg","signature":"wx公共号：米粒电影院","alt":"http://www.douban.com/people/conanemily/","id":"4416375","name":"米粒"},"subject_id":"26336252","content":"3.5 铁人三十八项冠军阿汤哥，部部电影跑下来确实会有种\u201c连汤姆克鲁斯都跑不过汤姆克鲁斯\u201d的感觉。说实话连看几部，每次他们要执行什么mission 是不是impossible已经完全记不清了，其实只要总结一下好人坏人和时好时坏的人就行，然后看看肉搏场面。而这部的动作戏确实是挺有想象力的，雪山直升机追逐战不错，相比之下是否砍掉一些前面的，缩短一些不是更紧凑吗。如果这个城市风光跑酷系列一定要继续拍下去，我希望丽贝卡弗格森可以当主角！她真的有魅力","created_at":"2018-07-28 12:20:53","id":"1419179524"},{"rating":{"max":5,"value":5,"min":0},"useful_count":735,"author":{"uid":"yiri","avatar":"http://img3.doubanio.com/icon/u3150207-27.jpg","signature":"相信守恒。","alt":"http://www.douban.com/people/yiri/","id":"3150207","name":"yiri"},"subject_id":"26336252","content":"阿汤哥牌永动机。","created_at":"2018-07-26 05:58:34","id":"1417152078"},{"rating":{"max":5,"value":4,"min":0},"useful_count":390,"author":{"uid":"60812524","avatar":"http://img3.doubanio.com/icon/u60812524-6.jpg","signature":"","alt":"http://www.douban.com/people/60812524/","id":"60812524","name":"Departure陆离"},"subject_id":"26336252","content":"阿汤哥演的伊森太完美了，以至于白寡妇这个角色被衬托的特别好，她第一眼望过去眼神里就充满了想上伊森的情欲🤣。不得不说IMF的任务一次比一次变态，阿汤哥快60的身体不知道下一步还扛不扛得住（另外按照往常惯例这部里他居然不是长发真的惊了），朱莉亚回归太煽情。","created_at":"2018-07-26 17:06:43","id":"1417550281"}]
     * alt : https://movie.douban.com/subject/26336252/
     * id : 26336252
     * mobile_url : https://movie.douban.com/subject/26336252/mobile
     * photos_count : 612
     * pubdate : 2018-08-31
     * title : 碟中谍6：全面瓦解
     * do_count : null
     * has_video : false
     * share_url : http://m.douban.com/movie/subject/26336252
     * seasons_count : null
     * languages : ["英语","法语"]
     * schedule_url : https://movie.douban.com/subject/26336252/cinema/
     * writers : [{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg"},"name_en":"Christopher McQuarrie","name":"克里斯托弗·麦奎里","alt":"https://movie.douban.com/celebrity/1276314/","id":"1276314"},{"avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1432739964.53.jpg","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1432739964.53.jpg","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1432739964.53.jpg"},"name_en":"Bruce Geller","name":"布鲁斯·盖勒","alt":"https://movie.douban.com/celebrity/1028370/","id":"1028370"}]
     * pubda  tes : ["2018-07-27(美国)","2018-08-31(中国大陆)"]
     * website :
     * tags : ["动作","特工","美国","谍战","2018","间谍","冒险","悬疑","犯罪","剧情"]
     * has_schedule : true
     * durations : ["147分钟","148分钟(中国大陆)"]
     * genres : ["动作","惊悚","冒险"]
     * collection : null
     * trailers : [{"medium":"http://img3.doubanio.com/img/trailer/medium/2531671156.jpg?1534749628","title":"中国预告片：终极版 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/235216/","small":"http://img3.doubanio.com/img/trailer/small/2531671156.jpg?1534749628","resource_url":"http://vt1.doubanio.com/201809121131/526bc87e843bf2a0b6299c439a009e7b/view/movie/M/302350216.mp4","id":"235216"},{"medium":"http://img7.doubanio.com/img/trailer/medium/2532814884.jpg?1535963894","title":"中国预告片 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/235731/","small":"http://img7.doubanio.com/img/trailer/small/2532814884.jpg?1535963894","resource_url":"http://vt1.doubanio.com/201809121131/9f709c6351d3da5fb99e7deadb6d5b2c/view/movie/M/302350731.mp4","id":"235731"},{"medium":"http://img3.doubanio.com/img/trailer/medium/2532505536.jpg?","title":"中国预告片 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/235553/","small":"http://img3.doubanio.com/img/trailer/small/2532505536.jpg?","resource_url":"http://vt1.doubanio.com/201809121131/4ad57945c26809c240441666d9712144/view/movie/M/302350553.mp4","id":"235553"},{"medium":"http://img7.doubanio.com/img/trailer/medium/2531099094.jpg?1534318289","title":"中国预告片：危机四伏版 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/235028/","small":"http://img7.doubanio.com/img/trailer/small/2531099094.jpg?1534318289","resource_url":"http://vt1.doubanio.com/201809121131/b25cdf6ad91038cb2c32f1b2a0585594/view/movie/M/302350028.mp4","id":"235028"}]
     * episodes_count : null
     * trailer_urls : ["http://vt1.doubanio.com/201809121131/526bc87e843bf2a0b6299c439a009e7b/view/movie/M/302350216.mp4","http://vt1.doubanio.com/201809121131/9f709c6351d3da5fb99e7deadb6d5b2c/view/movie/M/302350731.mp4","http://vt1.doubanio.com/201809121131/4ad57945c26809c240441666d9712144/view/movie/M/302350553.mp4","http://vt1.doubanio.com/201809121131/b25cdf6ad91038cb2c32f1b2a0585594/view/movie/M/302350028.mp4"]
     * has_ticket : true
     * bloopers : [{"medium":"http://img7.doubanio.com/img/trailer/medium/2533462321.jpg?1536295801","title":"花絮：\u201c对我来说，拍电影就是度假\u201d\u2014\u2014汤姆·克鲁斯","subject_id":"26336252","alt":"https://movie.douban.com/trailer/236050/","small":"http://img7.doubanio.com/img/trailer/small/2533462321.jpg?1536295801","resource_url":"http://vt1.doubanio.com/201809121131/4a1617cec476dd678fdf8dc30eae0ad1/view/movie/M/302360050.mp4","id":"236050"},{"medium":"http://img7.doubanio.com/img/trailer/medium/2533302103.jpg?","title":"花絮：完整版幕后特辑 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/235941/","small":"http://img7.doubanio.com/img/trailer/small/2533302103.jpg?","resource_url":"http://vt1.doubanio.com/201809121131/10801217b0bf06bfb21809c5b6ad44c9/view/movie/M/302350941.mp4","id":"235941"},{"medium":"http://img7.doubanio.com/img/trailer/medium/2533099740.jpg?1535962116","title":"花絮：摩托车特辑 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/235816/","small":"http://img7.doubanio.com/img/trailer/small/2533099740.jpg?1535962116","resource_url":"http://vt1.doubanio.com/201809121131/fd295de1968f9c376440a1fa6ad6a010/view/movie/M/302350816.mp4","id":"235816"},{"medium":"http://img7.doubanio.com/img/trailer/medium/2531986254.jpg?1535008573","title":"花絮 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/235386/","small":"http://img7.doubanio.com/img/trailer/small/2531986254.jpg?1535008573","resource_url":"http://vt1.doubanio.com/201809121131/d32b8d00334996ebf012c4ccef4577d0/view/movie/M/302350386.mp4","id":"235386"}]
     * clip_urls : ["http://vt1.doubanio.com/201809121131/623700353d0ce9d5cbf13f0771359d75/view/movie/M/302360243.mp4","http://vt1.doubanio.com/201809121131/df2b59b0d4322c9d99572d2ad6a9b986/view/movie/M/302360018.mp4","http://vt1.doubanio.com/201809121131/2f9410708426894873bd0149ecf796b9/view/movie/M/302350584.mp4","http://vt1.doubanio.com/201809121131/7642e83cb9644292a976c310c201e34f/view/movie/M/302350423.mp4"]
     * current_season : null
     * casts : [{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p567.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p567.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p567.jpg"},"name_en":"Tom Cruise","name":"汤姆·克鲁斯","alt":"https://movie.douban.com/celebrity/1054435/","id":"1054435"},{"avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1371934661.95.jpg","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1371934661.95.jpg","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1371934661.95.jpg"},"name_en":"Henry Cavill","name":"亨利·卡维尔","alt":"https://movie.douban.com/celebrity/1044713/","id":"1044713"},{"avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8712.jpg","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8712.jpg","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8712.jpg"},"name_en":"Ving Rhames","name":"文·瑞姆斯","alt":"https://movie.douban.com/celebrity/1048129/","id":"1048129"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8176.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8176.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p8176.jpg"},"name_en":"Simon Pegg","name":"西蒙·佩吉","alt":"https://movie.douban.com/celebrity/1035648/","id":"1035648"}]
     * countries : ["美国"]
     * mainland_pubdate : 2018-08-31
     * photos : [{"thumb":"http://img3.doubanio.com/view/photo/m/public/p2532072669.jpg","image":"http://img3.doubanio.com/view/photo/l/public/p2532072669.jpg","cover":"http://img3.doubanio.com/view/photo/sqs/public/p2532072669.jpg","alt":"https://movie.douban.com/photos/photo/2532072669/","id":"2532072669","icon":"http://img3.doubanio.com/view/photo/s/public/p2532072669.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/m/public/p2528318164.jpg","image":"http://img7.doubanio.com/view/photo/l/public/p2528318164.jpg","cover":"http://img7.doubanio.com/view/photo/sqs/public/p2528318164.jpg","alt":"https://movie.douban.com/photos/photo/2528318164/","id":"2528318164","icon":"http://img7.doubanio.com/view/photo/s/public/p2528318164.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/m/public/p2528317833.jpg","image":"http://img7.doubanio.com/view/photo/l/public/p2528317833.jpg","cover":"http://img7.doubanio.com/view/photo/sqs/public/p2528317833.jpg","alt":"https://movie.douban.com/photos/photo/2528317833/","id":"2528317833","icon":"http://img7.doubanio.com/view/photo/s/public/p2528317833.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/m/public/p2532940313.jpg","image":"http://img7.doubanio.com/view/photo/l/public/p2532940313.jpg","cover":"http://img7.doubanio.com/view/photo/sqs/public/p2532940313.jpg","alt":"https://movie.douban.com/photos/photo/2532940313/","id":"2532940313","icon":"http://img7.doubanio.com/view/photo/s/public/p2532940313.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/m/public/p2533334290.jpg","image":"http://img7.doubanio.com/view/photo/l/public/p2533334290.jpg","cover":"http://img7.doubanio.com/view/photo/sqs/public/p2533334290.jpg","alt":"https://movie.douban.com/photos/photo/2533334290/","id":"2533334290","icon":"http://img7.doubanio.com/view/photo/s/public/p2533334290.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/m/public/p2527737144.jpg","image":"http://img7.doubanio.com/view/photo/l/public/p2527737144.jpg","cover":"http://img7.doubanio.com/view/photo/sqs/public/p2527737144.jpg","alt":"https://movie.douban.com/photos/photo/2527737144/","id":"2527737144","icon":"http://img7.doubanio.com/view/photo/s/public/p2527737144.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/m/public/p2511717134.jpg","image":"http://img7.doubanio.com/view/photo/l/public/p2511717134.jpg","cover":"http://img7.doubanio.com/view/photo/sqs/public/p2511717134.jpg","alt":"https://movie.douban.com/photos/photo/2511717134/","id":"2511717134","icon":"http://img7.doubanio.com/view/photo/s/public/p2511717134.jpg"},{"thumb":"http://img3.doubanio.com/view/photo/m/public/p2511717127.jpg","image":"http://img3.doubanio.com/view/photo/l/public/p2511717127.jpg","cover":"http://img3.doubanio.com/view/photo/sqs/public/p2511717127.jpg","alt":"https://movie.douban.com/photos/photo/2511717127/","id":"2511717127","icon":"http://img3.doubanio.com/view/photo/s/public/p2511717127.jpg"},{"thumb":"http://img3.doubanio.com/view/photo/m/public/p2527737069.jpg","image":"http://img3.doubanio.com/view/photo/l/public/p2527737069.jpg","cover":"http://img3.doubanio.com/view/photo/sqs/public/p2527737069.jpg","alt":"https://movie.douban.com/photos/photo/2527737069/","id":"2527737069","icon":"http://img3.doubanio.com/view/photo/s/public/p2527737069.jpg"},{"thumb":"http://img7.doubanio.com/view/photo/m/public/p2512056195.jpg","image":"http://img7.doubanio.com/view/photo/l/public/p2512056195.jpg","cover":"http://img7.doubanio.com/view/photo/sqs/public/p2512056195.jpg","alt":"https://movie.douban.com/photos/photo/2512056195/","id":"2512056195","icon":"http://img7.doubanio.com/view/photo/s/public/p2512056195.jpg"}]
     * summary : 有时好意会造成恶果，人反而被自己所造成的结果所困扰。伊桑·亨特（汤姆·克鲁斯）和他的IMF团队（亚历克·鲍德温、西蒙·佩吉、文·瑞姆斯）将在最新的电影《碟中谍6：全面瓦解》中再度回归，他们会与观众们熟悉的盟友（丽贝卡·弗格森、米歇尔·莫娜汉）一起与时间赛跑，应对一次任务中出现的意外。亨利·卡维尔、安吉拉·贝塞特和凡妮莎·柯比也将加入本片的演员阵容，电影制片人克里斯托夫·迈考利将会再度担任导演。
     * clips : [{"medium":"http://img3.doubanio.com/img/trailer/medium/2533839797.jpg?1536661604","title":"片段 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/236243/","small":"http://img3.doubanio.com/img/trailer/small/2533839797.jpg?1536661604","resource_url":"http://vt1.doubanio.com/201809121131/623700353d0ce9d5cbf13f0771359d75/view/movie/M/302360243.mp4","id":"236243"},{"medium":"http://img3.doubanio.com/img/trailer/medium/2533379499.jpg?","title":"片段：片段合集 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/236018/","small":"http://img3.doubanio.com/img/trailer/small/2533379499.jpg?","resource_url":"http://vt1.doubanio.com/201809121131/df2b59b0d4322c9d99572d2ad6a9b986/view/movie/M/302360018.mp4","id":"236018"},{"medium":"http://img7.doubanio.com/img/trailer/medium/2532537091.jpg?1535532340","title":"片段 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/235584/","small":"http://img7.doubanio.com/img/trailer/small/2532537091.jpg?1535532340","resource_url":"http://vt1.doubanio.com/201809121131/2f9410708426894873bd0149ecf796b9/view/movie/M/302350584.mp4","id":"235584"},{"medium":"http://img7.doubanio.com/img/trailer/medium/2532074493.jpg?1535096142","title":"片段 (中文字幕)","subject_id":"26336252","alt":"https://movie.douban.com/trailer/235423/","small":"http://img7.doubanio.com/img/trailer/small/2532074493.jpg?1535096142","resource_url":"http://vt1.doubanio.com/201809121131/7642e83cb9644292a976c310c201e34f/view/movie/M/302350423.mp4","id":"235423"}]
     * subtype : movie
     * directors : [{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg"},"name_en":"Christopher McQuarrie","name":"克里斯托弗·麦奎里","alt":"https://movie.douban.com/celebrity/1276314/","id":"1276314"}]
     * comments_count : 62421
     * popular_reviews : [{"rating":{"max":5,"value":5,"min":0},"title":"电影不需要是3D的，但是导航一定要3D的","subject_id":"26336252","author":{"uid":"mbbbbbbbcker","avatar":"http://img3.doubanio.com/icon/u45066005-6.jpg","signature":"你們中出了一個叛徒","alt":"http://www.douban.com/people/mbbbbbbbcker/","id":"45066005","name":"马泽尔法克尔"},"summary":"就算被剧透了，你还是会去电影院看这部电影。不过跟最近的其他影片相比，这一部确实是最怕剧透的，所以请谨慎下翻。 一切纷争由三颗铁蛋引起。准确说来，应该是钚蛋，注意是金字旁，不要因为惯性思维看成土字旁。...","alt":"https://movie.douban.com/review/9545627/","id":"9545627"},{"rating":{"max":5,"value":5,"min":0},"title":"严肃影评#44：《碟中谍》系列的极限在哪里？ - 9.5/10","subject_id":"26336252","author":{"uid":"TheGZMovieGuy","avatar":"http://img7.doubanio.com/icon/u95316262-2.jpg","signature":"严肃影评 & 严肃回顾","alt":"http://www.douban.com/people/TheGZMovieGuy/","id":"95316262","name":"TheGZMovieGuy"},"summary":"文末有关于高跳低开（HALO Jump）动作场面的详细讨论，有兴趣的朋友可以关注一下！ 转眼22年过去，碟中谍系列已经走到第6部了。22年前俊俏的阿汤哥参与第1部的拍摄时，大概并没有想到这部60年代老旧英剧的翻拍作...","alt":"https://movie.douban.com/review/9546561/","id":"9546561"},{"rating":{"max":5,"value":5,"min":0},"title":"《碟中谍6：全面瓦解》，很多你不知道的事！！（宝马M5 系列的两种车型；有腹肌的西蒙......）","subject_id":"26336252","author":{"uid":"strikeman","avatar":"http://img7.doubanio.com/icon/u96893311-3.jpg","signature":"","alt":"http://www.douban.com/people/strikeman/","id":"96893311","name":"Aloneye"},"summary":"你知道阿汤哥的骨折是怎么回事吗？ 你知道\u201c高跳低开\u201d是什么样危险行动吗？ 你知道丽贝卡在拍戏的时候已经怀孕了吗？ 西蒙·佩吉也有八块腹肌了？ 这次片中的宝马轿车，知道是什么型号的吗？ 片中，直升飞机的追...","alt":"https://movie.douban.com/review/9552526/","id":"9552526"},{"rating":{"max":5,"value":4,"min":0},"title":"拍摄《碟中谍6》本身就是不可能的任务，但阿汤哥做到了","subject_id":"26336252","author":{"uid":"lingrui1995","avatar":"http://img3.doubanio.com/icon/u63688511-18.jpg","signature":"一个影迷","alt":"http://www.douban.com/people/lingrui1995/","id":"63688511","name":"凌睿"},"summary":"最近，整个网络都在传\u201c《碟中谍6》系列最佳\u201d。单看数据的话也的确如此，截止7月31日MTC 86分，烂番茄新鲜度98%，均分8.4，IMDb 8.5分，均是系列最高分。 《碟中谍6》确实是一部非常优秀的动作片，甚至是今年最...","alt":"https://movie.douban.com/review/9553431/","id":"9553431"},{"rating":{"max":5,"value":4,"min":0},"title":"阿汤哥：我了个大去的，玩了这么多吧吃鸡，各种救人，你们谁遇到过在天上就需要补血的队友？","subject_id":"26336252","author":{"uid":"nggw","avatar":"http://img3.doubanio.com/icon/u2579250-17.jpg","signature":"我看起来像橘子但我真的是个南瓜","alt":"http://www.douban.com/people/nggw/","id":"2579250","name":"南瓜国王@似水流年"},"summary":"警告：以下不但有剧透，还有剧解刨及剧瞎想，未看慎入！ 眼看着007换了一波又一波，伯恩最近也悄无声息，拯救世界的重任最后只好落在年仅六旬的阿汤哥身上 阿汤：哎，谁让我帅啊，没办法\u2026 见过了各种fancy的布置...","alt":"https://movie.douban.com/review/9549554/","id":"9549554"},{"rating":{"max":5,"value":5,"min":0},"title":"阿汤哥这哪里是在演戏，分明是在上纪实真人秀！（暴多拍摄现场图以证清白）","subject_id":"26336252","author":{"uid":"zishuiyilan","avatar":"http://img3.doubanio.com/icon/u1048262-16.jpg","signature":"偷得浮生半日闲","alt":"http://www.douban.com/people/zishuiyilan/","id":"1048262","name":"薇羅尼卡"},"summary":"本来没想写东西，结果周五看IMAX场看得我血脉贲张，还想二刷！就是那种你明明知道这个片没啥剧情主要就是让人爽爽爽的，但是爽爽爽和爽爽爽还有分不同的，对不对！这个片可不是什么替身、CGI或者抠图的存在，从第...","alt":"https://movie.douban.com/review/9629303/","id":"9629303"},{"rating":{"max":5,"value":4,"min":0},"title":"敬阿汤哥！敬《碟中谍》！","subject_id":"26336252","author":{"uid":"157129341","avatar":"http://img3.doubanio.com/icon/u157129341-8.jpg","signature":"","alt":"http://www.douban.com/people/157129341/","id":"157129341","name":"CC~"},"summary":"碟中谍从1996年的第一部到现在2018的第六部，转眼就是22年！完全是陪伴了几代人的成长，深受其影响，致使如今培养了一大批从小树立间谍梦的青年（包括我在内），虽然长大后我们都会慢慢发现，年少时的间谍梦是那...","alt":"https://movie.douban.com/review/9599640/","id":"9599640"},{"rating":{"max":5,"value":2,"min":0},"title":"毫无风格","subject_id":"26336252","author":{"uid":"tiberium","avatar":"http://img3.doubanio.com/icon/u1255670-36.jpg","signature":"我要拿什么给她们消愁？","alt":"http://www.douban.com/people/tiberium/","id":"1255670","name":"Tiberium"},"summary":"要不是一堆人的hype，我原本是不会去看碟中谍6的，因为我已经对好莱坞动作片审美疲劳。结果看到评价这样高，我抱着去看一部有突破的动作片的期待走进电影院，结果是\u2026\u2026浪费70块钱。我近期看的最差好莱坞电影，没...","alt":"https://movie.douban.com/review/9630412/","id":"9630412"},{"rating":{"max":5,"value":4,"min":0},"title":"《碟中谍》:同样是悬疑动作片，我为何对该系列情有独钟？","subject_id":"26336252","author":{"uid":"161795616","avatar":"http://img7.doubanio.com/icon/u161795616-3.jpg","signature":"","alt":"http://www.douban.com/people/161795616/","id":"161795616","name":"张福星♂"},"summary":"同样是腰椎间盘，为何你如此突出。 《碟中谍6》内容已经更新，如有需要直接翻到最后。 阿汤哥连超人都打败了，下一部是不是直接打灭霸。 原文\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014 首先声明:你...","alt":"https://movie.douban.com/review/9546202/","id":"9546202"},{"rating":{"max":5,"value":4,"min":0},"title":"建议一刷就看IMAX，二刷好累的","subject_id":"26336252","author":{"uid":"silence2top","avatar":"http://img7.doubanio.com/icon/u16417472-23.jpg","signature":"在无趣中寻找有趣","alt":"http://www.douban.com/people/silence2top/","id":"16417472","name":"Silence"},"summary":"本人汤哥20年真爱粉，但不喜动作片所以MI系列都没好好看过。今年初春时候我朋友目击到汤哥在圣保罗大教堂拍外景，急得我那天午休就冒着风雨往圣保罗跑可惜只看到保姆车没看到人。然后电影院里看到的预告片把我一...","alt":"https://movie.douban.com/review/9602103/","id":"9602103"}]
     * ratings_count : 167029
     * aka : ["碟中谍6","不可能的任务：全面瓦解(台)","职业特工队：叛逆之谜(港)","Mission: Impossible 6","MI6"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String original_title;
    private int collect_count;
    private ImagesBean images;
    private String douban_site;
    private String year;
    private String alt;
    private String id;
    private String mobile_url;
    private int photos_count;
    private String pubdate;
    private String title;
    private Object do_count;
    private boolean has_video;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private String website;
    private boolean has_schedule;
    private Object collection;
    private Object episodes_count;
    private boolean has_ticket;
    private Object current_season;
    private String mainland_pubdate;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<?> videos;
    private List<String> blooper_urls;
    private List<PopularCommentsBean> popular_comments;
    private List<String> languages;
    private List<WritersBean> writers;
    private List<String> pubdates;
    private List<String> tags;
    private List<String> durations;
    private List<String> genres;
    private List<TrailersBean> trailers;
    private List<String> trailer_urls;
    private List<BloopersBean> bloopers;
    private List<String> clip_urls;
    private List<CastsBean> casts;
    private List<String> countries;
    private List<PhotosBean> photos;
    private List<ClipsBean> clips;
    private List<DirectorsBean> directors;
    private List<PopularReviewsBean> popular_reviews;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public int getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(int photos_count) {
        this.photos_count = photos_count;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public boolean isHas_video() {
        return has_video;
    }

    public void setHas_video(boolean has_video) {
        this.has_video = has_video;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isHas_schedule() {
        return has_schedule;
    }

    public void setHas_schedule(boolean has_schedule) {
        this.has_schedule = has_schedule;
    }

    public Object getCollection() {
        return collection;
    }

    public void setCollection(Object collection) {
        this.collection = collection;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public boolean isHas_ticket() {
        return has_ticket;
    }

    public void setHas_ticket(boolean has_ticket) {
        this.has_ticket = has_ticket;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getMainland_pubdate() {
        return mainland_pubdate;
    }

    public void setMainland_pubdate(String mainland_pubdate) {
        this.mainland_pubdate = mainland_pubdate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<?> getVideos() {
        return videos;
    }

    public void setVideos(List<?> videos) {
        this.videos = videos;
    }

    public List<String> getBlooper_urls() {
        return blooper_urls;
    }

    public void setBlooper_urls(List<String> blooper_urls) {
        this.blooper_urls = blooper_urls;
    }

    public List<PopularCommentsBean> getPopular_comments() {
        return popular_comments;
    }

    public void setPopular_comments(List<PopularCommentsBean> popular_comments) {
        this.popular_comments = popular_comments;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<WritersBean> getWriters() {
        return writers;
    }

    public void setWriters(List<WritersBean> writers) {
        this.writers = writers;
    }

    public List<String> getPubdates() {
        return pubdates;
    }

    public void setPubdates(List<String> pubdates) {
        this.pubdates = pubdates;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getDurations() {
        return durations;
    }

    public void setDurations(List<String> durations) {
        this.durations = durations;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<TrailersBean> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<TrailersBean> trailers) {
        this.trailers = trailers;
    }

    public List<String> getTrailer_urls() {
        return trailer_urls;
    }

    public void setTrailer_urls(List<String> trailer_urls) {
        this.trailer_urls = trailer_urls;
    }

    public List<BloopersBean> getBloopers() {
        return bloopers;
    }

    public void setBloopers(List<BloopersBean> bloopers) {
        this.bloopers = bloopers;
    }

    public List<String> getClip_urls() {
        return clip_urls;
    }

    public void setClip_urls(List<String> clip_urls) {
        this.clip_urls = clip_urls;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotosBean> photos) {
        this.photos = photos;
    }

    public List<ClipsBean> getClips() {
        return clips;
    }

    public void setClips(List<ClipsBean> clips) {
        this.clips = clips;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<PopularReviewsBean> getPopular_reviews() {
        return popular_reviews;
    }

    public void setPopular_reviews(List<PopularReviewsBean> popular_reviews) {
        this.popular_reviews = popular_reviews;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 8.3
         * details : {"1":69,"2":462,"3":6516,"4":20710,"5":13092}
         * stars : 45
         * min : 0
         */

        private int max;
        private double average;
        private DetailsBean details;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public static class DetailsBean {
            /**
             * 1 : 69
             * 2 : 462
             * 3 : 6516
             * 4 : 20710
             * 5 : 13092
             */

            @SerializedName("1")
            private int _$1;
            @SerializedName("2")
            private int _$2;
            @SerializedName("3")
            private int _$3;
            @SerializedName("4")
            private int _$4;
            @SerializedName("5")
            private int _$5;

            public int get_$1() {
                return _$1;
            }

            public void set_$1(int _$1) {
                this._$1 = _$1;
            }

            public int get_$2() {
                return _$2;
            }

            public void set_$2(int _$2) {
                this._$2 = _$2;
            }

            public int get_$3() {
                return _$3;
            }

            public void set_$3(int _$3) {
                this._$3 = _$3;
            }

            public int get_$4() {
                return _$4;
            }

            public void set_$4(int _$4) {
                this._$4 = _$4;
            }

            public int get_$5() {
                return _$5;
            }

            public void set_$5(int _$5) {
                this._$5 = _$5;
            }
        }
    }

    public static class ImagesBean {
        /**
         * small : http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2529365085.jpg
         * large : http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2529365085.jpg
         * medium : http://img7.doubanio.com/view/photo/s_ratio_poster/public/p2529365085.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class PopularCommentsBean {
        /**
         * rating : {"max":5,"value":4,"min":0}
         * useful_count : 3202
         * author : {"uid":"53869175","avatar":"http://img3.doubanio.com/icon/u53869175-58.jpg","signature":"","alt":"http://www.douban.com/people/53869175/","id":"53869175","name":"妙可中国"}
         * subject_id : 26336252
         * content : 人脸面具、阿汤长镜头跑步、开场录音任务三大系列传统一个没丢，同时也成功打破系列一部一个风格的传统。最大限度开发阿汤的身体魅力和明星光环，而他这股劲头逐年呈现与年龄反比的趋势，但不好的是你会觉得剧本几乎成为了他搏命噱头的陪衬，两次计中计都是看过N遍的老把戏。相比邦德和伯恩，亨特无疑用活力跟上了这个新时代。
         * created_at : 2018-07-26 23:41:04
         * id : 1417890798
         */

        private RatingBeanX rating;
        private int useful_count;
        private AuthorBean author;
        private String subject_id;
        private String content;
        private String created_at;
        private String id;

        public RatingBeanX getRating() {
            return rating;
        }

        public void setRating(RatingBeanX rating) {
            this.rating = rating;
        }

        public int getUseful_count() {
            return useful_count;
        }

        public void setUseful_count(int useful_count) {
            this.useful_count = useful_count;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class RatingBeanX {
            /**
             * max : 5
             * value : 4
             * min : 0
             */

            private int max;
            private int value;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class AuthorBean {
            /**
             * uid : 53869175
             * avatar : http://img3.doubanio.com/icon/u53869175-58.jpg
             * signature :
             * alt : http://www.douban.com/people/53869175/
             * id : 53869175
             * name : 妙可中国
             */

            private String uid;
            private String avatar;
            private String signature;
            private String alt;
            private String id;
            private String name;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    public static class WritersBean {
        /**
         * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg"}
         * name_en : Christopher McQuarrie
         * name : 克里斯托弗·麦奎里
         * alt : https://movie.douban.com/celebrity/1276314/
         * id : 1276314
         */

        private AvatarsBean avatars;
        private String name_en;
        private String name;
        private String alt;
        private String id;

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg
             * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg
             * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class TrailersBean {
        /**
         * medium : http://img3.doubanio.com/img/trailer/medium/2531671156.jpg?1534749628
         * title : 中国预告片：终极版 (中文字幕)
         * subject_id : 26336252
         * alt : https://movie.douban.com/trailer/235216/
         * small : http://img3.doubanio.com/img/trailer/small/2531671156.jpg?1534749628
         * resource_url : http://vt1.doubanio.com/201809121131/526bc87e843bf2a0b6299c439a009e7b/view/movie/M/302350216.mp4
         * id : 235216
         */

        private String medium;
        private String title;
        private String subject_id;
        private String alt;
        private String small;
        private String resource_url;
        private String id;

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getResource_url() {
            return resource_url;
        }

        public void setResource_url(String resource_url) {
            this.resource_url = resource_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class BloopersBean {
        /**
         * medium : http://img7.doubanio.com/img/trailer/medium/2533462321.jpg?1536295801
         * title : 花絮：“对我来说，拍电影就是度假”——汤姆·克鲁斯
         * subject_id : 26336252
         * alt : https://movie.douban.com/trailer/236050/
         * small : http://img7.doubanio.com/img/trailer/small/2533462321.jpg?1536295801
         * resource_url : http://vt1.doubanio.com/201809121131/4a1617cec476dd678fdf8dc30eae0ad1/view/movie/M/302360050.mp4
         * id : 236050
         */

        private String medium;
        private String title;
        private String subject_id;
        private String alt;
        private String small;
        private String resource_url;
        private String id;

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getResource_url() {
            return resource_url;
        }

        public void setResource_url(String resource_url) {
            this.resource_url = resource_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class CastsBean {
        /**
         * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p567.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p567.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p567.jpg"}
         * name_en : Tom Cruise
         * name : 汤姆·克鲁斯
         * alt : https://movie.douban.com/celebrity/1054435/
         * id : 1054435
         */

        private AvatarsBeanX avatars;
        private String name_en;
        private String name;
        private String alt;
        private String id;

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p567.jpg
             * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p567.jpg
             * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p567.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class PhotosBean {
        /**
         * thumb : http://img3.doubanio.com/view/photo/m/public/p2532072669.jpg
         * image : http://img3.doubanio.com/view/photo/l/public/p2532072669.jpg
         * cover : http://img3.doubanio.com/view/photo/sqs/public/p2532072669.jpg
         * alt : https://movie.douban.com/photos/photo/2532072669/
         * id : 2532072669
         * icon : http://img3.doubanio.com/view/photo/s/public/p2532072669.jpg
         */

        private String thumb;
        private String image;
        private String cover;
        private String alt;
        private String id;
        private String icon;

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class ClipsBean {
        /**
         * medium : http://img3.doubanio.com/img/trailer/medium/2533839797.jpg?1536661604
         * title : 片段 (中文字幕)
         * subject_id : 26336252
         * alt : https://movie.douban.com/trailer/236243/
         * small : http://img3.doubanio.com/img/trailer/small/2533839797.jpg?1536661604
         * resource_url : http://vt1.doubanio.com/201809121131/623700353d0ce9d5cbf13f0771359d75/view/movie/M/302360243.mp4
         * id : 236243
         */

        private String medium;
        private String title;
        private String subject_id;
        private String alt;
        private String small;
        private String resource_url;
        private String id;

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getResource_url() {
            return resource_url;
        }

        public void setResource_url(String resource_url) {
            this.resource_url = resource_url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class DirectorsBean {
        /**
         * avatars : {"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg"}
         * name_en : Christopher McQuarrie
         * name : 克里斯托弗·麦奎里
         * alt : https://movie.douban.com/celebrity/1276314/
         * id : 1276314
         */

        private AvatarsBeanXX avatars;
        private String name_en;
        private String name;
        private String alt;
        private String id;

        public AvatarsBeanXX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanXX avatars) {
            this.avatars = avatars;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanXX {
            /**
             * small : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg
             * large : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg
             * medium : http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1535912054.09.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class PopularReviewsBean {
        /**
         * rating : {"max":5,"value":5,"min":0}
         * title : 电影不需要是3D的，但是导航一定要3D的
         * subject_id : 26336252
         * author : {"uid":"mbbbbbbbcker","avatar":"http://img3.doubanio.com/icon/u45066005-6.jpg","signature":"你們中出了一個叛徒","alt":"http://www.douban.com/people/mbbbbbbbcker/","id":"45066005","name":"马泽尔法克尔"}
         * summary : 就算被剧透了，你还是会去电影院看这部电影。不过跟最近的其他影片相比，这一部确实是最怕剧透的，所以请谨慎下翻。 一切纷争由三颗铁蛋引起。准确说来，应该是钚蛋，注意是金字旁，不要因为惯性思维看成土字旁。...
         * alt : https://movie.douban.com/review/9545627/
         * id : 9545627
         */

        private RatingBeanXX rating;
        private String title;
        private String subject_id;
        private AuthorBeanX author;
        private String summary;
        private String alt;
        private String id;

        public RatingBeanXX getRating() {
            return rating;
        }

        public void setRating(RatingBeanXX rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public AuthorBeanX getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBeanX author) {
            this.author = author;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class RatingBeanXX {
            /**
             * max : 5
             * value : 5
             * min : 0
             */

            private int max;
            private int value;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class AuthorBeanX {
            /**
             * uid : mbbbbbbbcker
             * avatar : http://img3.doubanio.com/icon/u45066005-6.jpg
             * signature : 你們中出了一個叛徒
             * alt : http://www.douban.com/people/mbbbbbbbcker/
             * id : 45066005
             * name : 马泽尔法克尔
             */

            private String uid;
            private String avatar;
            private String signature;
            private String alt;
            private String id;
            private String name;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
