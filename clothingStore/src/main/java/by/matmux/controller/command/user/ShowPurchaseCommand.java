package by.matmux.controller.command.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.bean.Order;
import by.matmux.bean.OrderedClothes;
import by.matmux.bean.User;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;
import by.matmux.service.OrderService;
import by.matmux.service.OrderedClothesService;
import by.matmux.service.ServiceEnum;

public class ShowPurchaseCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/buyer/profileUser.jsp");
		OrderService orderService = (OrderService) factory.getService(ServiceEnum.ORDER);

		User user = (User) request.getSession().getAttribute("authorizedUser");
		List<Order> orders = orderService.findOrdersByUser(user.getIdentity());
		List<OrderedClothes> orderedClothes = new ArrayList<OrderedClothes>();

		if (orders != null && !orders.isEmpty()) {
			OrderedClothesService oClothesService = (OrderedClothesService) factory
					.getService(ServiceEnum.ORDERED_CLOTHES);
			for (Order order : orders) {
				orderedClothes.addAll(oClothesService.findOrderedClothesByOrder(order));
			}
		}

		if (!orderedClothes.isEmpty()) {
			request.setAttribute("orderedClothes", orderedClothes);
		}
			
		return forward;
	}

}
