package by.matmux.service;

import java.util.List;

import by.matmux.bean.Order;
import by.matmux.dao.OrderDao;
import by.matmux.exception.PersistentException;

public class OrderServiceImpl extends ServiceImpl implements OrderService {

	@Override
	public List<Order> findOrdersByStatus(Boolean status) throws PersistentException {
		OrderDao dao = transaction.createDao(OrderDao.class);
		return dao.readOrdersByStatus(status);
	}

	@Override
	public void save(Order order) throws PersistentException {
		OrderDao dao = transaction.createDao(OrderDao.class);
		if (order.getIdentity() != null) {
			dao.update(order);
		} else {
			dao.create(order);
		}
	}

	@Override
	public void delete(Integer identity) throws PersistentException {
		OrderDao dao = transaction.createDao(OrderDao.class);
		dao.delete(identity);
	}
	
}
