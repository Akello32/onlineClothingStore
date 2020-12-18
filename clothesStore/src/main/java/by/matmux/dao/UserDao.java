package by.matmux.dao;

import java.util.List;

import by.matmux.bean.Role;
import by.matmux.bean.User;

public interface UserDao extends AbstractDAO<Integer, User> {
	List<User> getUsersByRole(Role role);
}
