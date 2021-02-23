package by.matmux.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Size;
import by.matmux.dao.SizeDao;
import by.matmux.exception.PersistentException;

public class SizeDaoImpl extends BaseDaoImpl implements SizeDao {
	private static final Logger log = LogManager.getLogger(SizeDaoImpl.class);

	@Override
	public Integer create(Size entity) throws PersistentException {
		String sql = "INSERT INTO `sizes` (`name`, `clothesId`, `number`) VALUES (?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getName());
			statement.setInt(2, entity.getClothesId());
			statement.setInt(3, entity.getNumbers());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				log.error("There is no autoincremented index after trying to add record into table `books`");
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
	public Size read(Integer identity) throws PersistentException {
		String sql = "SELECT `name`, `clothesId`, `number` FROM `sizes` WHERE `identity` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Size size = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, identity);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				size = new Size();
				size.setIdentity(identity);
				size.setClothesId(resultSet.getInt("clothesId"));
				size.setNumbers(resultSet.getInt("number"));
				size.setName(resultSet.getString("name"));
			}
			return size;
		} catch (SQLException e) {
			log.error("SQLExeption when performing a read operation");
			throw new PersistentException();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException | NullPointerException e) {
			}
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
			}
		}
	}

	@Override
	public void update(Size entity) throws PersistentException {
		String sql = "UPDATE `sizes` SET `name` = ?, `clothesId` = ?, `number` = ?  WHERE `identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getName());
			statement.setInt(2, entity.getNumbers());
			statement.setInt(3, entity.getClothesId());
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
			} catch (SQLException e) {
			}
		}
	}

	@Override
	public void delete(Integer id) throws PersistentException {
		String sql = "DELETE FROM `sizes` WHERE`identity` = ?";
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
	public List<Size> readByClothesId(int clothesId) throws PersistentException {
		String sql = "SELECT `identity`, `name`, `number` FROM `sizes` WHERE `clothesId` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, clothesId);
			resultSet = statement.executeQuery();
			List<Size> sizes = new ArrayList<>();
			while (resultSet.next()) {
				Size size = new Size();
				size = new Size();
				size.setIdentity(resultSet.getInt("identity"));
				size.setClothesId(clothesId);
				size.setNumbers(resultSet.getInt("number"));
				size.setName(resultSet.getString("name"));
				sizes.add(size);
			}
			return sizes;
		} catch (SQLException e) {
			log.error("SQLExeption when performing a read operation");
			throw new PersistentException();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException | NullPointerException e) {
			}
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
			}
		}
	}

	@Override
	public List<Size> readByName(String name) throws PersistentException {
		String sql = "SELECT `identity`, `clothesId`, `number` FROM `sizes` WHERE `name` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Size> sizes = new ArrayList<>();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Size size = new Size();
				size = new Size();
				size.setIdentity(resultSet.getInt("identity"));
				size.setClothesId(resultSet.getInt("clothesId"));
				size.setNumbers(resultSet.getInt("number"));
				size.setName(name);
				sizes.add(size);
			}
			return sizes;
		} catch (SQLException e) {
			log.error("SQLExeption when performing a read operation");
			throw new PersistentException();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException | NullPointerException e) {
			}
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
			}
		}
	}

}
