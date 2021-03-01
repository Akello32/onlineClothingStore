package by.matmux.controller.command.user;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.bean.Clothes;
import by.matmux.bean.Order;
import by.matmux.bean.OrderedClothes;
import by.matmux.bean.Size;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;
import by.matmux.service.OrderService;
import by.matmux.service.OrderedClothesService;
import by.matmux.service.ServiceEnum;
import by.matmux.service.SizeService;

public class PaymentOrderCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		String priceOrder = (String) request.getParameter("priceOrder");
		String[] orderedClothesArr = request.getParameterValues("orderedClothes");
		String[] sizeOClothes = request.getParameterValues("sizeOrderedClothes");
		
		Order order = (Order) request.getSession().getAttribute("order");
		Forward forward = new Forward("/buyer/order.html", true);
		
		if (priceOrder != null && orderedClothesArr != null && order != null) {

			order.setPrice(new BigDecimal(priceOrder));
			order.setStatus(true);
			OrderService orderService = (OrderService) factory.getService(ServiceEnum.ORDER);
			orderService.save(order);
					
			
			for (String s : orderedClothesArr) {
				String clothesNumber = (String) request.getParameter("numbersClothes" + s);
				SizeService sizeService = (SizeService) factory.getService(ServiceEnum.SIZE);
				
				OrderedClothesService oClothesService = (OrderedClothesService) factory
						.getService(ServiceEnum.ORDERED_CLOTHES);
				
				for (String t : sizeOClothes) {
					Size size = sizeService.findByIdentity(Integer.parseInt(t));
					if (size.getClothesId() == Integer.parseInt(s)) {
						size.setNumbers(size.getNumbers() - Integer.parseInt(clothesNumber));
						OrderedClothes oClothes = new OrderedClothes();
						Clothes clothes = new Clothes();
						clothes.setIdentity(Integer.parseInt(s));
						oClothes.setClothes(clothes);
						oClothes.setOrder(order);
						oClothes.setSize(size);
						sizeService.save(size);
						oClothesService.save(oClothes);
					}
				}				
			}
			
			request.getSession().removeAttribute("userOrder");
			request.getSession().removeAttribute("order");
			forward.getAttributes().put("resultOrder", "Заказ оплачен) Спасибо за покупку!");
		} else {
			forward.getAttributes().put("resultOrder", "Сначала выберите товар!");
		}

		return forward;
	}

}
