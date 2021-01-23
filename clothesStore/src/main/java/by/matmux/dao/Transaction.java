package by.matmux.dao;

import by.matmux.exception.PersistentException;

public interface Transaction {
	<T extends AbstractDAO<?>> T createDao(Class<T> key) throws PersistentException;

	void commit();

	void rollback();
}
