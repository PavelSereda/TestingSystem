package by.tc.nb.command.impl;


import by.tc.nb.bean.*;
import by.tc.nb.bean.entity.Result;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.TestService;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

public class AddAnswer implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        AddAnswerRequest req = null;

        if (request instanceof AddAnswerRequest) {
            req = (AddAnswerRequest) request;
        } else {
            throw new CommandException("Wrong request");
        }

        AddAnswerResponce response = new AddAnswerResponce();
        List<Result> result = null;

        ServiceFactory service = ServiceFactory.getInstance();
        TestService tstService = service.getTstService();


        try {
            result = tstService.addAnswer(req.getAnswers());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }


        response.setErrorStatus(false);
        response.setResult(result);
        response.setResultMessage("All OK!");
        return response;
    }
}
