package by.matmux.controller.command.manager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.IOUtils;

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

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class SaveProductCommand extends BaseCommand {
	private static final Logger logger = LogManager.getLogger(SaveProductCommand.class);

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws PersistentException, IOException, ServletException {
		Forward forward = new Forward("/manager/profileManager.html", true);

		uploadFile(request, response);

		String identity = (String) request.getAttribute("identity");
		String sizeIdentity = (String) request.getAttribute("sizeIdentity");
		String photo = (String) request.getAttribute("photo");
		if (photo == null) {
			photo = (String) request.getAttribute("imgPath");
		}
		String name = (String) request.getAttribute("name");
		String typeName = (String) request.getAttribute("type");
		String brandName = (String) request.getAttribute("brand");
		String sizeName = (String) request.getAttribute("size");
		String price = (String) request.getAttribute("price");
		String numbers = (String) request.getAttribute("numbers");
		String color = (String) request.getAttribute("color");
		String gender = (String) request.getAttribute("gender");
		String description = (String) request.getAttribute("description");

		if (name != null && brandName != null && typeName != null && photo != null && sizeName != null && price != null
				&& numbers != null) {

			BrandService brandService = (BrandService) factory.getService(ServiceEnum.BRAND);
			TypeService typeService = (TypeService) factory.getService(ServiceEnum.TYPE);
			SizeService sizeService = (SizeService) factory.getService(ServiceEnum.SIZE);
			ClothesService clothesService = (ClothesService) factory.getService(ServiceEnum.CLOTHES);

			Clothes clothes = new Clothes();

			if (identity != null) {
				clothes.setIdentity(Integer.parseInt(identity));
			}
			clothes.setName(name);
			clothes.setImgPath(photo);
			clothes.setPrice(new BigDecimal(price));
			clothes.setColor(color);
			clothes.setGender(gender);
			clothes.setDescription(description);

			Brand brand = brandService.findByName(brandName);
			if (brand != null) {
				clothes.setBrand(brand);
			} else {
				brand = new Brand();
				brand.setName(brandName);
				brandService.save(brand);
				clothes.setBrand(brand);
			}

			Type type = typeService.findByName(typeName);
			if (type != null) {
				clothes.setType(type);
			} else {
				type = new Type();
				type.setName(typeName);
				typeService.save(type);
				clothes.setType(type);
			}

			clothesService.save(clothes);
			
			Size size = new Size();
			if (sizeIdentity != null) {
				size.setIdentity(Integer.parseInt(sizeIdentity));
			}
			size.setName(sizeName);
			size.setNumbers(Integer.parseInt(numbers));
			size.setClothesId(clothes.getIdentity());
			sizeService.save(size);

			forward.getAttributes().put("result", "Товар успешно изменен");
		}

		return forward;
	}

	private void uploadFile(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		final String SAVE_DIR = "img/BDimg/";

		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		logger.debug(fileSaveDir.getAbsolutePath());

		String fileName = "";

		for (Part part : request.getParts()) {
			if (fileName.equals("")) {
				fileName = extractFileName(part, "filename");
				if (!fileName.equals("none")) {
					fileName = new File(fileName).getName();
					logger.debug(fileName);
					request.setAttribute("photo", fileName);
					part.write(savePath + File.separator + fileName);
					logger.debug("Upload " + fileName + " has been done successfully!");
				}
			} else {
				String paramName = extractFileName(part, "name");
				String paramValue = exctractValue(part);
				if (paramName != null) {
					switch (paramName) {
					case "productId":
						request.setAttribute("identity", paramValue);
						break;
					case "sizeId":
						request.setAttribute("sizeIdentity", paramValue);
						break;
					case "nameProduct":
						request.setAttribute("name", paramValue);
						break;
					case "typeProduct":
						request.setAttribute("type", paramValue);
						break;	
					case "imgPath":
						request.setAttribute("imgPath", paramValue);
						break;
					case "brandProduct":
						request.setAttribute("brand", paramValue);
						break;
					case "sizeProduct":
						request.setAttribute("size", paramValue);
						break;
					case "priceClothes":
						request.setAttribute("price", paramValue);
						break;
					case "numbersClothes":
						request.setAttribute("numbers", paramValue);
						break;
					case "genderProduct":
						request.setAttribute("gender", paramValue);
						break;
					case "colorProduct":
						request.setAttribute("color", paramValue);
						break;
					case "descriptionProduct":
						request.setAttribute("description", paramValue);
						break;
					default:
						break;
					}
				}
			}
		}

	}

	private String extractFileName(Part part, String name) throws IOException {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		logger.debug("filename " + name);
		for (String s : items) {
			if (s.trim().startsWith(name)) {
				String result = s.substring(s.indexOf("=") + 2, s.length() - 1);
				if (result.equals("")) {
					return "none";
				}
				return result;
			}
		}
		return "none";
	}

	private String exctractValue(Part part) throws IOException {
		BufferedInputStream reader = new BufferedInputStream(part.getInputStream());
		StringBuilder string = new StringBuilder();
		int i;
		while ((i = reader.read()) != -1) {
			string.append((char) i);
		}
		String strTemp = new String(string.toString().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
		return strTemp;
	}
}
