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
import by.matmux.dao.BrandDao;
import by.matmux.exception.PersistentException;

public class BrandDaoImpl extends BaseDaoImpl implements BrandDao{
	private static final Logger log = LogManager.getLogger(BrandDaoImpl.class);

	@Override
	public Integer create(Brand entity) throws PersistentException {
		String sql = "INSERT INTO `brands` ('name') VALUES (?)";
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
				log.error("There is no autoincremented index after trying to add record into table `brands`");
				throw new PersistentException();
			}
		} catch (SQLException e) {
			log.error("There ");
			throw new PersistentException(e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {}
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
	public Brand read(Integer identity) throws PersistentException {
		String sql = "SELECT `name` FROM `brands` WHERE `identity`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Brand brand = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				brand = new Brand();
				brand.setIdentity(identity);
				brand.setName(resultSet.getString("name"));
			}
			return brand;
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
	public void update(Brand entity) throws PersistentException  {
		String sql = "UPDATE `brands` SET `name` = ? WHERE `identity` = ?";
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
		String sql = "DELETE FROM `brands` WHERE`identity` = ?";
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
	public List<Brand> readAllBrands() throws PersistentException {
		String sql = "SELECT `identity`, `name` FROM `brands` ORDER BY `name`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Brand brand = null;
			List<Brand> brandList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
		while(resultSet.next()) {
			brand = new Brand();
			brand.setIdentity(resultSet.getInt("identity"));
			brand.setName(resultSet.getString("name"));
			brandList.add(brand);
		}
		return brandList;
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
