package com.baidu.camera.template.db;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by yangmengrong on 14-8-27.
 */
public interface DaoManager<T> {

    void close();

    void add(T t) throws SQLException;

    void addAll(Collection<T> ts) throws SQLException;

    void delete(T t) throws SQLException;

    void deleteAll(Collection<T> ts) throws SQLException;

    void update(T t) throws SQLException;

    void updateALl(Collection<T> ts) throws SQLException;

    List<T> findByKey(String key, Object value) throws SQLException;

    List<T> findAll() throws SQLException;

}
