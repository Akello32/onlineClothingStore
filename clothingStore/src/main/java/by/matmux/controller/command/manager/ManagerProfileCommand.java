package by.matmux.controller.command.manager;

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
import by.matmux.controller.command.guest.catalog.CatalogHelpersMethods;
import by.matmux.exception.PersistentException;
import by.matmux.service.BrandService;
import by.matmux.service.ClothesService;
import by.matmux.service.ServiceEnum;
import by.matmux.service.TypeService;

public class ManagerProfileCommand extends BaseCommand {
	private static final Logger log = LogManager.getLogger(ManagerProfileCommand.class);
	
	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		String adds = request.getParameter("addsProduct");
		String update = request.getParameter("updProduct");
		String del = request.getParameter("delProduct");
		
		if (adds != null) {
			if (update != null) {
				if (request.getAttribute("emptySize") != null) {
					request.setAttribute("result", "Выберите размер");
				}
				request.setAttribute("add", true);
				return null;
			} else {
				request.setAttribute("add", true);
				request.getSession().removeAttribute("product");
				request.getSession().removeAttribute("size");
			}
			return null;
		} else if (update != null || del != null ) {
			ClothesService service = (ClothesService) factory.getService(ServiceEnum.CLOTHES);

			List<Clothes> clothes = new ArrayList<>();

			int prevId = 0;
			int nextId = 0;
			
			if (request.getParameter("previous") != null) {
				prevId = Integer.parseInt(request.getParameter("previous"));
			}

			if (request.getParameter("next") != null) {
				nextId = Integer.parseInt(request.getParameter("next"));
			}
			
			clothes = CatalogHelpersMethods.getPaginationCatalog(clothes, service, prevId, nextId, request);

			BrandService brandService = (BrandService) factory.getService(ServiceEnum.BRAND);
			List<Brand> brands = brandService.findAllBrands();
			TypeService typeService = (TypeService) factory.getService(ServiceEnum.TYPE);
			List<Type> types = typeService.findAllType();

			CatalogHelpersMethods.addTypeAndBrand(clothes, brands, types);
			
			request.setAttribute("clothes", clothes);
			if (update != null) {
				log.debug("update complete! " + update);
				request.setAttribute("upd", true);				
			} else {
				request.setAttribute("del", true);
			}
			return null;
		} 
		return null;
	}

}
