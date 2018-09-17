package openeyes.dr.openeyes.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by darryrzhong on 2018/9/8.
 */

@Table(name = "userInfo")
public class UserInfoEntity {
    @Column(name = "id",isId = true,autoGen = true)
    private int id;
    @Column(name = "userId")
    private String userId;
    @Column(name = "passWord")
    private String passWord;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userIcon")
    private String userIcon;

    public UserInfoEntity(){

    }

    public UserInfoEntity(String userId, String passWord, String userName, String userIcon) {
        this.userId = userId;
        this.passWord = passWord;
        this.userName = userName;
        this.userIcon = userIcon;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}
