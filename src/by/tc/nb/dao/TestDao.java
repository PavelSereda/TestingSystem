package by.tc.nb.dao;


import by.tc.nb.bean.entity.Answer;
import by.tc.nb.bean.entity.Result;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.dao.exception.DAOException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface TestDao {
    List<Result> addAnswer(List<Answer> answers) throws DAOException;

    List<Test> showSubjects() throws DAOException;
}
