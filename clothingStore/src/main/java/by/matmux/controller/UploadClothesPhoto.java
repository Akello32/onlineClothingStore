package by.matmux.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class TestServ
 */

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadClothesPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(UploadClothesPhoto.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private static final String SAVE_DIR = "img/BDimg/";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		logger.debug(fileSaveDir.getAbsolutePath());

		String photo = "";
		String name = "";
		String type = "";
		String brand = "";
		String size = "";
		String price = "";
		String numbers = "";

		String fileName = "";
		
		for (Part part : request.getParts()) {
			if (fileName.equals("")) {
				fileName = extractFileName(part, "filename");
				fileName = new File(fileName).getName();
				logger.debug(fileName);
				photo = fileName;
				part.write(savePath + File.separator + fileName);
				logger.debug("Upload " + fileName + " has been done successfully!");
			} else {
				String paramName = extractFileName(part, "name");
				if (paramName != null) {
					switch (paramName) {
					case "nameProduct":
						name = paramName;
						break;
					case "typeProduct":
						type = paramName;
						break;
					case "brandProduct":
						brand = paramName;
						break;
					case "sizeProduct":
						size = paramName;
						break;
					case "priceClothes":
						price = paramName;
						break;
					case "numbersClothes":
						numbers = paramName;
						break;
					default:
						break;
					}
				}
			}
		}

		request.setAttribute("name", name);
		request.setAttribute("type", type);
		request.setAttribute("brand", brand);
		request.setAttribute("size", size);
		request.setAttribute("price", price);
		request.setAttribute("numbers", numbers);
		request.setAttribute("photo", photo);

		getServletContext().getRequestDispatcher("/manager/addsProduct.html").forward(request, response);
	}

	private String extractFileName(Part part, String name) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith(name)) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
