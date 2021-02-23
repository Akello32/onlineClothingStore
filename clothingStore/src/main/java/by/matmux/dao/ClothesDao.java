package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Clothes;
import by.matmux.exception.PersistentException;

public interface ClothesDao extends AbstractDAO<Clothes> {
	List<Clothes> readNextPageClothes(int number) throws PersistentException;	
	
	List<Clothes> readPrevPageClothes(int number) throws PersistentException;	

	List<Clothes> readLastPageClothes() throws PersistentException;	

//	List<Clothes> readClothesByNameAndColor(String name, String color) throws PersistentException;
	
	List<Clothes> readClothesByBrand(Integer brand) throws PersistentException;
	
//	List<Clothes> readClothesBySize(String size) throws PersistentException;
	
	List<Clothes> readClothesByType(Integer typeId) throws PersistentException;
	
	List<Clothes> readClothesByColor(String color) throws PersistentException;
	
	List<Clothes> readClothesByGender(String gender) throws PersistentException;

}
