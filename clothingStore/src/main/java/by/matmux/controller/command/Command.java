package by.matmux.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	Forward execute(HttpServletRequest request, HttpServletResponse response);
	
	void close();
}
