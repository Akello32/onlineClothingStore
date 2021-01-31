package by.matmux.service;

import by.matmux.exception.PersistentException;

public interface ServiceFactory {
	<Type extends Service> Type getService(Class<Type> key) throws PersistentException;

	void close();
}
