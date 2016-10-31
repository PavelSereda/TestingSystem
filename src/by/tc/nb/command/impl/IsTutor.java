package by.tc.nb.command.impl;

import by.tc.nb.bean.*;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.TutorService;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;


public class IsTutor implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        IsTutorRequest req = null;
        if (request instanceof IsTutorRequest) {
            req = (IsTutorRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        IsTutorResponce response = new IsTutorResponce();


        ServiceFactory service = ServiceFactory.getInstance();
        TutorService tutService = service.getTutService();
        try {
            response.setTutor(tutService.checkTutor(req.getLogin(), req.getPassword()));
        } catch (ServiceException e) {
             throw new CommandException(e);
        }
        response.setErrorStatus(false);
        response.setResultMessage("All OK!");
        return response;
    }
}
