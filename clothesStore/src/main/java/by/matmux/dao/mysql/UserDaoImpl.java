package by.matmux.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Role;
import by.matmux.bean.User;
import by.matmux.dao.UserDao;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	private static final Logger log = LogManager.getLogger(UserDaoImpl.class);
	
	@Override
	public Integer create(User user) {
		String sql = "INSERT INTO `users`(`login`, `password`, `role`) VALUES (?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getRole().getIdentity());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			} else {
				log.error("There is no autoincremented index after trying to add record into table `users`");
			}
		} catch (SQLException e) {
			log.error("SQLException when performing a create operation ");
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
	public User read(Integer identity) {
		String sql = "SELECT `login` = ?, `password` = ?, `role` = ?, FROM `users` WHERE `identity` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setIdentity(identity);
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(Role.getByIdentity(resultSet.getInt("role")));
			}
			return user;
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
		return user;
	}

	@Override
	public void update(User user) {
		String sql = "UPDATE `users` SET `login` = ?, `password` = ?, `role` = ?, FROM `users` WHERE `identity` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getRole().getIdentity());
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
		String sql = "DELETE FROM `users` WHERE`identity` = ?";
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
	public User read(String login, String password) {
		String sql = "SELECT `identity`, `role` FROM `users` WHERE `login` = ? AND `password` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			User user = null;
			if(resultSet.next()) {
				user = new User();
				user.setIdentity(resultSet.getInt("identity"));
				user.setLogin(login);
				user.setPassword(password);
				user.setRole(Role.getByIdentity(resultSet.getInt("role")));
			}
			return user;
		} catch(SQLException e) {
			
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
	public List<User> read() {
		String sql = "SELECT `identity`, `login`, `password`, `role` FROM `users` ORDER BY `login`";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			List<User> users = new ArrayList<>();
			User user = null;
			while(resultSet.next()) {
				user = new User();
				user.setIdentity(resultSet.getInt("identity"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(Role.getByIdentity(resultSet.getInt("role")));
				users.add(user);
			}
			return users;
		} catch(SQLException e) {
			
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
}
