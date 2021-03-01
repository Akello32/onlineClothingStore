package by.matmux.controller.command.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;

public class ManagerProfileCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		return null;
	}

}
