package by.tc.nb.command.impl;

import by.tc.nb.bean.*;
import by.tc.nb.bean.entity.Answer;
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


public class ShowResults implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        Request req = null;

        if (request instanceof Request) {
            req = (Request) request;
        } else {
            throw new CommandException("Wrong request");
        }

        ShowResultsResponse response = new ShowResultsResponse();


        ServiceFactory service = ServiceFactory.getInstance();
        TutorService tutService = service.getTutService();


        response.setErrorStatus(false);

        try {
            response.setAnswers(tutService.showResults());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        response.setResultMessage("All OK!");
        return response;
    }
}
