package by.matmux.service;

import java.util.List;

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Type;
import by.matmux.dao.BrandDao;
import by.matmux.dao.ClothesDao;
import by.matmux.dao.TypeDao;
import by.matmux.exception.PersistentException;

public class ClothesServiceImpl extends ServiceImpl implements ClothesService {
	@Override
	public List<Clothes> findAllClothes() throws PersistentException {
		ClothesDao dao = transaction.createDao(ClothesDao.class);
		return dao.readAllClothes();
	}

	@Override
	public List<Clothes> findClothesByBrand(Integer brand) throws PersistentException {
		ClothesDao dao = transaction.createDao(ClothesDao.class);
		return dao.readClothesByBrand(brand);
	}

	@Override
	public List<Clothes> findClothesBySize(String size) throws PersistentException {
		ClothesDao dao = transaction.createDao(ClothesDao.class);
		return dao.readClothesBySize(size);
	}

	@Override
	public List<Clothes> findClothesByType(Integer typeId) throws PersistentException {
		ClothesDao dao = transaction.createDao(ClothesDao.class);
		return dao.readClothesByType(typeId);
	}

	@Override
	public List<Clothes> findClothesByColor(String color) throws PersistentException {
		ClothesDao dao = transaction.createDao(ClothesDao.class);
		return dao.readClothesByColor(color);
	}
	
	@Override
	public List<Clothes> findClothesByGender(String gender) throws PersistentException {
		ClothesDao dao = transaction.createDao(ClothesDao.class);
		return dao.readClothesByGender(gender);
	}

	@Override
	public Clothes findByIdentity(Integer identity) throws PersistentException {
		ClothesDao dao = transaction.createDao(ClothesDao.class);
		return dao.read(identity);
	}
	
	@Override
	public List<Clothes> findClothesByNameAndColor(String name, String color) throws PersistentException {
		ClothesDao dao = transaction.createDao(ClothesDao.class);
		return dao.readClothesByNameAndColor(name, color);
	}

	@Override
	public void save(Clothes clothes) throws PersistentException {
		ClothesDao dao = transaction.createDao(ClothesDao.class);
		if (clothes.getIdentity() != null) {
			dao.update(clothes);
		} else {
			dao.create(clothes);
		}
	}

	@Override
	public void delete(Integer identity) throws PersistentException {
		ClothesDao dao = transaction.createDao(ClothesDao.class);
		dao.delete(identity);
	}
}
