package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Size;
import by.matmux.exception.PersistentException;

public interface SizeDao extends AbstractDAO<Size> {
	List<Size> readByClothesId(int clothesId) throws PersistentException;
	
	List<Size> readByName(String name) throws PersistentException;
}
