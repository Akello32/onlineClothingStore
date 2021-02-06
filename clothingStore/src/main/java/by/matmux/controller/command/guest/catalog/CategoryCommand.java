package by.matmux.controller.command.guest.catalog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Type;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;
import by.matmux.service.BrandService;
import by.matmux.service.ClothesService;
import by.matmux.service.ServiceEnum;
import by.matmux.service.TypeService;

public class CategoryCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/catalog.jsp", false);
		
		List<Clothes> result = new ArrayList<>();
		
		ClothesService service = (ClothesService) factory.getService(ServiceEnum.CLOTHES);
		List<Clothes> clothes = service.findAllClothes();
		
		BrandService brandService = (BrandService) factory.getService(ServiceEnum.BRAND);
		List<Brand> brands = brandService.findAllBrands();

		TypeService typeService = (TypeService) factory.getService(ServiceEnum.TYPE);
		List<Type> types = typeService.findAllType();
		
		String category = request.getParameter("category");
		Type type = null;
		
		request.setAttribute("brands", brands);
		request.setAttribute("types", types);
		
		for (Type t : types) {
			if (t.getName().equals(category)) {
				type = t;
				break;
			} 
		}
		
		if (type == null) {
			return forward;
		}
		
		for (Clothes c : clothes) {
			if (c.getType().getIdentity() == type.getIdentity()) {
				result.add(c);
			}
		}
 		
		request.setAttribute("clothes", result);
		
		return forward;
	}

}
