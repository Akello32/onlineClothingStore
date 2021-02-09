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

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Type;
import by.matmux.dao.ClothesDao;
import by.matmux.exception.PersistentException;

public class ClothesDaoImpl extends BaseDaoImpl implements ClothesDao {
	private static final Logger log = LogManager.getLogger(ClothesDaoImpl.class);

	@Override
	public Integer create(Clothes entity) throws PersistentException {
		String sql = "INSERT INTO `books` (`price`, `numbers`, `size`, `color`, `typeID`, `brandID`, `gender`, `image`, `name`, `description`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setBigDecimal(1, entity.getPrice());
			statement.setInt(2, entity.getNumbers());
			statement.setString(3, entity.getSize());
			statement.setString(4, entity.getColor());
			statement.setInt(5, entity.getType().getIdentity());
			statement.setInt(6, entity.getBrand().getIdentity());
			statement.setString(7, entity.getGender());
			statement.setString(8, entity.getImgPath());
			statement.setString(9, entity.getName());
			statement.setString(10, entity.getDescription());
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
	public Clothes read(Integer identity) throws PersistentException {
		String sql = "SELECT `price`, `numbers`, `size`, `color`, `typeID`, `brandID`, `gender`, `image`, `name`, `description` "
				+ "FROM `clothes` WHERE `identity` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Clothes clothes = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, identity);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				clothes = new Clothes();
				clothes.setIdentity(identity);
				clothes.setPrice(resultSet.getBigDecimal("price"));
				clothes.setNumbers(resultSet.getInt("numbers"));
				clothes.setSize(resultSet.getString("size"));
				clothes.setColor(resultSet.getString("color"));
				Type type = new Type();
				type.setIdentity(resultSet.getInt("typeID"));
				clothes.setType(type);
				Brand brand = new Brand();
				brand.setIdentity(resultSet.getInt("brandID"));
				clothes.setBrand(brand);
				clothes.setGender(resultSet.getString("gender"));
				clothes.setImgPath(resultSet.getString("image"));
				clothes.setName(resultSet.getString("name"));
				clothes.setDescription(resultSet.getString("description"));
			}
			return clothes;
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
	public void update(Clothes entity) throws PersistentException {
		String sql = "UPDATE `clothes` SET `price` = ?, `numbers` = ? "
				+ "`size` = ?, `color` = ?, `typeID` = ?, `brandID` = ?, `gender` = ?, `image` = ?, `name` = ?, `description` = ? WHERE `identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setBigDecimal(1, entity.getPrice());
			statement.setInt(2, entity.getNumbers());
			statement.setString(3, entity.getSize());
			statement.setString(4, entity.getColor());
			statement.setInt(5, entity.getType().getIdentity());
			statement.setInt(6, entity.getBrand().getIdentity());
			statement.setString(7, entity.getGender());
			statement.setString(8, entity.getImgPath());
			statement.setString(9, entity.getName());
			statement.setString(10, entity.getDescription());
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
		String sql = "DELETE FROM `clothes` WHERE`identity` = ?";
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
	public List<Clothes> readAllClothes() throws PersistentException {
		String sql = "SELECT `identity`, `price`, `numbers`, `size`, `color`, `typeID`, `brandID`, `gender`, `image`, `name`, `description`"
				+ " FROM `clothes` ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clothes = new Clothes();
				clothes.setIdentity(resultSet.getInt("identity"));
				clothes.setPrice(resultSet.getBigDecimal("price"));
				clothes.setNumbers(resultSet.getInt("numbers"));
				clothes.setSize(resultSet.getString("size"));
				clothes.setColor(resultSet.getString("color"));
				Type type = new Type();
				type.setIdentity(resultSet.getInt("typeID"));
				clothes.setType(type);
				Brand brand = new Brand();
				brand.setIdentity(resultSet.getInt("brandID"));
				clothes.setBrand(brand);
				clothes.setGender(resultSet.getString("gender"));
				clothes.setImgPath(resultSet.getString("image"));
				clothes.setName(resultSet.getString("name"));
				clothes.setDescription(resultSet.getString("description"));
				clotheList.add(clothes);
			}
			return clotheList;
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
	public List<Clothes> readClothesByBrand(Integer brand) throws PersistentException {
		String sql = "SELECT `identity`, `price`, `numbers`, `size`, `color`, `typeID`, `gender`, `image`, `name`, `description`"
				+ " FROM `clothes` WHERE `brandID` = ? ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, brand);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clothes = new Clothes();
				clothes.setIdentity(resultSet.getInt("identity"));
				clothes.setPrice(resultSet.getBigDecimal("price"));
				clothes.setNumbers(resultSet.getInt("numbers"));
				clothes.setSize(resultSet.getString("size"));
				clothes.setColor(resultSet.getString("color"));
				Type type = new Type();
				type.setIdentity(resultSet.getInt("typeID"));
				clothes.setType(type);
				Brand brands = new Brand();
				brands.setIdentity(brand);
				clothes.setBrand(brands);
				clothes.setGender(resultSet.getString("gender"));
				clothes.setImgPath(resultSet.getString("image"));
				clothes.setName(resultSet.getString("name"));
				clothes.setDescription(resultSet.getString("description"));
				clotheList.add(clothes);
			}
			return clotheList;
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
	public List<Clothes> readClothesBySize(String size) throws PersistentException {
		String sql = "SELECT `identity`, `price`, `numbers`, `color`, `typeID`, `brandID`, `gender`, `image`, `name`, `description` "
				+ "FROM `clothes` WHERE `size` = ? ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setString(1, size);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clothes = new Clothes();
				clothes.setIdentity(resultSet.getInt("identity"));
				clothes.setPrice(resultSet.getBigDecimal("price"));
				clothes.setNumbers(resultSet.getInt("numbers"));
				clothes.setSize(size);
				clothes.setColor(resultSet.getString("color"));
				Type type = new Type();
				type.setIdentity(resultSet.getInt("typeID"));
				clothes.setType(type);
				Brand brand = new Brand();
				brand.setIdentity(resultSet.getInt("brandID"));
				clothes.setBrand(brand);
				clothes.setGender(resultSet.getString("gender"));
				clothes.setImgPath(resultSet.getString("image"));
				clothes.setName(resultSet.getString("name"));
				clothes.setDescription(resultSet.getString("description"));
				clotheList.add(clothes);
			}
			return clotheList;
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
	public List<Clothes> readClothesByType(Integer typeId) throws PersistentException {
		String sql = "SELECT `identity`, `price`, `numbers`, `size`, `color`, `brandID`, `gender`, `image`, `name`, `description`"
				+ " FROM `clothes` WHERE `typeID` = ? ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, typeId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clothes = new Clothes();
				clothes.setIdentity(resultSet.getInt("identity"));
				clothes.setPrice(resultSet.getBigDecimal("price"));
				clothes.setNumbers(resultSet.getInt("numbers"));
				clothes.setSize(resultSet.getString("size"));
				clothes.setColor(resultSet.getString("color"));
				Type type = new Type();
				type.setIdentity(typeId);
				clothes.setType(type);
				Brand brand = new Brand();
				brand.setIdentity(resultSet.getInt("brandID"));
				clothes.setBrand(brand);
				clothes.setGender(resultSet.getString("gender"));
				clothes.setImgPath(resultSet.getString("image"));
				clothes.setName(resultSet.getString("name"));
				clothes.setDescription(resultSet.getString("description"));
				clotheList.add(clothes);
			}
			return clotheList;
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
	public List<Clothes> readClothesByColor(String color) throws PersistentException {
		String sql = "SELECT `identity`, `price`, `numbers`, `size`, `typeID`, `brandID`, `gender`, `image`, `name`, `description`"
				+ " FROM `clothes` WHERE `color` = ? ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setString(1, color);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clothes = new Clothes();
				clothes.setIdentity(resultSet.getInt("identity"));
				clothes.setPrice(resultSet.getBigDecimal("price"));
				clothes.setNumbers(resultSet.getInt("numbers"));
				clothes.setSize(resultSet.getString("size"));
				clothes.setColor(color);
				Type type = new Type();
				type.setIdentity(resultSet.getInt("typeID"));
				clothes.setType(type);
				Brand brand = new Brand();
				brand.setIdentity(resultSet.getInt("brandID"));
				clothes.setBrand(brand);
				clothes.setGender(resultSet.getString("gender"));
				clothes.setImgPath(resultSet.getString("image"));
				clothes.setName(resultSet.getString("name"));
				clothes.setDescription(resultSet.getString("description"));
				clotheList.add(clothes);
			}
			return clotheList;
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
	public List<Clothes> readClothesByGender(String gender) throws PersistentException {
		String sql = "SELECT `identity`, `price`, `numbers`, `size`, `color`, `typeID`, `brandID`, `image`, `name`, `description`"
				+ " FROM `clothes` WHERE `gender` = ? ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setString(1, gender);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clothes = new Clothes();
				clothes.setIdentity(resultSet.getInt("identity"));
				clothes.setPrice(resultSet.getBigDecimal("price"));
				clothes.setNumbers(resultSet.getInt("numbers"));
				clothes.setSize(resultSet.getString("size"));
				clothes.setColor(resultSet.getString("color"));
				Type type = new Type();
				type.setIdentity(resultSet.getInt("typeID"));
				clothes.setType(type);
				Brand brand = new Brand();
				brand.setIdentity(resultSet.getInt("brandID"));
				clothes.setBrand(brand);
				clothes.setGender(gender);
				clothes.setImgPath(resultSet.getString("image"));
				clothes.setName(resultSet.getString("name"));
				clothes.setDescription(resultSet.getString("description"));
				clotheList.add(clothes);
			}
			return clotheList;
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
	public List<Clothes> readClothesByNameAndColor(String name, String color) throws PersistentException {
		String sql = "SELECT `identity`, `size` FROM `clothes` WHERE `name` = ? AND `color` = ? ORDER BY `price`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Clothes clothes = null;
			List<Clothes> clotheList = new ArrayList<>();
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setString(2, color);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				clothes = new Clothes();
				clothes.setIdentity(resultSet.getInt("identity"));
				clothes.setSize(resultSet.getString("size"));
				clotheList.add(clothes);
			}
			return clotheList;
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
