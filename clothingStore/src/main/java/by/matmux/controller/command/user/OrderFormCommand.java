package by.matmux.controller.command.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.User;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;

public class OrderFormCommand extends BaseCommand {
	private static Logger logger = LogManager.getLogger(OrderFormCommand.class);

	@SuppressWarnings("unchecked")
	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		String[] paramArr = request.getParameterValues("product");
		
		if (paramArr == null) {
			logger.debug("null");
		} else {
			logger.debug("not null");
		}
		
		Map<String, List<String>> idAndSizeMap = new ConcurrentHashMap<>();

		Forward forward = new Forward("/buyer/order.html", true);

		Map<String, Map<String, List<String>>> userOrder = new ConcurrentHashMap<>();

		if (request.getSession().getAttribute("userOrder") != null) {
			userOrder.putAll((Map<String, Map<String, List<String>>>) request.getSession().getAttribute("userOrder"));
		}

		User user = (User) request.getSession().getAttribute("authorizedUser");

		Cookie cookie = new Cookie("userId", user.getIdentity().toString());
		response.addCookie(cookie);

		if (paramArr != null) {
			Map<String, List<String>> tempMap = userOrder.get(user.getIdentity().toString());
			if (tempMap != null && !tempMap.isEmpty()) {
				idAndSizeMap.putAll(tempMap);
			}
			for (String s : paramArr) {
				String size = request.getParameter("sizeProduct" + s);
				if (size != null) {
					if (idAndSizeMap.containsKey(s)) {
						if (!idAndSizeMap.get(s).contains(size)) {
							idAndSizeMap.get(s).add(size);
						}
					} else {
						idAndSizeMap.put(s, new ArrayList<>(Arrays.asList(size)));
					}
				}
			}
			userOrder.put(user.getIdentity().toString(), idAndSizeMap);
			request.getSession().setAttribute("userOrder", userOrder);
		}

		return forward;
	}
}
