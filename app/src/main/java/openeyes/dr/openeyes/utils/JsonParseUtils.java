package openeyes.dr.openeyes.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import openeyes.dr.openeyes.model.FindMoreEntity;

/**
 * Created by darryrzhong on 2018/6/14.
 * json解析工具类
 */


/*
* LinkedList双向序列化链表,插入和删除操作比 ArrayList 更加高效
*
* */
public class JsonParseUtils {
    public static List<FindMoreEntity> parseFromJson(String jsonData){
        List<FindMoreEntity> moreEntities = new ArrayList<>();
        Type listType = new TypeToken<LinkedList<FindMoreEntity>>(){}.getType();//获取需要转换的类型
        Gson gson = new Gson();
        LinkedList<FindMoreEntity> findMoreEntities = gson.fromJson(jsonData,listType);
        //遍历集合
        for (Iterator iterator=findMoreEntities.iterator();iterator.hasNext();){
            FindMoreEntity findMoreEntity = (FindMoreEntity) iterator.next();
            moreEntities.add(findMoreEntity);
        }
        return moreEntities;

    }
}
