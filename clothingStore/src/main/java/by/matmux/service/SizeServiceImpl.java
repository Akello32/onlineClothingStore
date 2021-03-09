package by.matmux.service;

import java.util.List;

import by.matmux.bean.Size;
import by.matmux.dao.SizeDao;
import by.matmux.exception.PersistentException;

public class SizeServiceImpl extends ServiceImpl implements SizeService{

	@Override
	public List<Size> findByClothesId(int clothesId) throws PersistentException {
		SizeDao dao = transaction.createDao(SizeDao.class);
		return dao.readByClothesId(clothesId);
	}
	
	@Override
	public Size findByIdentity(int id) throws PersistentException {
		SizeDao dao = transaction.createDao(SizeDao.class);
		return dao.read(id);
	}

	@Override
	public List<Size> findByName(String name) throws PersistentException {
		SizeDao dao = transaction.createDao(SizeDao.class);
		return dao.readByName(name);
	}

	@Override
	public void save(Size size) throws PersistentException {
		SizeDao dao = transaction.createDao(SizeDao.class);
		if (size.getIdentity() != null) {
			dao.update(size);
		} else {
			size.setIdentity(dao.create(size));
		}		
	}

	@Override
	public void delete(Integer identity) throws PersistentException {
		SizeDao dao = transaction.createDao(SizeDao.class);
		dao.delete(identity);		
	}
}
