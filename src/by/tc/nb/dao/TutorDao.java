package by.tc.nb.dao;

import by.tc.nb.bean.entity.Answer;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.exception.ServiceException;

import java.util.List;


public interface TutorDao {
    void addTest(List<Test> test) throws  DAOException;

    List<Answer> showResults() throws  DAOException;

    boolean checkTutor(String login, String password) throws DAOException;
}
