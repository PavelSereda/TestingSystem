package by.tc.nb.service.impl;

import by.tc.nb.bean.entity.Answer;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.dao.DaoFactory;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.TutorService;
import by.tc.nb.service.exception.ServiceException;

import java.util.List;


public class TutorServiceImpl implements TutorService {
    @Override
    public void addTest(List<Test> test) throws ServiceException {
        if(test.size() == 0) {
            throw new ServiceException("Empty test!");
        }
        try {
            DaoFactory.getInstance().getTutorDAO().addTest(test);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public List<Answer> showResults() throws ServiceException {
        try {
            return DaoFactory.getInstance().getTutorDAO().showResults();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkTutor(String login, String password) throws ServiceException {
        if(login.equals("") || password.equals("")) {
            throw new ServiceException("Illegal parameters");
        }

        try {
            return DaoFactory.getInstance().getTutorDAO().checkTutor(login,password);
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
    }
}
