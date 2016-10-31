package by.tc.nb.command;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;

public interface Command {
    Response execute(Request request) throws CommandException;
}
