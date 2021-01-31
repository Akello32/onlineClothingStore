package by.matmux.service;

import java.util.List;

import by.matmux.bean.Clothes;
import by.matmux.bean.User;
import by.matmux.exception.PersistentException;

public interface UserService extends Service {
	User findByLoginAndPassword(String login, String password) throws PersistentException;

	List<User> findAll() throws PersistentException; 
	
	User findByIdentity(Integer identity) throws PersistentException;
	
	void save(User user) throws PersistentException;

	void delete(Integer identity) throws PersistentException;
}
