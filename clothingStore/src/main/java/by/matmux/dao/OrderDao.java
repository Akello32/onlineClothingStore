package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Order;
import by.matmux.exception.PersistentException;

public interface OrderDao extends AbstractDAO<Order> {
	List<Order> readOrdersByStatus(Boolean status);
	
	List<Order> readOrdersByUser(int id) throws PersistentException;

}
