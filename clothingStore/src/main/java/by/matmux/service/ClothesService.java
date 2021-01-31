package by.matmux.service;

import java.util.List;

import by.matmux.bean.Clothes;
import by.matmux.exception.PersistentException;

public interface ClothesService extends Service {
	List<Clothes> findClothesByBrand(Integer brand) throws PersistentException;
	
	List<Clothes> findClothesBySize(String size) throws PersistentException;
	
	List<Clothes> findClothesByType(Integer typeId) throws PersistentException;
	
	List<Clothes> findClothesByColor(String color) throws PersistentException;
	
	Clothes findByIdentity(Integer identity) throws PersistentException;
	
	void save(Clothes clothes) throws PersistentException;

	void delete(Integer identity) throws PersistentException;
}
