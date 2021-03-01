package by.matmux.service;

import java.util.List;

import by.matmux.bean.Clothes;
import by.matmux.bean.Order;
import by.matmux.bean.OrderedClothes;
import by.matmux.bean.Size;
import by.matmux.dao.ClothesDao;
import by.matmux.dao.OrderedClothesDao;
import by.matmux.dao.SizeDao;
import by.matmux.exception.PersistentException;

public class OrderedClothesServiceImpl extends ServiceImpl implements OrderedClothesService {

	@Override
	public void save(OrderedClothes orderedClothes) throws PersistentException {
		OrderedClothesDao dao = transaction.createDao(OrderedClothesDao.class);
		if (orderedClothes.getIdentity() != null) {
			dao.update(orderedClothes);
		} else {
			orderedClothes.setIdentity(dao.create(orderedClothes));
		}		
	}

	@Override
	public List<OrderedClothes> findOrderedClothesByOrder(Order order) throws PersistentException {
		OrderedClothesDao dao = transaction.createDao(OrderedClothesDao.class);
		List<OrderedClothes> orderedClothes = dao.readOrderedClothesByOrder(order);
		buildOrderedClothes(orderedClothes);
		return orderedClothes;
	}

	@Override
	public void delete(Integer identity) throws PersistentException {
		OrderedClothesDao dao = transaction.createDao(OrderedClothesDao.class);
		dao.delete(identity);		
	}
	
	
	private void buildOrderedClothes(List<OrderedClothes> orderedClothes) throws PersistentException {
		ClothesDao clothesDao = transaction.createDao(ClothesDao.class);
		SizeDao sizeDao = transaction.createDao(SizeDao.class);
		for (OrderedClothes o : orderedClothes) {
			Clothes clothes = o.getClothes();
			if (clothes != null) {
				clothes = clothesDao.read(clothes.getIdentity());
				o.setClothes(clothes);
			}
			Size size = o.getSize();
			if (size != null) {
				size = sizeDao.read(size.getIdentity());
				o.setSize(size);
			}
		}
	}
}
