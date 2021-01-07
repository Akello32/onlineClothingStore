package by.matmux.dao;

public interface TransactionFactory {
	Transaction createTransaction() ;

	void close();
}
