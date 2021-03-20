package by.matmux.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.dao.pool.ConnectionPool;
import by.matmux.exception.PersistentException;
import by.matmux.service.ServiceFactory;

@WebServlet(urlPatterns = { "*.html" })
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LogManager.getLogger(DispatcherServlet.class);

	// public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	// public static final String	 DB_URL = "jdbc:mysql://localhost:3306/clothingDB?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
	// public static final String DB_USER = "akello";
	// public static final String DB_PASSWORD = "2003/2003";
	// public static final int DB_POOL_START_SIZE = 10;
	// public static final int DB_POOL_MAX_SIZE = 1000;
	// public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

	public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String	 DB_URL = "jdbc:mysql://d3y0lbg7abxmbuoi.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/inwhreyn62qznjm0?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
	public static final String DB_USER = "ohzll4hfa2cdwmli";
	public static final String DB_PASSWORD = "d7cu6ry9pcvn2p6w";
	public static final int DB_POOL_START_SIZE = 10;
	public static final int DB_POOL_MAX_SIZE = 1000;
	public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;
		
	public void init() {
		try {
			ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, 
					DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
		} catch (PersistentException e) {
			logger.error("It is impossible to initialize application", e);
			destroy();
		}
	}
	
	public ServiceFactory getFactory() throws PersistentException {
		return new ServiceFactory();
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BaseCommand command = (BaseCommand) req.getAttribute("command");
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
			command.setFactory(getFactory());
			Forward forward = command.execute(req, resp);
			command.close();
			if(session != null && forward != null && !forward.getAttributes().isEmpty()) {
				session.setAttribute("redirectedData", forward.getAttributes());
			}
			String requestedUri = req.getRequestURI();
			if(forward != null && forward.isRedirect()) {
				String redirectedUri = req.getContextPath() + forward.getPath();
				logger.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri, redirectedUri));
				resp.sendRedirect(redirectedUri);
			} else {
				String jspPage;
				if(forward != null) {
					jspPage = forward.getPath();
				} else {
					jspPage = command.getName() + ".jsp";
				}
				jspPage = "/WEB-INF/jsp" + jspPage;
				logger.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
				getServletContext().getRequestDispatcher(jspPage).forward(req, resp);
			}
		}	catch(PersistentException e) {
			logger.error("It is impossible to process request", e);
			if (req.getAttribute("error") == null) {
				req.setAttribute("error", "Ошибка обработки данных");
			}
			
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
		} 
	}
}
