package by.matmux.service;

import java.util.List;

import by.matmux.bean.Brand;
import by.matmux.exception.PersistentException;

public interface BrandService extends Service {
	List<Brand> findAllBrands() throws PersistentException;
	
	Brand findByIndentity(Integer id) throws PersistentException;
}
