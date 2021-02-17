package by.matmux.controller.command.guest.catalog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.bean.Clothes;
import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;
import by.matmux.service.ClothesService;
import by.matmux.service.ServiceEnum;

public class ProductFromCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		ClothesService service = (ClothesService) factory.getService(ServiceEnum.CLOTHES);

		Forward forward = new Forward("/product.jsp", true);
		
		String clothesId = request.getParameter("product");
		Clothes product = service.findByIdentity(Integer.parseInt(clothesId));
	
		forward.getAttributes().put("product", product);

		return forward;
	}
}
