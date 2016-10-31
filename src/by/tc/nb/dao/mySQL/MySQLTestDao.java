package by.tc.nb.dao.mySQL;


import by.tc.nb.bean.entity.Answer;
import by.tc.nb.bean.entity.Result;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.dao.TestDao;
import by.tc.nb.dao.connectionsPool.Pool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MySQLTestDao implements TestDao {


    @Override
    public List<Test> showSubjects() throws DAOException {
        Connection connection = null;
        ArrayList<Test> resultList = new ArrayList<>();
        try {
            ResultSet resultSet = null;
            connection = Pool.getInstance().getConnection();
            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery("SELECT subj_name, test_name,question,right_answer from tests");

                while (resultSet.next()) {
                    resultList.add(new Test(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
                }
            }

        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                Pool.getInstance().returnConnection(connection);

            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e);
            }
        }
        return resultList;
    }

    @Override
    public List<Result> addAnswer(List<Answer> answers) throws DAOException {
        List<Result> resultList = new ArrayList<>();
        PreparedStatement ps ;
        Connection connection = null;
        try {
            ResultSet resultSet = null;

            connection = Pool.getInstance().getConnection();


            ps = connection.prepareStatement("insert into answers (login, subj_name, test_name,answer)" + " values(?,?,?,?)");
            ps.setString(1, answers.get(0).getLogin());
            ps.setString(2, answers.get(0).getSubj_name());
            ps.setString(3, answers.get(0).getTest_name());
            ps.setString(4, answers.get(0).getAnswer());
            ps.execute();

            resultSet = ps.executeQuery("select answer.answer, tests.right_answer from tests" +
                    " join answer on answer.subj_name=tests.subj_name And" +
                    " answer.test_name=tests.test_name where answer.login='" + answers.get(0).getLogin() + "';");

            while (resultSet.next()) {
                resultList.add(new Result(resultSet.getString(1), resultSet.getString(2)));
            }

        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e);
        } finally {
            Pool.getInstance().closePool();
        }
        return resultList;
    }
}


