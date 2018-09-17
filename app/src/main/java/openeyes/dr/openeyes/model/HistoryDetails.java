package openeyes.dr.openeyes.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 历史记录详情表
 * Created by darryrzhong on 2018/7/23.
 */


@Table(name = "details")
public class HistoryDetails {
    @Column(name = "id",isId = true,autoGen = true)
    private int id;
    @Column(name = "userid")
    private String userid;
    @Column(name = "photo")
    private String photo;//视频封面
    @Column(name = "title")
    private String title;//视频标题
    @Column( name = "detail")
    private String detail;//视频分类and时长
    @Column(name = "date")
    private String date;//观看日期
    @Column(name = "desc")
    private String desc;//视频详情
    @Column(name = "blurred")
    private String blurred;//模糊图片
    @Column(name = "video")
    private String video;//视频播放地址
    @Column(name = "collect")
    private int collect;//收藏量
    @Column(name = "share")
    private int share;//分享量
    @Column(name = "reply")
    private int reply;//回复量

    /**
     * 无参数构造法不可私有化，否则数据库表格创建异常
     * 且不设置自增，若使用无参构造，容易引起数据插入只有一条。
     */
    public HistoryDetails(){

    }


    /**
    *
    * @param photo 视频封面
    * @param title 视频标题
    * @param detail 视频分类And时间
     *@param date 观看日期
    * @param desc 视频详情
    *@param blurred 迷糊图片
    * @param video 视频播放地址
    * @param collect 收藏量
    * @param share 分享量
    * @param reply 回复量
    * */

    public HistoryDetails(String photo, String title, String detail, String date, String desc, String blurred, String video, int collect, int share, int reply,String userid) {
        this.photo = photo;
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.desc = desc;
        this.blurred = blurred;
        this.video = video;
        this.collect = collect;
        this.share = share;
        this.reply = reply;
        this.userid = userid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBlurred() {
        return blurred;
    }

    public void setBlurred(String blurred) {
        this.blurred = blurred;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
