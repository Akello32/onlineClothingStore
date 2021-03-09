package by.matmux.controller.command.manager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Size;
import by.matmux.bean.Type;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.controller.command.guest.catalog.CatalogHelpersMethods;
import by.matmux.exception.PersistentException;
import by.matmux.service.BrandService;
import by.matmux.service.ClothesService;
import by.matmux.service.ServiceEnum;
import by.matmux.service.SizeService;
import by.matmux.service.TypeService;

public class UpdateProductFormCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws PersistentException, IOException, ServletException {

		Forward forward = new Forward("/manager/profileManager.html?addsProduct='true'&updProduct='true'", true);

		String productId = request.getParameter("product");
		String sizeId;
		
		if (productId != null) {
			sizeId = request.getParameter("sizeProduct" + productId);
			ClothesService clothesService = (ClothesService) factory.getService(ServiceEnum.CLOTHES);
			SizeService sizeService = (SizeService) factory.getService(ServiceEnum.SIZE);
			Size size = null;
			
			if (sizeId != null) {
				size = sizeService.findByIdentity(Integer.parseInt(sizeId));				
			}
			
			Clothes clothes = clothesService.findByIdentity(Integer.parseInt(productId));

			if (clothes != null) {
				BrandService brandService = (BrandService) factory.getService(ServiceEnum.BRAND);
				List<Brand> brands = brandService.findAllBrands();
				TypeService typeService = (TypeService) factory.getService(ServiceEnum.TYPE);
				List<Type> types = typeService.findAllType();

				CatalogHelpersMethods.addTypeAndBrand(Arrays.asList(clothes), brands, types);

				List<Size> sizes = sizeService.findByClothesId(Integer.parseInt(productId));

				if (size != null) {
					request.getSession().setAttribute("product", clothes);
					request.getSession().setAttribute("size", size);
				} else if (sizes != null && sizes.stream().allMatch(s -> s.getNumbers() == 0)) {
					request.getSession().setAttribute("product", clothes);
				} else {
					forward.getAttributes().put("emptySize", true);
				}
			}
		}
		
		return forward;
	}

}
