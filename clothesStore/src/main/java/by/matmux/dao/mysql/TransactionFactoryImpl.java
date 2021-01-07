package by.matmux.dao.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.dao.Transaction;
import by.matmux.dao.TransactionFactory;
import by.matmux.dao.pool.ConnectionPool;

public class TransactionFactoryImpl implements TransactionFactory {
	private static Logger logger = LogManager.getLogger(TransactionFactoryImpl.class);
	private Connection connection;
	
	public TransactionFactoryImpl(){
		connection = ConnectionPool.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
		} catch(SQLException e) {
			logger.error("It is impossible to turn off autocommiting for database connection", e);
		}
	}

	@Override
	public Transaction createTransaction() {
		return new TransactionImpl(connection);
	}

	@Override
	public void close() {
		try {
			connection.close();
		} catch(SQLException e) {}
	}
}