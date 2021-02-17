package by.matmux.controller.command.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Order;
import by.matmux.bean.Type;
import by.matmux.bean.User;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.controller.command.guest.catalog.CatalogHelpersMethods;
import by.matmux.exception.PersistentException;
import by.matmux.service.BrandService;
import by.matmux.service.ClothesService;
import by.matmux.service.OrderService;
import by.matmux.service.ServiceEnum;
import by.matmux.service.TypeService;

public class OrderCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		List<Clothes> clothes = new ArrayList<>();
		List<String> idArr = new ArrayList<>();

		Cookie[] cookies = request.getCookies();
		
		Order order = new Order();
		User user = (User) request.getSession().getAttribute("authorizedUser");
		order.setUser(user);
		order.setStatus(false);

		if (request.getSession().getAttribute("userOrder") != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> userOrder = (Map<String, Object>) request.getSession().getAttribute("userOrder");
			for (Cookie c : cookies) {
				if (userOrder.containsKey(c.getValue())) {
					idArr = (List<String>) userOrder.get(c.getValue());
				}
			}
		}

		ClothesService service = (ClothesService) factory.getService(ServiceEnum.CLOTHES);

		if (idArr != null) {
			for (String s : idArr) {
				clothes.add(service.findByIdentity(Integer.parseInt(s)));
			}
		}
		
		BrandService brandService = (BrandService) factory.getService(ServiceEnum.BRAND);
		List<Brand> brands = brandService.findAllBrands();

		TypeService typeService = (TypeService) factory.getService(ServiceEnum.TYPE);
		List<Type> types = typeService.findAllType();

		if (!clothes.isEmpty()) {
			CatalogHelpersMethods.addTypeAndBrand(clothes, brands, types);
			request.setAttribute("order", order);
			request.setAttribute("products", clothes);
		}

		return null;
	}

}