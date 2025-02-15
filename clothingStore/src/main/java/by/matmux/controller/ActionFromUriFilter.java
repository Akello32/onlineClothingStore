package by.matmux.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.LoginCommand;
import by.matmux.controller.command.LogoutCommand;
import by.matmux.controller.command.SignUpCommand;
import by.matmux.controller.command.administrator.AddManagerCommand;
import by.matmux.controller.command.administrator.AdminProfileCommand;
import by.matmux.controller.command.administrator.DelManagerCommand;
import by.matmux.controller.command.guest.MainCommand;
import by.matmux.controller.command.guest.catalog.CatalogCommand;
import by.matmux.controller.command.guest.catalog.CategoryCommand;
import by.matmux.controller.command.guest.catalog.ProductCommand;
import by.matmux.controller.command.guest.catalog.ShowByParam;
import by.matmux.controller.command.manager.DeleteProductCommand;
import by.matmux.controller.command.manager.ManagerProfileCommand;
import by.matmux.controller.command.manager.SaveProductCommand;
import by.matmux.controller.command.manager.UpdateProductFormCommand;
import by.matmux.controller.command.user.BuyerProfileCommand;
import by.matmux.controller.command.user.DeleteProductFromCart;
import by.matmux.controller.command.user.OrderCommand;
import by.matmux.controller.command.user.OrderFormCommand;
import by.matmux.controller.command.user.PaymentOrderCommand;
import by.matmux.controller.command.user.ShowPurchaseCommand;

/**
 * Servlet Filter implementation class ActionFromURLFilter
 */
@WebFilter(urlPatterns = { "*.html" })
public class ActionFromUriFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(ActionFromUriFilter.class);
	private static Map<String, BaseCommand> actions = new ConcurrentHashMap<>();

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		actions.put("/", new MainCommand());
		actions.put("/index", new MainCommand());
		actions.put("/catalog", new CatalogCommand());
		actions.put("/category", new CategoryCommand());
		actions.put("/showByParam", new ShowByParam());
		actions.put("/product/form", new ProductCommand());
		actions.put("/product", new ProductCommand());

		actions.put("/login", new LoginCommand());
		actions.put("/signup", new SignUpCommand());
		actions.put("/logout", new LogoutCommand());
		
		actions.put("/buyer/paymentOrder", new PaymentOrderCommand());
		actions.put("/buyer/deleteProduct", new DeleteProductFromCart());
		actions.put("/buyer/form", new OrderFormCommand());
		actions.put("/buyer/order", new OrderCommand());
		actions.put("/buyer/profileUser", new BuyerProfileCommand());
		actions.put("/buyer/purchase", new ShowPurchaseCommand());
		
		actions.put("/manager/profileManager", new ManagerProfileCommand());
		actions.put("/manager/managerUpdForm", new UpdateProductFormCommand());
		actions.put("/manager/managerUpd", new UpdateProductFormCommand());
		actions.put("/manager/managerDel", new DeleteProductCommand());
		actions.put("/manager/addsProduct", new SaveProductCommand());
		
		actions.put("/admin/profileAdmin", new AdminProfileCommand());
		actions.put("/admin/addsManager", new AddManagerCommand());
		actions.put("/admin/delManager", new DelManagerCommand());
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			String contextPath = httpRequest.getContextPath();
			String uri = httpRequest.getRequestURI();
			logger.debug(String.format("Starting of processing of request for URI \"%s\"", uri));
			int beginAction = contextPath.length();
			int endAction = uri.lastIndexOf('.');
			String actionName;
			if (endAction >= 0) {
				actionName = uri.substring(beginAction, endAction);
			} else {
				actionName = uri.substring(beginAction);
			}

			try {
				BaseCommand command = actions.get(actionName);
				command.setName(actionName);
				httpRequest.setAttribute("command", command);
				chain.doFilter(request, response);
			} catch (Exception e) {
				logger.error("It is impossible to create action handler object", e);
				httpRequest.setAttribute("error",
						String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
				httpRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request,
						response);
			}
		} else {
			logger.error("It is impossible to use HTTP filter");
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	public void destroy() {
	}

}
