package by.matmux.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.User;
import by.matmux.exception.PersistentException;

public class LogoutCommand extends BaseCommand{
	private static Logger logger = LogManager.getLogger(SignUpCommand.class);

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		User user = (User) request.getSession().getAttribute("authorizedUser");
		logger.info(String.format("user \"%s\" is logged out", user.getLogin()));
		request.getSession(false).invalidate();
		return new Forward("/index.jsp");
	}
	
}
