package by.tc.nb.command.impl;


import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.SubjectsResponce;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.TestService;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ShowSubjects implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        Request req = null;

        if (request instanceof Request) {
            req = (Request) request;
        } else {
            throw new CommandException("Wrong request");
        }

        SubjectsResponce response = new SubjectsResponce();
        ArrayList<Test> result = null;

        ServiceFactory service = ServiceFactory.getInstance();
        TestService tstService = service.getTstService();


        try {
            result = (ArrayList<Test>) tstService.showSubjects();
        } catch (ServiceException e) {
            throw new CommandException(e);

        }


        response.setErrorStatus(false);
        response.setTests(result);
        response.setResultMessage("All OK!");
        return response;
    }
}
