package by.tc.nb.service;

import by.tc.nb.bean.entity.Answer;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.exception.ServiceException;

import java.util.List;


public interface TutorService {
    void addTest(List<Test> test) throws ServiceException;

    List<Answer> showResults() throws ServiceException;

    boolean checkTutor(String login, String password) throws  ServiceException;
}
