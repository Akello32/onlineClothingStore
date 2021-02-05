package by.matmux.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.exception.PersistentException;

public interface Command {
	Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException;
	
	void close();
}
