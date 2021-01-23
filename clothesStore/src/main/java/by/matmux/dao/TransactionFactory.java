package by.matmux.dao;

import by.matmux.exception.PersistentException;

public interface TransactionFactory {
	Transaction createTransaction() throws PersistentException ;

	void close();
}
