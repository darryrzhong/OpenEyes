package openeyes.dr.openeyes.database;

import android.util.Log;
import android.view.View;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.IOException;
import java.util.List;

import openeyes.dr.openeyes.model.HistoryDetails;

/**
 * 历史记录表实用类
 * Created by darryrzhong on 2018/7/23.
 */

public class HistoryDB {

    private DbManager dbManager;
    private boolean succeed;//操作是否成功
    boolean idDesc = true;//是否倒序，默认false
    private List<HistoryDetails>  details =null;
    private HistoryDetails historyDetails;
    long count=0;

    //初始化的DbManager对象
    public HistoryDB(){
        dbManager=DatabaseOpenHelper.getInstance();
    }

    /**
     * 将HistoryDetails实例存进数据库
     * 保存新增
     * @param historyDetails
     *
     */

    public void saveHistory(HistoryDetails historyDetails){

        try {
            dbManager.save(historyDetails);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增或更新
     *
     * @param historyDetails
     */

    public void saveOrUpdate(HistoryDetails historyDetails){
        try {
            dbManager.saveOrUpdate(historyDetails);
           // Log.e("log","save succeed!");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取所有HistoryDetails信息
     *
     * @return
     */
  public List<HistoryDetails> loadHistoryAll(){
      try {
          details=dbManager.selector(HistoryDetails.class).findAll();
      } catch (DbException e) {
          e.printStackTrace();
      }
      return details;
  }

    /**
     * 根据视频标题读取HistoryDetails信息
     *
     * @return
     */

    public List<HistoryDetails> loadHistoryByTitle(String title){
        try {
            details=dbManager.selector(HistoryDetails.class).where("title","==",title).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return details;
    }

    /**
     * 根据用户id读取HistoryDetails信息
     *
     * @return
     */

    public List<HistoryDetails> loadHistoryByUserId(String userid){
        try {
            details=dbManager.selector(HistoryDetails.class).where("userid","==",userid).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return details;
    }




    /**
     * 删除对象数据
     *
     *
     * @return
     */
    public boolean delete(HistoryDetails historyDetails){
        try {
            dbManager.delete(historyDetails);
            succeed=true;
        } catch (DbException e) {
            succeed=false;
            e.printStackTrace();
        }
        return succeed;
    }


    /**
     * 删除表
     *
     * @throws DbException
     */

    private void delTable() throws  DbException{
        dbManager.dropTable(HistoryDetails.class);

    }

    //关闭数据库
    public void close(){
        try {
            dbManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
