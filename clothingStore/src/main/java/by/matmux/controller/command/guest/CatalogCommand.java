package by.matmux.controller.command.guest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.matmux.controller.command.BaseCommand;
import by.matmux.controller.command.Forward;

public class CatalogCommand extends BaseCommand {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response) {
		return new Forward("/catalog");
	}

}
