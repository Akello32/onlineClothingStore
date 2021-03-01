package by.matmux.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Order;
import by.matmux.bean.Role;
import by.matmux.bean.Type;
import by.matmux.bean.User;
import by.matmux.dao.OrderDao;
import by.matmux.exception.PersistentException;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
	private static final Logger log = LogManager.getLogger(OrderDaoImpl.class);
	
	@Override
	public Integer create(Order order) {
		String sql = "INSERT INTO `orders`(`status`, `user_id`, `price`) VALUES (?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setBoolean(1, order.isStatus());
			statement.setInt(2, order.getUser().getIdentity());
			statement.setBigDecimal(3, order.getPrice());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				log.error("There is no autoincremented index after trying to add record into table `orders`");
			}
		} catch (SQLException e) {
			log.error("SQLException when performing a create operation ");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException | NullPointerException e) {
				log.error("SQLException when closing resultSet");
			}
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				log.error("SQLException when closing statement");
			}
		}
		return null;
	}

	@Override
	public Order read(Integer identity) {
		String sql = "SELECT `status` = ?, `user_id` = ?, `price` = ? FROM `orders` WHERE `identity` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Order order = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				order.setIdentity(identity);
				order.setStatus(resultSet.getBoolean("status"));
				Integer userId = resultSet.getInt("user_id");
				if (userId != null) {
					User user = new User();
					user.setIdentity(userId);
					order.setUser(user);	
				} 
			}
			return order;
		} catch (SQLException e) {
			log.error("SQLExeption when performing a read operation");
		} finally {
			try {
				resultSet.close();
			} catch (SQLException | NullPointerException e) {
				log.error("SQLException when closing resultSet");
			}
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				log.error("SQLException when closing statement");
			}
		}
		return order;
	}

	@Override
	public void update(Order order) {
		String sql = "UPDATE `status` = ?, `user_id` = ?, `price` = ? FROM `orders` WHERE `identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setBoolean(1, order.isStatus());
			statement.setInt(2, order.getUser().getIdentity());
			statement.setBigDecimal(3, order.getPrice());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQLException when performing a update operation");
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				log.error("SQLException when closing statement");
			}
		}
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM `users` WHERE`identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQLException when performing a delete operation");
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				log.error("SQLException when performing a delete operation");
				
			}
		}
	}

	@Override
	public List<Order> readOrdersByUser(int id) throws PersistentException {
		String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY identity";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Order order = null;
			List<Order> orderList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				order = new Order();
				order.setIdentity(resultSet.getInt("identity"));
				order.setPrice(resultSet.getBigDecimal("price"));
				order.setStatus(resultSet.getBoolean("status"));
				User user = new User();
				user.setIdentity(id);
				order.setUser(user);
				orderList.add(order);
			}
			return orderList;
		} catch (SQLException e) {
			throw new PersistentException(e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (resultSet != null) {
					statement.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	
	@Override
	public List<Order> readOrdersByStatus(Boolean status) {
		// TODO Auto-generated method stub
		return null;
	}

}
