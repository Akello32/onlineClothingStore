package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Role;
import by.matmux.bean.User;

public interface UserDao extends AbstractDAO<User> {
	User read(String login, String password);

	List<User> read();
}
