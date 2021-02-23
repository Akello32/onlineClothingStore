package by.matmux.controller.command.guest.catalog;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Size;
import by.matmux.bean.Type;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;
import by.matmux.service.BrandService;
import by.matmux.service.ClothesService;
import by.matmux.service.ServiceEnum;
import by.matmux.service.SizeService;
import by.matmux.service.TypeService;

public class ProductCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		ClothesService service = (ClothesService) factory.getService(ServiceEnum.CLOTHES);
		SizeService sizeService = (SizeService) factory.getService(ServiceEnum.SIZE);
		
		BrandService brandService = (BrandService) factory.getService(ServiceEnum.BRAND);
		List<Brand> brands = brandService.findAllBrands();

		TypeService typeService = (TypeService) factory.getService(ServiceEnum.TYPE);
		List<Type> types = typeService.findAllType();

		String clothesId = request.getParameter("product");
		Clothes product = service.findByIdentity(Integer.parseInt(clothesId));
	
		CatalogHelpersMethods.addTypeAndBrand(Arrays.asList(product), brands, types);
		
		List<Size> sizes = product.getSizes();
		
		request.setAttribute("sizes", sizes);
		request.setAttribute("product", product);

		return null;
	}
}
