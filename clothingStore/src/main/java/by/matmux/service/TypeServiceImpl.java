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
	public Type findByIndentity(Integer id) throws PersistentException {
		TypeDao dao = transaction.createDao(TypeDao.class);
		return dao.read(id);
	}
	
}
