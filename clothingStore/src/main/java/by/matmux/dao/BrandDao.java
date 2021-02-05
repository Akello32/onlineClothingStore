package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Brand;
import by.matmux.exception.PersistentException;

public interface BrandDao extends AbstractDAO<Brand> {
	List<Brand> readAllBrands() throws PersistentException;	
}
