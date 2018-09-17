package openeyes.dr.openeyes.database;

import org.xutils.DbManager;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;

import java.util.List;

import openeyes.dr.openeyes.model.DownloadDetails;
import openeyes.dr.openeyes.model.SearchHistory;
import openeyes.dr.openeyes.model.UserInfoEntity;

/**
 * Created by darryrzhong on 2018/9/10.
 */


public class SearchDB {
    private DbManager dbManager;
    private boolean succeed;//操作是否成功
    boolean idDesc = true;//是否倒序，默认false
    private List<SearchHistory> details;

    public SearchDB(){
        dbManager=DatabaseOpenHelper.getInstance();
    }

    //更新或保存
    public void saveOrUpdate(SearchHistory searchHistory){
        try {
            dbManager.saveOrUpdate(searchHistory);
            // Log.e("log","save succeed!");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    //查询所有数据
    public List<SearchHistory> loadSearchHistoryAll(){
        try {
            details=dbManager.selector(SearchHistory.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return details;
    }


    public List<SearchHistory> loadSearchByKeyWord(String keyWord){
        try {
            details=dbManager.selector(SearchHistory.class).where("keyWord","==",keyWord).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return details;
    }

    //删除表
    public void delTable() throws  DbException{
        dbManager.dropTable(SearchHistory.class);
    }
}
