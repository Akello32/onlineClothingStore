package by.matmux.controller.command.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Order;
import by.matmux.bean.Size;
import by.matmux.bean.Type;
import by.matmux.bean.User;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.controller.command.guest.catalog.CatalogHelpersMethods;
import by.matmux.exception.PersistentException;
import by.matmux.service.BrandService;
import by.matmux.service.ClothesService;
import by.matmux.service.ServiceEnum;
import by.matmux.service.SizeService;
import by.matmux.service.TypeService;

public class OrderCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		List<Clothes> clothes = new ArrayList<>();
		Map<String, List<String>> idMap = new ConcurrentHashMap();

		Cookie[] cookies = request.getCookies();

		Order order = new Order();
		User user = (User) request.getSession().getAttribute("authorizedUser");
		order.setUser(user);
		order.setStatus(false);

		if (request.getSession().getAttribute("userOrder") != null) {
			@SuppressWarnings("unchecked")
			Map<String, Map<String, List<String>>> userOrder = (Map<String, Map<String, List<String>>>) request
					.getSession().getAttribute("userOrder");
			for (Cookie c : cookies) {
				if (userOrder.containsKey(c.getValue())) {
					idMap = userOrder.get(c.getValue());
					break;
				}
			}
		}

		ClothesService service = (ClothesService) factory.getService(ServiceEnum.CLOTHES);
		SizeService sizeService = (SizeService) factory.getService(ServiceEnum.SIZE);
		
		if (idMap != null) {
			for (Map.Entry entry : idMap.entrySet()) {
				List<String> sizeIdList = (List<String>) entry.getValue();
				if (sizeIdList.size() > 1) {
					for (String s : sizeIdList) {
						Size size = sizeService.findByIdentity(Integer.parseInt(s));
						Clothes c = service.findByIdentity(size.getClothesId());
						c.getSizes().clear();
						c.getSizes().add(size);
						clothes.add(c);
					}
				} else {
					Size size = sizeService.findByIdentity(Integer.parseInt(sizeIdList.get(0)));
					Clothes c = service.findByIdentity(Integer.parseInt((String) entry.getKey()));
					c.getSizes().clear();
					c.getSizes().add(size);
					clothes.add(c);
				}
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