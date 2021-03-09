package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Type;
import by.matmux.exception.PersistentException;

public interface TypeDao extends AbstractDAO<Type> {
	List<Type> readAllType() throws PersistentException;	

	Type readTypeByName(String name) throws PersistentException;	
}
