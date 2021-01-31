package by.matmux.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Clothes;
import by.matmux.dao.ClothesDao;
import by.matmux.exception.PersistentException;

public class ClothesDaoImpl extends BaseDaoImpl implements ClothesDao {
	private static final Logger log = LogManager.getLogger(ClothesDaoImpl.class);

	@Override
	public Integer create(Clothes entity) throws PersistentException {
		String sql = "INSERT INTO `books` (`price`, `numbers`, `size`, `color`, `typeID`, `brandID`) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setBigDecimal(1, entity.getPrice());
			statement.setInt(2, entity.getNumbers());
			statement.setString(3, entity.getSize());
			statement.setString(4, entity.getColor());
			statement.setInt(5, entity.getTypeId());
			statement.setInt(6, entity.getBrandId());
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
	public Clothes read(Integer identity) throws PersistentException {
		String sql = "SELECT `price`, `numbers`, "
				+ "`size`, `color`, `typeID`, `brandID`, FROM `clothes` WHERE `identity`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Clothes clothes = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				clothes = new Clothes();
				clothes.setIdentity(identity);
				clothes.setPrice(resultSet.getBigDecimal("price"));
				clothes.setNumbers(resultSet.getInt("numbers"));
				clothes.setSize(resultSet.getString("size"));
				clothes.setColor(resultSet.getString("color"));
				clothes.setTypeId(resultSet.getInt("typeID"));
				clothes.setBrandId(resultSet.getInt("brandID"));
			}
			return clothes;
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
	public void update(Clothes entity) throws PersistentException  {
		String sql = "UPDATE `clothes` SET `price` = ?, `numbers` = ?, "
				+ "`size` = ?, `color` = ?, `typeID` = ?, `brandID` = ?, WHERE `identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setBigDecimal(1, entity.getPrice());
			statement.setInt(2, entity.getNumbers());
			statement.setString(3, entity.getSize());
			statement.setString(4, entity.getColor());
			statement.setInt(5, entity.getTypeId());
			statement.setInt(6, entity.getBrandId());
			statement.setInt(7, entity.getIdentity());
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
		String sql = "DELETE FROM `clothes` WHERE`identity` = ?";
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
	public List<Clothes> readClothesByBrand(Integer brand) throws PersistentException {
		String sql = "SELECT `identity`, `price`, `numbers`, `size`, `color`, `typeID`"
				+ " FROM `clothes` WHERE `brandID` = ? ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, brand);
			resultSet = statement.executeQuery();
		while(resultSet.next()) {
			clothes = new Clothes();
			clothes.setIdentity(resultSet.getInt("identity"));
			clothes.setPrice(resultSet.getBigDecimal("price"));
			clothes.setNumbers(resultSet.getInt("numbers"));
			clothes.setSize(resultSet.getString("size"));
			clothes.setColor(resultSet.getString("color"));
			clothes.setTypeId(resultSet.getInt("typeID"));
			clothes.setBrandId(brand);
			clotheList.add(clothes);
		}
		return clotheList;
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

	@Override
	public List<Clothes> readClothesBySize(String size) throws PersistentException {
		String sql = "SELECT `identity`, `price`, `numbers`, `color`, `typeID`, `brandID` "
				+ "FROM `clothes` WHERE `size` = ? ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);	
			statement.setString(1, size);
			resultSet = statement.executeQuery();
		while(resultSet.next()) {
			clothes = new Clothes();
			clothes.setIdentity(resultSet.getInt("identity"));
			clothes.setPrice(resultSet.getBigDecimal("price"));
			clothes.setNumbers(resultSet.getInt("numbers"));
			clothes.setSize(size);
			clothes.setColor(resultSet.getString("color"));
			clothes.setTypeId(resultSet.getInt("typeID"));
			clothes.setBrandId(resultSet.getInt("brandID"));
			clotheList.add(clothes);
		}
		return clotheList;
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

	@Override
	public List<Clothes> readClothesByType(Integer typeId) throws PersistentException {
		String sql = "SELECT `identity`, `price`, `numbers`, `size`, `color`, `typeID`, `brandID`,"
				+ " FROM `clothes` WHERE `typeID` = ? ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);	
			resultSet = statement.executeQuery();
		while(resultSet.next()) {
			clothes = new Clothes();
			clothes.setIdentity(resultSet.getInt("identity"));
			clothes.setPrice(resultSet.getBigDecimal("price"));
			clothes.setNumbers(resultSet.getInt("numbers"));
			clothes.setSize(resultSet.getString("size"));
			clothes.setColor(resultSet.getString("color"));
			clothes.setTypeId(resultSet.getInt("typeID"));
			clothes.setBrandId(resultSet.getInt("brandID"));
			clotheList.add(clothes);
		}
		return clotheList;
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

	@Override
	public List<Clothes> readClothesByColor(String color) throws PersistentException {
		String sql = "SELECT `identity`, `price`, `numbers`, `size`, `color`, `typeID`, `brandID`,"
				+ " FROM `clothes` WHERE `color` = ? ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);	
			statement.setString(1, "%" + color + "%");
			resultSet = statement.executeQuery();
		while(resultSet.next()) {
			clothes = new Clothes();
			clothes.setIdentity(resultSet.getInt("identity"));
			clothes.setPrice(resultSet.getBigDecimal("price"));
			clothes.setNumbers(resultSet.getInt("numbers"));
			clothes.setSize(resultSet.getString("size"));
			clothes.setColor(resultSet.getString("color"));
			clothes.setTypeId(resultSet.getInt("typeID"));
			clothes.setBrandId(resultSet.getInt("brandID"));
			clotheList.add(clothes);
		}
		return clotheList;
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
