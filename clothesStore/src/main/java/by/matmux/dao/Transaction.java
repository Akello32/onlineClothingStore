package by.matmux.dao;

public interface Transaction {
	<T extends AbstractDAO<?>> T createDao(Class<T> key);

	void commit();

	void rollback();
}
