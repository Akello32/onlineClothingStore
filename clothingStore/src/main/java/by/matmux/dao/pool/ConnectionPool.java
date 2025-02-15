package by.matmux.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.exception.PersistentException;

public final class ConnectionPool {
	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

	private Lock lock = new ReentrantLock();
	private String url;
	private String user;
	private String password;
	private int maxSize;
	private int checkConnectionTimeout;

	private BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
	private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

	private ConnectionPool() {
	}

	public Connection getConnection() throws PersistentException {
		PooledConnection connection = null;
		while (connection == null) {
			lock.lock();
			try {
				if (!freeConnections.isEmpty()) {
					connection = freeConnections.take();
					if (!connection.isValid(checkConnectionTimeout)) {
						try {
							connection.getConnection().close();
						} catch (SQLException e) {
							
						}
						connection = null;
					}
				} else if (usedConnections.size() < maxSize) {
					connection = createConnection();
				} else {
					logger.debug("The limit of number of database connections is exceeded");
				}
			} catch (InterruptedException | SQLException e) {
				logger.error("It is impossible to connect to a database", e);
				throw new PersistentException(e);
			} finally {
				lock.unlock();
			}
		}
		usedConnections.add(connection);
		logger.debug(String.format(
				"Connection was received from pool. Current pool size: %d used connections; %d free connection",
				usedConnections.size(), freeConnections.size()));
		return connection;
	}

	 void freeConnection(PooledConnection connection) {
		try {
			lock.lock();
			if (connection.isValid(checkConnectionTimeout)) {
				connection.clearWarnings();
				connection.setAutoCommit(true);
				usedConnections.remove(connection);
				freeConnections.put(connection);
				logger.debug(String.format(
						"Connection was returned into pool. Current pool size: %d used connections; %d free connection",
						usedConnections.size(), freeConnections.size()));
			}
		} catch (SQLException | InterruptedException e1) {
			logger.warn("It is impossible to return database connection into pool", e1);
			try {
				connection.getConnection().close();
			} catch (SQLException e2) {
				logger.error("");
			}
		} finally {
			lock.unlock();
		}
	}

	public void init(String driverClass, String url, String user, String password, int startSize,
			int maxSize, int checkConnectionTimeout) throws PersistentException {
		try {
			lock.lock();
			destroy();
			Class.forName(driverClass);
			this.url = url;
			this.user = user;
			this.password = password;
			this.maxSize = maxSize;
			this.checkConnectionTimeout = checkConnectionTimeout;
			for (int counter = 0; counter < startSize; counter++) {
				freeConnections.put(createConnection());
			}
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			logger.fatal("It is impossible to initialize connection pool", e);
			throw new PersistentException(e);
		} finally {
			lock.unlock();
		}
	}

	private static ConnectionPool instance = new ConnectionPool();

	public static ConnectionPool getInstance() {
		return instance;
	}

	private PooledConnection createConnection() throws SQLException {
		return new PooledConnection(DriverManager.getConnection(url, user, password));
	}

	public void destroy() {
		usedConnections.addAll(freeConnections);
		freeConnections.clear();
		for (PooledConnection connection : usedConnections) {
			try {
				lock.lock();
				connection.getConnection().close();
			} catch (SQLException e) {
				logger.fatal("It is impossible to destroy connection pool");
			} finally {
				lock.unlock();
			}
		}
		usedConnections.clear();
	}
}
