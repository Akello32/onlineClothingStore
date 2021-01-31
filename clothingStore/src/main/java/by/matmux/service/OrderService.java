package by.matmux.service;

import java.util.List;

import by.matmux.bean.Order;
import by.matmux.exception.PersistentException;

public interface OrderService extends Service {
	List<Order> findOrdersByStatus(Boolean status) throws PersistentException;	
	
	void save(Order order) throws PersistentException;

	void delete(Integer identity) throws PersistentException;
}
