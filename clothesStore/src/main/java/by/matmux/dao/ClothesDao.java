package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Clothes;

public interface ClothesDao extends AbstractDAO<Integer, Clothes> {
	List<Clothes> readClothesByBrand(String brand);
	
	List<Clothes> readClothesBySize(String size);
	
	List<Clothes> readClothesByType(Integer typeId);
	
	List<Clothes> readClothesByColor(String color);

}
