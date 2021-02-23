package by.matmux.service;

import java.util.List;

import by.matmux.bean.Size;
import by.matmux.exception.PersistentException;

public interface SizeService extends Service {
	List<Size> findByClothesId(int clothesId) throws PersistentException;
	
	Size findByIdentity(int id) throws PersistentException;
	
	List<Size> findByName(String name) throws PersistentException;
	
	void save(Size size) throws PersistentException;

	void delete(Integer identity) throws PersistentException;
}
