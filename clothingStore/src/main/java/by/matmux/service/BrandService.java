package by.matmux.service;

import java.util.List;

import by.matmux.bean.Brand;
import by.matmux.exception.PersistentException;

public interface BrandService extends Service {
	List<Brand> findAllBrands() throws PersistentException;
	
	Brand findByIndentity(Integer id) throws PersistentException;
	
	Brand findByName(String name) throws PersistentException;
	
	void save(Brand brand) throws PersistentException;

	void delete(Integer identity) throws PersistentException;
}
