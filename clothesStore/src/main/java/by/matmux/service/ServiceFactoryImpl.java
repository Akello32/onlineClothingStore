package by.matmux.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.dao.Transaction;
import by.matmux.dao.TransactionFactory;
import by.matmux.exception.PersistentException;

public class ServiceFactoryImpl implements ServiceFactory {
	private static Logger logger = LogManager.getLogger(ServiceFactoryImpl.class);

	private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

	static {
		SERVICES.put(ClothesService.class, ClothesServiceImpl.class);
		SERVICES.put(OrderService.class, OrderServiceImpl.class);
		SERVICES.put(UserService.class, UserServiceImpl.class);
	}

	private TransactionFactory factory;

	public ServiceFactoryImpl(TransactionFactory factory) {
		this.factory = factory;
	}

	@Override
	public <Type extends Service> Type getService(Class<Type> key) throws PersistentException {
		Class<? extends ServiceImpl> value = SERVICES.get(key);
		if (value != null) {
			try {
				ClassLoader classLoader = value.getClassLoader();
				Class<?>[] interfaces = { key };
				Transaction transaction = factory.createTransaction();
				@SuppressWarnings("deprecation")
				ServiceImpl service = value.newInstance();
				service.setTransaction(transaction);
				InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
				return (Type) Proxy.newProxyInstance(classLoader, interfaces, handler);
			} catch (InstantiationException | IllegalAccessException e) {
				logger.error("It is impossible to instance service class", e);
				throw new PersistentException(e);
			}

		}
		return null;
	}

	@Override
	public void close() {
		factory.close();
	}

}
