package by.matmux.service;

import java.util.List;

import by.matmux.bean.Type;
import by.matmux.exception.PersistentException;

public interface TypeService extends Service {
	List<Type> findAllType() throws PersistentException;
	
	Type findByIndentity(Integer id) throws PersistentException;
	
	Type findByName(String name) throws PersistentException;	
	
	void save(Type type) throws PersistentException;

	void delete(Integer identity) throws PersistentException;
}
