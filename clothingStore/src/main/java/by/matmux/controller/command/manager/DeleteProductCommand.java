package by.matmux.controller.command.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;
import by.matmux.service.ClothesService;
import by.matmux.service.ServiceEnum;
import by.matmux.service.SizeService;

public class DeleteProductCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		ClothesService clothesService = (ClothesService) factory.getService(ServiceEnum.CLOTHES);
		
		Forward forward = new Forward("/manager/profileManager.html", true);
		
		String productId = request.getParameter("product");
		
		if (productId != null) {
			clothesService.delete(Integer.parseInt(productId));
			forward.getAttributes().put("result", "Товар успешно удален");
		}
			
		return forward;
	}

}
