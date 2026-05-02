package lk.ijse.serenitymentalhealthsystem.dao;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(T entity) throws Exception;
}
