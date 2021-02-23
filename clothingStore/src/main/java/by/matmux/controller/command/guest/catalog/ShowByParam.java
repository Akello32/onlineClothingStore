package by.matmux.controller.command.guest.catalog;

import java.util.ArrayList;
import java.util.Iterator;
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

public class ShowByParam extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/catalog.html", true);

		List<Clothes> result = new ArrayList<>();
		
		ClothesService service = (ClothesService) factory.getService(ServiceEnum.CLOTHES);
		SizeService sizeService = (SizeService) factory.getService(ServiceEnum.SIZE);
		
		String size = request.getParameter("size");
		String color = request.getParameter("color");
		String brandParam = request.getParameter("brand");
		String gender = request.getParameter("gender");
		
		if (size != null) {
			List<Size> sizeTemp = sizeService.findByName(size);
			if (!sizeTemp.isEmpty()) {
				for (Size s : sizeTemp) {
					result.add(service.findByIdentity(s.getClothesId()));
				}
			}
		}
		
		if (color != null) {
			List<Clothes> temp = service.findClothesByColor(color);
			if (result.isEmpty()) {
				result.addAll(temp);
			} else if (!result.containsAll(temp)) {
				result.removeIf(c -> !temp.contains(c));
			}
		}
		
		if (gender != null) {
			List<Clothes> temp = service.findClothesByGender(gender);
			if (result.isEmpty()) {
				result.addAll(temp);
			} else if (!result.containsAll(temp)) {
				result.removeIf(c -> !temp.contains(c));
			}
		}
		
		if (brandParam != null) {
			List<Clothes> temp = service.findClothesByBrand(Integer.parseInt(brandParam));
			if (result.isEmpty()) {
				result.addAll(temp);
			} else if (!result.containsAll(temp)) {
				result.removeIf(c -> !temp.contains(c));
			}
		}

		forward.getAttributes().put("clothes", result);

		return forward;
	}
}
