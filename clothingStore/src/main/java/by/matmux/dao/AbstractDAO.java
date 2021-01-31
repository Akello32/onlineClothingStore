package by.matmux.dao;

import by.matmux.bean.Entity;
import by.matmux.exception.PersistentException;

public interface AbstractDAO<T extends Entity> {
	public Integer create(T entity) throws PersistentException;
	
	T read(Integer identity) throws PersistentException;
	
	public void update(T entity) throws PersistentException;
	
	public void delete(Integer id) throws PersistentException;
}