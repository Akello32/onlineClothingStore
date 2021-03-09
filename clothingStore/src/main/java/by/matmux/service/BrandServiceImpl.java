package by.matmux.service;

import java.util.List;

import by.matmux.bean.Brand;
import by.matmux.dao.BrandDao;
import by.matmux.exception.PersistentException;

public class BrandServiceImpl extends ServiceImpl implements BrandService {

	@Override
	public List<Brand> findAllBrands() throws PersistentException {
		BrandDao dao = transaction.createDao(BrandDao.class);
		return dao.readAllBrands();
	}

	@Override
	public Brand findByName(String name) throws PersistentException {
		BrandDao dao = transaction.createDao(BrandDao.class);
		return dao.readBrandByName(name);
	}

	@Override
	public Brand findByIndentity(Integer id) throws PersistentException {
		BrandDao dao = transaction.createDao(BrandDao.class);
		return dao.read(id);
	}

	@Override
	public void save(Brand brand) throws PersistentException {
		BrandDao dao = transaction.createDao(BrandDao.class);
		if (brand.getIdentity() != null) {
			dao.update(brand);
		} else {
			brand.setIdentity(dao.create(brand));
		}	
	}

	@Override
	public void delete(Integer identity) throws PersistentException {
		BrandDao dao = transaction.createDao(BrandDao.class);
		dao.delete(identity);		
	}
}
