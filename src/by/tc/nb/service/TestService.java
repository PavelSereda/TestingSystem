package by.tc.nb.service;


import by.tc.nb.bean.entity.Answer;
import by.tc.nb.bean.entity.Result;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.exception.ServiceException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface TestService {
    List<Test> showSubjects() throws ServiceException;

    List<Result> addAnswer(List<Answer> answers) throws ServiceException;

}
