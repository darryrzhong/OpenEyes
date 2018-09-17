package openeyes.dr.openeyes.model;

/**
 * Created by darryrzhong on 2018/6/7.
 * 发现更多
 */

public class FindMoreEntity {

    /**
     * id : 24
     * name : 时尚
     * alias : null
     * description : 优雅地行走在潮流尖端
     * bgPicture : http://img.kaiyanapp.com/22192a40de238fe853b992ed57f1f098.jpeg
     * bgColor :
     * headerImage : http://img.kaiyanapp.com/c9b19c2f0a2a40f4c45564dd8ea766d3.png
     * defaultAuthorId : 2160
     */

    private int id;
    private String name;
    private Object alias;
    private String description;
    private String bgPicture;
    private String bgColor;
    private String headerImage;
    private int defaultAuthorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getAlias() {
        return alias;
    }

    public void setAlias(Object alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public int getDefaultAuthorId() {
        return defaultAuthorId;
    }

    public void setDefaultAuthorId(int defaultAuthorId) {
        this.defaultAuthorId = defaultAuthorId;
    }
}
