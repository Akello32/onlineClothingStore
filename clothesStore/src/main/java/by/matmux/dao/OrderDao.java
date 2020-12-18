package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Order;

public interface OrderDao extends AbstractDAO<Integer, Order> {
	List<Order> getOrdersByStatus(Boolean status);
}
