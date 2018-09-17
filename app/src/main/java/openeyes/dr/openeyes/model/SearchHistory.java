package openeyes.dr.openeyes.model;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by darryrzhong on 2018/9/10.
 */
@Table(name = "search")
public class SearchHistory {
    @Column(name = "id", isId = true, autoGen = true)
    private int id;
    @Column(name = "keyWord")
    private String keyWord;

    /**
     * 无参数构造法不可私有化，否则数据库表格创建异常
     * 且不设置自增，若使用无参构造，容易引起数据插入只有一条。
     */

    public SearchHistory(){

    }

    public SearchHistory(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
