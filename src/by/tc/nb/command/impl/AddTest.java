package by.tc.nb.command.impl;

import by.tc.nb.bean.*;
import by.tc.nb.bean.entity.Result;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.TestService;
import by.tc.nb.service.TutorService;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class AddTest implements Command {

    @Override
    public Response execute(Request request) throws CommandException {

        AddTestRequest req = null;
        if (request instanceof AddTestRequest) {
            req = (AddTestRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        Response response = new Response();


        ServiceFactory service = ServiceFactory.getInstance();
        TutorService tutService = service.getTutService();
        try {
            tutService.addTest(req.getTest());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        response.setErrorStatus(false);
        response.setResultMessage("All OK!");
        return response;
    }
}
