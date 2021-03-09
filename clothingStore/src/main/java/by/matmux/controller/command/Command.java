package by.matmux.controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.exception.PersistentException;

public interface Command {
	Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException, IOException, ServletException;
	
	void close();
}
