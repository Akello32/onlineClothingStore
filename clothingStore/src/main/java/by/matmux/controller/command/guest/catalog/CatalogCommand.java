package by.matmux.controller.command.guest.catalog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Brand;
import by.matmux.bean.Clothes;
import by.matmux.bean.Type;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.dao.mysql.ClothesDaoImpl;
import by.matmux.exception.PersistentException;
import by.matmux.service.BrandService;
import by.matmux.service.ClothesService;
import by.matmux.service.ServiceEnum;
import by.matmux.service.TypeService;

public class CatalogCommand extends BaseCommand {
	private static final Logger log = LogManager.getLogger(CatalogCommand.class);

	@SuppressWarnings("unchecked")
	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		ClothesService service = (ClothesService) factory.getService(ServiceEnum.CLOTHES);

		List<Clothes> clothes = new ArrayList<>();
		List<Clothes> temp = (List<Clothes>) request.getAttribute("clothes");

		int prevId = 0;
		int nextId  = 0;
		boolean lastPage = false;
		
		if (request.getParameter("previous") != null) {
			prevId = Integer.parseInt(request.getParameter("previous"));
			log.debug(prevId);
		}
		
		if (request.getParameter("next") != null) {
			nextId = Integer.parseInt(request.getParameter("next"));
			log.debug(nextId);
		}
		
		if (request.getParameter("last") != null) {
			lastPage = Boolean.parseBoolean(request.getParameter("last"));
			log.debug(lastPage);
		}

		if (temp != null) {
			clothes.addAll(temp);
		} else if (prevId != 0) {
			clothes = service.findPrevPageClothes(prevId);
			if (clothes.size() == 9) {
				clothes.remove(8);
				request.setAttribute("prevBul", true);
				request.setAttribute("nextBul", true);
				request.setAttribute("startId", prevId+1);
			}
		} else if (nextId != 0) {
			clothes = service.findNextPageClothes(nextId);
			request.setAttribute("prevBul", true);
			if (clothes.size() == 9) {
				clothes.remove(8);
				request.setAttribute("nextBul", true);
				request.setAttribute("startId", nextId+1);
			}
		} /*
			 * else if (lastPage) { clothes = service.findLastPageClothes();
			 * request.setAttribute("previousBul", true); }
			 */ else {
			clothes = service.findNextPageClothes(0);
			if (clothes.size() == 9) {
				clothes.remove(8);
				request.setAttribute("nextBul", true);
			}
		}

		BrandService brandService = (BrandService) factory.getService(ServiceEnum.BRAND);
		List<Brand> brands = brandService.findAllBrands();
		TypeService typeService = (TypeService) factory.getService(ServiceEnum.TYPE);
		List<Type> types = typeService.findAllType();

		CatalogHelpersMethods.addTypeAndBrand(clothes, brands, types);

		request.setAttribute("types", types);
		request.setAttribute("brands", brands);
		request.setAttribute("clothes", clothes);

		return null;
	}
}
