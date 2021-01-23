package by.matmux.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.bean.Clothes;
import by.matmux.dao.mysql.TransactionFactoryImpl;
import by.matmux.dao.pool.ConnectionPool;
import by.matmux.exception.PersistentException;
import by.matmux.service.ClothesService;
import by.matmux.service.ServiceFactoryImpl;

@WebServlet("/controller")
public class DispatcherServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(DispatcherServlet.class);

	public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/clothesDB?useUnicode=true&characterEncoding=UTF-8";
	public static final String DB_USER = "clothes_user";
	public static final String DB_PASSWORD = "clothes_password";
	public static final int DB_POOL_START_SIZE = 10;
	public static final int DB_POOL_MAX_SIZE = 1000;
	public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;
		
	public void init() {
		try {
			ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, 
					DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
		} catch (PersistentException e) {
			logger.error("It is impossible to initialize application", e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession(false);
			if(session != null) {
				@SuppressWarnings("unchecked")
				Map<String, Object> attributes = (Map<String, Object>)session.getAttribute("redirectedData");
				if(attributes != null) {
					for(String key : attributes.keySet()) {
						req.setAttribute(key, attributes.get(key));
					}
					session.removeAttribute("redirectedData");
				}
			}		 
			ServiceFactoryImpl factoryImpl = new ServiceFactoryImpl(new TransactionFactoryImpl());
			ClothesService cImpl = factoryImpl.getService(ClothesService.class);
			List<Clothes> list = cImpl.findClothesByBrand(1);
			req.setAttribute("list", list);
			for(Clothes c : list) {
				logger.debug(c.getBrandId() + "\n");
			}
			req.getRequestDispatcher("jsp/tes.jsp").forward(req, resp);
		} catch (PersistentException e) {
			logger.debug(e.getMessage());
			/* 
			 * "/webapp/WEB-INF/jsp/tes.jsp"
			 * /src/main/webapp/WEB-INF/jsp/tes.jsp
			 */		
		} catch (ServletException e) {
			logger.debug(e.getMessage());

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			logger.debug(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
