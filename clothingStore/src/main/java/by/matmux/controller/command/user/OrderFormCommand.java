package by.matmux.controller.command.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Order;
import by.matmux.bean.User;
import by.matmux.controller.DispatcherServlet;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;

public class OrderFormCommand extends BaseCommand {
	private static Logger logger = LogManager.getLogger(OrderFormCommand.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		String[] paramArr = request.getParameterValues("product");

		Forward forward = new Forward("/buyer/order.html", true);

		List<String> idList = new ArrayList<>();
		
		Map<String, Object> userOrder = new ConcurrentHashMap<>();

		if (request.getSession().getAttribute("userOrder") != null) {
			userOrder.putAll((Map<String, ? extends Object>) request.getSession().getAttribute("userOrder"));
		}

		if (paramArr != null) {
			idList.addAll(Arrays.asList(paramArr));
		}
		
		User user = (User) request.getSession().getAttribute("authorizedUser");

		Cookie cookie = new Cookie("userId", user.getIdentity().toString());
		response.addCookie(cookie);

		if (!idList.isEmpty()) {
			List<String> temp = (List<String>) userOrder.get(user.getIdentity().toString());
			logger.debug(user.getLogin() + " добавляет в idList ");
			if (temp != null && !temp.isEmpty()) {
				idList.addAll(temp);				
			}
			userOrder.put(user.getIdentity().toString(), idList);	
		}
		request.getSession().setAttribute("userOrder", userOrder);

		return forward;
	}
}
