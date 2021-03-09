package by.matmux.service;

import java.util.List;

import by.matmux.bean.Type;
import by.matmux.dao.TypeDao;
import by.matmux.exception.PersistentException;

public class TypeServiceImpl extends ServiceImpl implements TypeService {

	@Override
	public List<Type> findAllType() throws PersistentException {
		TypeDao dao = transaction.createDao(TypeDao.class);
		return dao.readAllType();
	}

	@Override
	public Type findByName(String name) throws PersistentException {
		TypeDao dao = transaction.createDao(TypeDao.class);
		return dao.readTypeByName(name);
	}
	
	@Override
	public Type findByIndentity(Integer id) throws PersistentException {
		TypeDao dao = transaction.createDao(TypeDao.class);
		return dao.read(id);
	}

	@Override
	public void save(Type type) throws PersistentException {
		TypeDao dao = transaction.createDao(TypeDao.class);
		if (type.getIdentity() != null) {
			dao.update(type);
		} else {
			type.setIdentity(dao.create(type));
		}	
	}

	@Override
	public void delete(Integer identity) throws PersistentException {
		TypeDao dao = transaction.createDao(TypeDao.class);
		dao.delete(identity);
	}
}
