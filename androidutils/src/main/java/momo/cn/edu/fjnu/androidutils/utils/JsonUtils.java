package momo.cn.edu.fjnu.androidutils.utils;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Json工具
 * Created by GaoFei on 2016/3/17.
 */
public class JsonUtils {
    public static final String TAG = JsonUtils.class.getSimpleName();

    public static JSONArray listToJsonArray(List<?> objects){
        JSONArray arrays = new JSONArray();
        for(Object object : objects){
            JSONObject jsonObject = new JSONObject();
            Class<?> objectClass = object.getClass();
            Field[] fields = objectClass.getDeclaredFields();
            for(Field field:fields){
                try{
                    field.setAccessible(true);
                    jsonObject.put(field.getName(), field.get(object));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            arrays.put(jsonObject);
        }
        return arrays;
    }

    /**
     * 将对象转化为Object
     * @param object
     * @return
     */
    public static JSONObject objectToJson(Object object){
        JSONObject jsonObject = new JSONObject();
        Class<?> objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        for(Field field:fields){
            try{
                field.setAccessible(true);
                jsonObject.put(field.getName(), field.get(object));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return jsonObject;
    }


    /**
     * 将json对象转化为java对象
     * @param tClass
     * @param jsonObject
     * @return
     */
    public static Object jsonToObject(Class< ?> tClass, JSONObject jsonObject){
        Object object = null;
        try{
            object = tClass.newInstance();
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()){
                String fieldName = iterator.next();
                Field field = tClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, jsonObject.get(fieldName));
            }
        }catch (Exception e){
            Log.i(TAG, "" + e);
            e.printStackTrace();
        }
        return object;
    }

    public static List<?> arrayToList(Class<?> tClass, JSONArray array){
        List list = new ArrayList<>();
        for(int i = 0; i != array.length(); ++i){
            try{
                list.add(jsonToObject(tClass, array.getJSONObject(i)));
            }catch (Exception e){

            }

        }
        return list;
    }
}
