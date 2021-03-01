package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Order;
import by.matmux.bean.OrderedClothes;
import by.matmux.exception.PersistentException;

public interface OrderedClothesDao extends AbstractDAO<OrderedClothes>{
	List<OrderedClothes> readOrderedClothesByOrder(Order order) throws PersistentException;
}
