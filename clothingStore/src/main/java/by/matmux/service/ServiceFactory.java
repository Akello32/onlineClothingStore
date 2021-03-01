package by.matmux.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.dao.Transaction;
import by.matmux.dao.TransactionFactory;
import by.matmux.dao.mysql.TransactionFactoryImpl;
import by.matmux.exception.PersistentException;

public class ServiceFactory {
	private static Logger logger = LogManager.getLogger(ServiceFactory.class);
	
	private TransactionFactory factory;

	public ServiceFactory() throws PersistentException {
		this.factory = new TransactionFactoryImpl();
	}

	public ServiceImpl getService(ServiceEnum serviceType) throws PersistentException {
		ServiceImpl service = null;
		switch (serviceType) {
		case USER:
			service = new UserServiceImpl();
			break;
		case ORDER:
			service = new OrderServiceImpl();
			break;
		case CLOTHES:
			service = new ClothesServiceImpl();
			break;
		case TYPE:
			service = new TypeServiceImpl();
			break;
		case BRAND:
			service = new BrandServiceImpl();
			break;
		case SIZE:
			service = new SizeServiceImpl();
			break;
		case ORDERED_CLOTHES:
			service = new OrderedClothesServiceImpl();
			break;
		}
		Transaction transaction = factory.createTransaction();
		service.setTransaction(transaction);
		return service;
	}	

	public void close() {
		factory.close();
	}

}
