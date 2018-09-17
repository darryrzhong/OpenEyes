package openeyes.dr.openeyes.database;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.IOException;
import java.util.List;

import openeyes.dr.openeyes.model.DownloadDetails;
import openeyes.dr.openeyes.model.HistoryDetails;

/**
 * 本地缓存详情表实用类
 * Created by darryrzhong on 2018/7/28.
 */

public class DownloadDB {

    private DbManager dbManager;
    private boolean succeed;//操作是否成功
    boolean idDesc = true;//是否倒序，默认false
    private List<DownloadDetails>  details=null;
    private DownloadDetails downloadDetails;
    long count=0;

    //初始化的DbManager对象
    public DownloadDB(){
        dbManager=DatabaseOpenHelper.getInstance();
    }

    /**
     * 将DownloadDetails实例存进数据库
     * 保存新增
     * @param downloadDetails
     *
     */

    public void saveDownload(DownloadDetails downloadDetails){

        try {
            dbManager.save(downloadDetails);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增或更新
     *
     * @param downloadDetails
     */

    public void saveOrUpdate(DownloadDetails downloadDetails){
        try {
            dbManager.saveOrUpdate(downloadDetails);
            // Log.e("log","save succeed!");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /*
    * 更新数据
    * */
    public void updateDownloadState(DownloadDetails downloadDetails){
        downloadDetails.setState("未缓存");
        try {
            dbManager.update(downloadDetails,"state");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取所有DownloadDetails信息
     *
     * @return
     */
    public List<DownloadDetails> loadDownAll(){
        try {
            details=dbManager.selector(DownloadDetails.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return details;
    }

    /**
     * 根据视频标题读取DownloadDetails信息
     *
     * @return
     */

    public List<DownloadDetails> loadDownByTitle(String title){
        try {
            details=dbManager.selector(DownloadDetails.class).where("title","==",title).findAll();
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
    public boolean delete(DownloadDetails downloadDetails){
        try {
            dbManager.delete(downloadDetails);
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
        dbManager.dropTable(DownloadDetails.class);

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
