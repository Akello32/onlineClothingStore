package by.matmux.controller.command.administrator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Role;
import by.matmux.bean.User;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;
import by.matmux.service.ServiceEnum;
import by.matmux.service.UserService;

public class AddManagerCommand extends BaseCommand {
	private static final Logger logger = LogManager.getLogger(AddManagerCommand.class);
	
	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws PersistentException, IOException, ServletException {
		Forward forward = new Forward("/admin/profileAdmin.html", true);
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		if (login != null && password != null) {
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			user.setRole(Role.MANAGER);
			
			UserService userService = (UserService) factory.getService(ServiceEnum.USER);
			userService.save(user);
			
			logger.debug("User added successfully");
			forward.getAttributes().put("result", "Менеджер успешно добавлен");
			
			return forward;
		}
		
		return null;
	}
}
