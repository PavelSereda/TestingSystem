package by.tc.nb.service.impl;


import by.tc.nb.bean.entity.Answer;
import by.tc.nb.bean.entity.Result;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.dao.DaoFactory;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.TestService;
import by.tc.nb.service.exception.ServiceException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestServiceImpl implements TestService {

    @Override
    public List<Test> showSubjects() throws ServiceException {
        try {
            return DaoFactory.getInstance().getTestDAO().showSubjects();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Result> addAnswer(List<Answer> answers) throws ServiceException {
        if (answers.size() == 0) {
            throw new ServiceException("Empty answers list");
        }
        try {
            return DaoFactory.getInstance().getTestDAO().addAnswer(answers);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
