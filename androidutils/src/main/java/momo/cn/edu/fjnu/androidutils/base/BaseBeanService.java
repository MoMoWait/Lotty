package momo.cn.edu.fjnu.androidutils.base;

import java.util.List;

/**
 * 基本的Bean操作
 * Created by GaoFei on 2016/3/7.
 */
public interface BaseBeanService <T>{
    /**
     * 保存对象
     * @param object
     */
    void save(T object);

    /**
     * 删除对象
     * @param object
     */
    void delete(T object);

    /**
     * 更新对象
     * @param object
     */
    void update(T object);

    /**
     * 获取所有数据
     * @return
     */
    List<T> getAll(Class<T> tClass);

    /**
     * 通过主键获取对象
     * @param id
     * @return
     */
    T getObjectById(Class<T> tClass, Object id);

    /**
     * 判断某个对象是否存在
     * @param object
     * @return
     */
    boolean isExist(T object);

    /**
     * 保存对象列表
     * @param objects
     */
    void saveAll(List<T> objects);

    /**
     * 更新对象列表
     * @param objects
     */
    void updateAll(List<T> objects);

    /**
     * 保存或更新列表
     * @param objects
     */
    void saveOrUpdateAll(List<T> objects);
}
