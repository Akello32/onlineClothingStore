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
	public Brand findByIndentity(Integer id) throws PersistentException {
		BrandDao dao = transaction.createDao(BrandDao.class);
		return dao.read(id);
	}
}
