package lk.ijse.serenitymentalhealthsystem.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    List<T> getAll() throws Exception;

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(Long id) throws Exception;

    String getLastId() throws Exception;
}
