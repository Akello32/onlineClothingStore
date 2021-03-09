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
import by.matmux.bean.Type;
import by.matmux.dao.TypeDao;
import by.matmux.exception.PersistentException;

public class TypeDaoImpl extends BaseDaoImpl implements TypeDao{
	private static final Logger log = LogManager.getLogger(BrandDaoImpl.class);

	@Override
	public Integer create(Type entity) throws PersistentException {
		String sql = "INSERT INTO `clothes_type` (`name`) VALUES (?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getName());			
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				log.error("There is no autoincremented index after trying to add record into table `clothes_type`");
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
	public Type read(Integer identity) throws PersistentException {
		String sql = "SELECT `name` FROM `clothes_type` WHERE `identity`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Type type = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				type = new Type();
				type.setIdentity(identity);
				type.setName(resultSet.getString("name"));
			}
			return type;
		} catch(SQLException e) {
			log.error("SQLExeption when performing a read operation");
			throw new PersistentException();
		} finally {
			try {
				resultSet.close();
			} catch(SQLException | NullPointerException e) {}
			try {
				statement.close();
			} catch(SQLException | NullPointerException e) {}
		}
	}

	@Override
	public void update(Type entity) throws PersistentException  {
		String sql = "UPDATE `clothes_type` SET `name` = ? WHERE `identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getName());
			statement.setInt(2, entity.getIdentity());		
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQLException when performing a update operation");
			throw new PersistentException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				} else {
					log.debug("null");
				}
			} catch (SQLException e) {}
		}
	}

	@Override
	public void delete(Integer id) throws PersistentException {
		String sql = "DELETE FROM `clothes_type` WHERE`identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch(SQLException e) {
			log.error("SQLException when performing a delete operation");
			throw new PersistentException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				} else {
					log.debug("null");
				}
			} catch (SQLException e) {}
		}
	}
	
	@Override
	public Type readTypeByName(String name) throws PersistentException {
		String sql = "SELECT * FROM `clothes_type` where `name` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Type type = null;
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				type = new Type();
				type.setIdentity(resultSet.getInt("identity"));
				type.setName(name);
			}
			return type;
		} catch(SQLException e) {
			log.error("SQLExeption when performing a read operation");
			throw new PersistentException();
		} finally {
			try {
				resultSet.close();
			} catch(SQLException | NullPointerException e) {}
			try {
				statement.close();
			} catch(SQLException | NullPointerException e) {}
		}
	}

	@Override
	public List<Type> readAllType() throws PersistentException {
		String sql = "SELECT `identity`, `name` FROM `clothes_type` ORDER BY `name`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Type type = null;
			List<Type> typeList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
		while(resultSet.next()) {
			type = new Type();
			type.setIdentity(resultSet.getInt("identity"));
			type.setName(resultSet.getString("name"));
			typeList.add(type);
		}
		return typeList;
	} catch(SQLException e) {
		throw new PersistentException(e);
	} finally {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {}
		try {
			if (resultSet != null) {
				statement.close();
			}
		} catch(SQLException e) {}
	}
	}
}
