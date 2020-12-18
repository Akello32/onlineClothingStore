package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Discount;

public interface DiscountsDao extends AbstractDAO<Integer, Discount>{
	List<Discount> getDiscountByDate();
}
