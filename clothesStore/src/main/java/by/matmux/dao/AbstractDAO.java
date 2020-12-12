package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Entity;

public interface AbstractDAO<K, T extends Entity> {
	public abstract List<T> findAll();

	public abstract T findEntityById(K id);

	public abstract boolean delete(K id);

	public abstract boolean delete(T entity);

	public abstract boolean create(T entity);

	public abstract T update(T entity);
}