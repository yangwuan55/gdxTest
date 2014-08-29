package com.baidu.camera.template.db;

import com.baidu.camera.app.CameraApp;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by yangmengrong on 14-8-27.
 */
public class DaoManagerImpl<T> implements DaoManager<T> {

    protected DAOHelper helper;
    protected Dao dao;

    public DAOHelper getHelper() {
        return helper;
    }

    public DaoManagerImpl() {
        helper = OpenHelperManager.getHelper(CameraApp.getInstance(), DAOHelper.class);

        Class<T> entityClass = null;
        Type modelType = getClass().getGenericSuperclass();
        if ((modelType instanceof ParameterizedType))
        {
            Type[] modelTypes = ((ParameterizedType)modelType).getActualTypeArguments();
            entityClass = ((Class)modelTypes[0]);
        }
        System.out.println("entityClass = " + entityClass);
        dao = helper.getHelperDao(entityClass);
    }

    @Override
    public void close() {
        dao = null;
    }

    @Override
    public void add(T t) throws SQLException {
        dao.create(t);
    }

    @Override
    public void addAll(Collection<T> ts) throws SQLException {
        for (T t : ts) {
            add(t);
        }
    }

    @Override
    public void delete(T t) throws SQLException {
        dao.delete(t);
    }

    @Override
    public void deleteAll(Collection<T> ts) throws SQLException {
        for (T t : ts) {
            delete(t);
        }
    }

    @Override
    public void update(T t) throws SQLException {
        dao.update(t);
    }

    @Override
    public void updateALl(Collection<T> ts) throws SQLException {
        for (T t : ts) {
            update(t);
        }
    }

    @Override
    public List<T> findByKey(String key,Object value) throws SQLException {
        return dao.queryForEq(key,value);
    }

    @Override
    public List<T> findAll() throws SQLException {
        return dao.queryForAll();
    }
}
