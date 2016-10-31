package by.tc.nb.controller;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;

public class Controller {
	private CommandHelper helper = new CommandHelper();
	
	
	public Controller(){}
	
	public Response doRequest(Request request) throws IOException, ParseException, ServiceException, ClassNotFoundException, DAOException {
		String commandName = request.getCommandName();
		
		Command command = helper.getCommand(commandName);
		
		Response response;
		try {
			response = command.execute(request);
		} catch (CommandException e) {
			response = new Response();
			response.setErrorStatus(true);
			response.setErrorMessage("ERROR!");
		}

		return response;
		
	}

}
