package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Order;

public interface OrderDao extends AbstractDAO<Order> {
	List<Order> readOrdersByStatus(Boolean status);
}
