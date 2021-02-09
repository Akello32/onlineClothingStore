package by.matmux.controller.command.guest.catalog;

import java.util.ArrayList;
import java.util.Iterator;
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

public class ShowByParam extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/catalog.jsp", false);

		List<Clothes> result = new ArrayList<>();
		
		ClothesService service = (ClothesService) factory.getService(ServiceEnum.CLOTHES);

		String size = request.getParameter("size");
		String color = request.getParameter("color");
		String brandParam = request.getParameter("brand");
		String gender = request.getParameter("gender");
		
		TypeService typeService = (TypeService) factory.getService(ServiceEnum.TYPE);
		List<Type> types = typeService.findAllType();
		
		BrandService brandService = (BrandService) factory.getService(ServiceEnum.BRAND);
		List<Brand> brands = brandService.findAllBrands();
		
		if (size != null) {
			result = service.findClothesBySize(size);
		} 
		
		if (color != null) {
			List<Clothes> temp = service.findClothesByColor(color);
			if (result.isEmpty()) {
				result.addAll(temp);
			} else if (!result.containsAll(temp)) {
				for (Clothes c : temp) {
					if (!result.contains(c)) {
						result.remove(c);
					}
				}
			}
		}
		
		if (gender != null) {
			List<Clothes> temp = service.findClothesByGender(gender);
			if (result.isEmpty()) {
				result.addAll(temp);
			} else if (!result.containsAll(temp)) {
				for (Clothes c : temp) {
					if (!result.contains(c)) {
						result.remove(c);
					}
				}
			}
		}
		
		if (brandParam != null) {
			List<Clothes> temp = service.findClothesByBrand(Integer.parseInt(brandParam));
			if (result.isEmpty()) {
				result.addAll(temp);
			} else if (!result.containsAll(temp)) {
				for (Clothes c : temp) {
					if (!result.contains(c)) {
						result.remove(c);
					}
				}
			}
		}
		
		CatalogHelpersMethods.addBrand(result, brands);
		CatalogHelpersMethods.deleteDuplicates(result);
		
		request.setAttribute("brands", brands);
		request.setAttribute("types", types);
		request.setAttribute("clothes", result);

		return forward;
	}
}
