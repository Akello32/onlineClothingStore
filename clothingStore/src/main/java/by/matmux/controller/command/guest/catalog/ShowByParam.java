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
		List<Clothes> clothes = service.findAllClothes();

		String size = request.getParameter("size");
		String color = request.getParameter("color");
		String brandParam = request.getParameter("brand");

		TypeService typeService = (TypeService) factory.getService(ServiceEnum.TYPE);
		List<Type> types = typeService.findAllType();
		
		BrandService brandService = (BrandService) factory.getService(ServiceEnum.BRAND);
		List<Brand> brands = brandService.findAllBrands();
		
		if (size != null) {
			result = showBySize(size, clothes);
		} 
		
		if (color != null) {
			List<Clothes> temp = showByColor(color, clothes);
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
			Brand brand = null;
			for (Brand b : brands) {
				if (b.getName().equals(brandParam)) {
					brand = b;
				}
			}
			
			List<Clothes> temp = showByBrand(brand, clothes);
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
		
		request.setAttribute("brands", brands);
		request.setAttribute("types", types);
		request.setAttribute("clothes", result);

		return forward;
	}

	private List<Clothes> showBySize(String size, List<Clothes> clothes) {
		List<Clothes> result = new ArrayList<>();
		for (Clothes c : clothes) {
			if (c.getSize().equals(size)) {
				result.add(c);
			}
		}
		return result;
	}
	
	private List<Clothes> showByColor(String color, List<Clothes> clothes) {
		List<Clothes> result = new ArrayList<>();
		for (Clothes c : clothes) {
			if (c.getColor().equals(color)) {
				result.add(c);
			}
		}
		return result;
	}
	
	private  List<Clothes> showByBrand(Brand brand, List<Clothes> clothes) {
		List<Clothes> result = new ArrayList<>();
		
		if (brand == null) {
			return result;
		}
		
		for (Clothes c : clothes) {
			if (c.getBrand().getIdentity() == brand.getIdentity()) {
				result.add(c);
			}
		}
		return result;
	}
}
