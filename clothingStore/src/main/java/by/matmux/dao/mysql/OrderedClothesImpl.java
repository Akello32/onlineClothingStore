package by.matmux.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Clothes;
import by.matmux.bean.Order;
import by.matmux.bean.OrderedClothes;
import by.matmux.bean.Size;
import by.matmux.bean.User;
import by.matmux.dao.OrderedClothesDao;
import by.matmux.exception.PersistentException;

public class OrderedClothesImpl extends BaseDaoImpl implements OrderedClothesDao{
	private static final Logger log = LogManager.getLogger(OrderedClothesImpl.class);
	
	@Override
	public Integer create(OrderedClothes entity) throws PersistentException {
		String sql = "INSERT INTO `ordered_clothes`(`thing_id`, `size_id`, `order_id`) VALUES (?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, entity.getClothes().getIdentity());
			statement.setInt(2, entity.getSize().getIdentity());
			statement.setInt(3, entity.getOrder().getIdentity());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				log.error("There is no autoincremented index after trying to add record into table `orders`");
				throw new PersistentException();
			}
		} catch (SQLException e) {
			log.error("SQLException when performing a create operation ");
			throw new PersistentException(e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
			}
			try {
				if (statement != null) {
					statement.close();
				} else {
					log.debug("null");
				}
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public OrderedClothes read(Integer identity) throws PersistentException {
		String sql = "SELECT `thing_id` = ?, `size_id` = ?, `order_id` = ? FROM `ordered_clothes` WHERE `identity` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		OrderedClothes orderedClothes = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				orderedClothes = new OrderedClothes();
				orderedClothes.setIdentity(identity);
				Integer clothesId = resultSet.getInt("thing_id");
				if (clothesId != null) {
					Clothes clothes = new Clothes();
					clothes.setIdentity(clothesId);
					orderedClothes.setClothes(clothes);
				}
				Integer sizeId = resultSet.getInt("size_id");
				if (sizeId != null) {
					Size size = new Size();
					size.setIdentity(sizeId);
					orderedClothes.setSize(size);
				}
				Integer orderId = resultSet.getInt("order_id");
				if (orderId != null) {
					Order order = new Order();
					order.setIdentity(orderId);
					orderedClothes.setOrder(order);	
				} 
			}
			return orderedClothes;
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
		return orderedClothes;
	}

	@Override
	public void update(OrderedClothes entity) throws PersistentException {
		String sql = "UPDATE `ordered_clothes` SET `thing_id` = ?, `size_id` = ?, `order_id` = ? WHERE `identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, entity.getClothes().getIdentity());
			statement.setInt(2, entity.getSize().getIdentity());
			statement.setInt(3, entity.getOrder().getIdentity());
			statement.setInt(4, entity.getIdentity());
			statement.executeUpdate();
		} catch(SQLException e) {
			throw new PersistentException(e);
		} finally {
			try {
				statement.close();
			} catch(SQLException | NullPointerException e) {}
		}
	}

	@Override
	public void delete(Integer id) throws PersistentException {
		String sql = "DELETE FROM `ordered_clothes` WHERE`identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQLException when performing a delete operation");
			throw new PersistentException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				} else {
					log.debug("null");
				}
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public List<OrderedClothes> readOrderedClothesByOrder(Order order) throws PersistentException {
		String sql = "SELECT * FROM ordered_clothes WHERE order_id = ? ORDER BY identity";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			OrderedClothes orderedClothes = null;
			List<OrderedClothes> orderList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, order.getIdentity());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				orderedClothes = new OrderedClothes();
				orderedClothes.setIdentity(resultSet.getInt("identity"));
				Clothes clothes = new Clothes();
				clothes.setIdentity(resultSet.getInt("thing_id"));
				orderedClothes.setClothes(clothes);
				orderedClothes.setOrder(order);
				Size size = new Size();
				size.setIdentity(resultSet.getInt("size_id"));
				orderedClothes.setSize(size);
				orderList.add(orderedClothes);
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
	
	
}
