package openeyes.dr.openeyes.database;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.io.IOException;
import java.util.List;

import openeyes.dr.openeyes.model.HistoryDetails;
import openeyes.dr.openeyes.model.UserInfoEntity;

/**
 * Created by darryrzhong on 2018/9/8.
 */

public class UserDB  {
    private DbManager dbManager;
    private boolean succeed;//操作是否成功
    boolean idDesc = true;//是否倒序，默认false
    private List<UserInfoEntity> details;

    //初始化dbManager
    public UserDB(){
        dbManager=DatabaseOpenHelper.getInstance();
    }


    //保存数据
    public void saveUserInfo(UserInfoEntity userInfoEntity){

        try {
            dbManager.save(userInfoEntity);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    //更新或保存
    public void saveOrUpdate(UserInfoEntity userInfoEntity){
        try {
            dbManager.saveOrUpdate(userInfoEntity);
            // Log.e("log","save succeed!");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    //查询所有数据
    public List<UserInfoEntity> loadUserInfoAll(){
        try {
            details=dbManager.selector(UserInfoEntity.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return details;
    }


    //根据userId查询数据
    public List<UserInfoEntity> loadUserInfoByUserId(String userid){
        try {
            details=dbManager.selector(UserInfoEntity.class).where("userId","==",userid).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return details;
    }


    //删除对象数据
    public boolean delete(UserInfoEntity userInfoEntity){
        try {
            dbManager.delete(userInfoEntity);
            succeed=true;
        } catch (DbException e) {
            succeed=false;
            e.printStackTrace();
        }
        return succeed;
    }

    //删除表
    private void delTable() throws  DbException{
        dbManager.dropTable(UserInfoEntity.class);

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
