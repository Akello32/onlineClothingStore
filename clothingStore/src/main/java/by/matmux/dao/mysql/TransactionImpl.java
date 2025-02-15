package by.matmux.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.dao.AbstractDAO;
import by.matmux.dao.BrandDao;
import by.matmux.dao.ClothesDao;
import by.matmux.dao.OrderDao;
import by.matmux.dao.OrderedClothesDao;
import by.matmux.dao.SizeDao;
import by.matmux.dao.Transaction;
import by.matmux.dao.TypeDao;
import by.matmux.dao.UserDao;
import by.matmux.exception.PersistentException;


public class TransactionImpl implements Transaction {
	private static final Logger log = LogManager.getLogger(TransactionImpl.class);

	private static Map<Class<? extends AbstractDAO<?>>, Class<? extends BaseDaoImpl>> classes = new ConcurrentHashMap<>();
	static {
		classes.put(ClothesDao.class, ClothesDaoImpl.class);
		classes.put(OrderDao.class, OrderDaoImpl.class);
		classes.put(UserDao.class, UserDaoImpl.class);
		classes.put(TypeDao.class, TypeDaoImpl.class);
		classes.put(BrandDao.class, BrandDaoImpl.class);
		classes.put(SizeDao.class, SizeDaoImpl.class);
		classes.put(OrderedClothesDao.class, OrderedClothesImpl.class);
	}

	private Connection connection;

	public TransactionImpl(Connection connection) {
		this.connection = connection;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public <Type extends AbstractDAO<?>> Type createDao(Class<Type> key) throws PersistentException {
		Class<? extends BaseDaoImpl> value = classes.get(key);
		if(value != null) {
			try {
				BaseDaoImpl dao = value.newInstance();
				dao.setConnection(connection);
				return (Type)dao;
			} catch(InstantiationException | IllegalAccessException e) {
				log.error("It is impossible to create data access object");
				throw new PersistentException(e);
			}
		}
		return null;
	}

	@Override
	public void commit() {
		try {
			connection.commit();
		} catch(SQLException e) {
			log.error("It is impossible to commit transaction");
		}
	}

	@Override
	public void rollback() {
		try {
			connection.rollback();
		} catch(SQLException e) {
			log.error("It is impossible to rollback transaction");
		}
	}
}
