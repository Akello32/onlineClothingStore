package by.matmux.service;

import java.util.List;

import by.matmux.bean.Order;
import by.matmux.bean.OrderedClothes;
import by.matmux.exception.PersistentException;

public interface OrderedClothesService extends Service {
	void save(OrderedClothes orderedClothes) throws PersistentException;

	List<OrderedClothes> findOrderedClothesByOrder(Order order) throws PersistentException;
	
	void delete(Integer identity) throws PersistentException;
}
