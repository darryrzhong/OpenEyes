package openeyes.dr.openeyes.database;

import org.xutils.DbManager;
import org.xutils.x;

/**
 * 数据库帮助类
 * Created by darryrzhong on 2018/7/23.
 */

public class DatabaseOpenHelper {
  private DbManager.DaoConfig daoConfig;
  private static DbManager dbManager;
  private final String DB_NAME="history.db";
  private final int VERSION = 1;


  private DatabaseOpenHelper(){
      //初始化数据库配置信息
      daoConfig = new DbManager.DaoConfig()
              .setDbName(DB_NAME)//设置数据库名称
              .setDbVersion(VERSION)//设置初始版本号
              .setDbOpenListener(new DbManager.DbOpenListener() {//监听数据库打开
                  @Override
                  public void onDbOpened(DbManager db) {
                     db.getDatabase().enableWriteAheadLogging();
                      //开启WAL, 对写入加速提升巨大(作者原话)
                  }
              })
              .setDbUpgradeListener(new DbManager.DbUpgradeListener() {//监听数据库更新
                  @Override
                  public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                  }
              });
      dbManager= x.getDb(daoConfig);//获取数据库管理类
  }

  //单例设计
  public static DbManager getInstance(){
     if (dbManager==null){
         DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper();
     }
     return dbManager;
  }
}
