package by.matmux.controller.command;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.bean.Role;
import by.matmux.bean.User;
import by.matmux.service.ServiceFactory;

public abstract class BaseCommand implements Command{
	private Set<Role> allowRoles = new HashSet<>();
	private User authorizedUser;
	private String name;

	protected ServiceFactory factory;

	public Set<Role> getAllowRoles() {
		return allowRoles;
	}

	public void setFactory(ServiceFactory factory) {
		this.factory = factory;
	}

	public User getAuthorizedUser() {
		return authorizedUser;
	}

	public void setAuthorizedUser(User authorizedUser) {
		this.authorizedUser = authorizedUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void close() {
		factory.close();
	}	
}
