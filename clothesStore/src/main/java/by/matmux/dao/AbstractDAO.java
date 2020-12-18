package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Entity;

public interface AbstractDAO<K, T extends Entity> {
	T read(Integer identity);
	
	public void delete(K id);

	public Integer create(T entity);

	public void update(T entity);
}