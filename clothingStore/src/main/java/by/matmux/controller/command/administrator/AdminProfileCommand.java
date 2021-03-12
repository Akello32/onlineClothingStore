package by.matmux.controller.command.administrator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;

public class AdminProfileCommand extends BaseCommand {
	private static final Logger log = LogManager.getLogger(AdminProfileCommand.class);

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) {
		String adds = request.getParameter("addsManager");
		String del = request.getParameter("delManager");

		if (adds != null) {
			request.setAttribute("add", true);
			return null;
		} else if (del != null) {
			request.setAttribute("del", true);
			return null;
		}
		return null;
	}

}
