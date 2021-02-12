package by.matmux.controller.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;
import by.matmux.exception.PersistentException;

public class BuyerProfileCommand extends BaseCommand{

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		return new Forward("/buyer/profile.jsp");
	}
	
}
