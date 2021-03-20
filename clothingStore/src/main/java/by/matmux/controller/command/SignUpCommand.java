package by.matmux.controller.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Role;
import by.matmux.bean.User;
import by.matmux.exception.PersistentException;
import by.matmux.service.ServiceEnum;
import by.matmux.service.UserService;

public class SignUpCommand extends BaseCommand {
	private static Logger logger = LogManager.getLogger(SignUpCommand.class);

	private static Map<Role, List<MenuItem>> menu = new ConcurrentHashMap<>();

	static {
		menu.put(Role.BUYER, new ArrayList<MenuItem>(Arrays.asList(new MenuItem("/buyer/form.html", "КОРЗИНА"),
				new MenuItem("/buyer/profileUser.html", "ПРОФИЛЬ"))));
	}

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if (login != null && password != null) {
			UserService service = (UserService) factory.getService(ServiceEnum.USER);
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			user.setRole(Role.BUYER);
			HttpSession session = request.getSession();
			service.save(user);
			session.setAttribute("authorizedUser", user);
			session.setAttribute("menu", menu.get(user.getRole()));
			logger.info(String.format("user \"%s\" is logged in from %s (%s:%s)", login, request.getRemoteAddr(),
					request.getRemoteHost(), request.getRemotePort()));
			return new Forward("/index.html", true);
		} else {
			request.setAttribute("message", "Имя пользователя или пароль не опознанны");
			logger.info(String.format("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login,
					request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort()));
		}
		return null;
	}
}
