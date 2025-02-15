package by.matmux.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Role;
import by.matmux.bean.User;
import by.matmux.controller.command.BaseCommand;

public class SecurityFilter implements Filter{
	private static final Logger logger = LogManager.getLogger(SecurityFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			BaseCommand command = (BaseCommand)httpRequest.getAttribute("command");
			Set<Role> allowRoles = command.getAllowRoles();
			String userName = "unauthorized user";
			HttpSession session = httpRequest.getSession(false);
			User user = null;
			if(session != null) {
				user = (User)session.getAttribute("authorizedUser");
				command.setAuthorizedUser(user);
				String errorMessage = (String)session.getAttribute("SecurityFilterMessage");
				if(errorMessage != null) {
					httpRequest.setAttribute("message", errorMessage);
					session.removeAttribute("SecurityFilterMessage");
				}
			}
			boolean canExecute = allowRoles == null;
			if(user != null) {
				userName = "\"" + user.getLogin() + "\" user";
				canExecute = canExecute || allowRoles.contains(user.getRole());
			}
			if(canExecute) {
				chain.doFilter(request, response);
			} else {
				logger.info(String.format("Trying of %s access to forbidden resource \"%s\"", userName, command.getName()));
				if(session != null && command.getClass() != BaseCommand.class) {
					session.setAttribute("SecurityFilterMessage", "Доступ запрещён");
				}
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
			}
		} else {
			logger.error("It is impossible to use HTTP filter");
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {}

}
