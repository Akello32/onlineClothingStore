package by.matmux.controller.command.user;

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
import by.matmux.controller.command.guest.catalog.CatalogHelpersMethods;
import by.matmux.exception.PersistentException;
import by.matmux.service.BrandService;
import by.matmux.service.ClothesService;
import by.matmux.service.ServiceEnum;
import by.matmux.service.TypeService;

public class OrderCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		String[] paramArr = request.getParameterValues("product");
		List<Clothes> clothes = new ArrayList<>();
		
		ClothesService service = (ClothesService) factory.getService(ServiceEnum.CLOTHES);
		
		BrandService brandService = (BrandService) factory.getService(ServiceEnum.BRAND);
		List<Brand> brands = brandService.findAllBrands();

		TypeService typeService = (TypeService) factory.getService(ServiceEnum.TYPE);
		List<Type> types = typeService.findAllType();

		
		
		if (paramArr != null) {
			for (String s : paramArr) {
				clothes.add(service.findByIdentity(Integer.parseInt(s)));
			}
		}
		
		CatalogHelpersMethods.addTypeAndBrand(clothes, brands, types);
			
		request.setAttribute("products", clothes);
		
		return new Forward("/buyer/order.jsp");
	}
	
}