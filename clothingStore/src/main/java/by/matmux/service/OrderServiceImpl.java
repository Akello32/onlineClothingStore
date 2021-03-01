package by.matmux.service;

import java.util.List;

import by.matmux.bean.Order;
import by.matmux.dao.OrderDao;
import by.matmux.exception.PersistentException;

public class OrderServiceImpl extends ServiceImpl implements OrderService {

	@Override
	public Order findById(int id) throws PersistentException {
		OrderDao dao = transaction.createDao(OrderDao.class);
		return dao.read(id);
	}

	@Override
	public List<Order> findOrdersByStatus(Boolean status) throws PersistentException {
		OrderDao dao = transaction.createDao(OrderDao.class);
		return dao.readOrdersByStatus(status);
	}

	@Override
	public List<Order> findOrdersByUser(int id) throws PersistentException {
		OrderDao dao = transaction.createDao(OrderDao.class);
		return dao.readOrdersByUser(id);
	}

	@Override
	public void save(Order order) throws PersistentException {
		OrderDao dao = transaction.createDao(OrderDao.class);
		if (order.getIdentity() != null) {
			dao.update(order);
		} else {
			order.setIdentity(dao.create(order));
		}
	}

	@Override
	public void delete(Integer identity) throws PersistentException {
		OrderDao dao = transaction.createDao(OrderDao.class);
		dao.delete(identity);
	}

}
