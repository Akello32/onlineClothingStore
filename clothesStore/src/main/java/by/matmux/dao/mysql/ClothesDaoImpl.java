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

public class ClothesDaoImpl extends BaseDaoImpl implements ClothesDao {
	private static final Logger log = LogManager.getLogger(ClothesDaoImpl.class);

	@Override
	public Integer create(Clothes entity) {
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
			}
		} catch (SQLException e) {
			log.error("There ");
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
		return null;
	}

	@Override
	public Clothes read(Integer identity) {
		String sql = "SELECT `price` = ?, `numbers` = ?, "
				+ "`size` = ?, `color` = ?, `typeID` = ?, `brandID` = ?, FROM `clothes` WHERE `identity` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Clothes clothes = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
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
			
		} finally {
			try {
				resultSet.close();
			} catch(SQLException | NullPointerException e) {}
			try {
				statement.close();
			} catch(SQLException | NullPointerException e) {}
		}
		return clothes;
	}

	@Override
	public void update(Clothes entity) {
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
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
			}
		}
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM `clothes` WHERE`identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch(SQLException e) {
			log.error("SQLException when performing a delete operation");
		} finally {
			try {
				statement.close();
			} catch(SQLException | NullPointerException e) {}
		}
	}

	@Override
	public List<Clothes> readClothesByBrand(String brand) {
		String sql = "SELECT `price` = ?, `numbers` = ?, "
				+ "`size` = ?, `color` = ?, `typeID` = ?, `brandID` = ?, WHERE `brand`, LIKE ?, ORDER BY `brand`" ;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);	
			statement.setString(1, "%" + brand + "%");
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
		log.error("SQLException when performing a read clothes by brand operation");
	} finally {
		try {
			resultSet.close();
		} catch(SQLException | NullPointerException e) {}
		try {
			statement.close();
		} catch(SQLException | NullPointerException e) {}
	}
		return null;
	}

	@Override
	public List<Clothes> readClothesBySize(String size) {
		String sql = "SELECT `price` = ?, `numbers` = ?, "
				+ "`size` = ?, `color` = ?, `typeID` = ?, `brandID` = ?, WHERE `size`, LIKE ?, ORDER BY `size`" ;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);	
			statement.setString(1, "%" + size + "%");
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
		log.error("SQLException when performing a read clothes by brand operation");
	} finally {
		try {
			resultSet.close();
		} catch(SQLException | NullPointerException e) {}
		try {
			statement.close();
		} catch(SQLException | NullPointerException e) {}
	}
		return null;
	}

	@Override
	public List<Clothes> readClothesByType(Integer typeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Clothes> readClothesByColor(String color) {
		// TODO Auto-generated method stub
		return null;
	}
}
