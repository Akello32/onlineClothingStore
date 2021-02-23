package by.matmux.controller.command.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;

public class DeleteProductFromCart extends BaseCommand  {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Cookie[] cookies = request.getCookies();
		
		Integer paramId = Integer.parseInt(request.getParameter("productId"));
		Integer sizeId = Integer.parseInt(request.getParameter("sizeId"));
		
		if (request.getSession().getAttribute("userOrder") != null) {
			@SuppressWarnings("unchecked")
			Map<String, Map<String, List<String>>> userOrder = (Map<String, Map<String, List<String>>>) request
			.getSession().getAttribute("userOrder");
			for (Cookie c : cookies) {
				if (userOrder.containsKey(c.getValue())) {
					if (userOrder.get(c.getValue()).get(paramId.toString()).size() > 1) {
						userOrder.get(c.getValue()).get(paramId.toString()).remove(sizeId.toString());						
					} else {
						userOrder.get(c.getValue()).remove(paramId.toString());
					}
					request.getSession().setAttribute("userOrder", userOrder);
					break;
				}
			}
		}
				
		return new Forward("/buyer/order.html", true);
	}
	
}




