package openeyes.dr.openeyes.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 本地缓存详情表
 * Created by darryrzhong on 2018/7/28.
 */

@Table(name = "download")
public class DownloadDetails {
    @Column(name = "id",isId = true,autoGen = true)
    private int id;
    @Column(name = "photo")
    private String photo;//视频封面
    @Column(name = "title")
    private String title;//视频标题
    @Column( name = "detail")
    private String detail;//视频分类and时长
    @Column(name = "video")
    private String video;//视频播放地址
    @Column(name = "state")
    private String state;//视频缓存状态

    /**
     * 无参数构造法不可私有化，否则数据库表格创建异常
     * 且不设置自增，若使用无参构造，容易引起数据插入只有一条。
     */
    public DownloadDetails(){

    }

    /**
     *
     * @param photo 视频封面
     * @param title 视频标题
     * @param detail 视频分类And时间
     * @param video 视频播放地址
     *@param state 视频缓存状态
     *
     * */

    public DownloadDetails(String photo, String title, String detail, String video,String state) {
        this.photo = photo;
        this.title = title;
        this.detail = detail;
        this.video = video;
        this.state=state;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
