package by.tc.nb.dao.mySQL;

import by.tc.nb.bean.entity.Answer;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.dao.TutorDao;
import by.tc.nb.dao.connectionsPool.Pool;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.exception.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLTutorDao implements TutorDao {
    @Override
    public void addTest(List<Test> test) throws DAOException {
        Connection connection = null;
        try {
            connection = Pool.getInstance().getConnection();
            PreparedStatement ps;
            for (int i = 0; i < test.size(); i++) {

                ps = connection.prepareStatement(" INSERT INTO tests (subj_name, test_name, question, right_answer)" +
                        "VALUES(?,?,?,?);");
                ps.setString(1, test.get(i).getSubj_name());
                ps.setString(2, test.get(i).getTestName());
                ps.setString(3, test.get(i).getQuestion());
                ps.setString(4, test.get(i).getRight_answer());
                ps.execute();
            }


        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                Pool.getInstance().returnConnection(connection);
                Pool.getInstance().closePool();
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public List<Answer> showResults() throws DAOException {

        Connection connection = null;
        List<Answer> resultList = new ArrayList<>();
        try {
            ResultSet resultSet = null;
            connection = Pool.getInstance().getConnection();
            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery("SELECT login, subj_name, test_name,answer from answer");
                while (resultSet.next()) {
                    resultList.add(new Answer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
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
    public boolean checkTutor(String login, String password) throws DAOException {
        Connection connection = null;
        try {
            ResultSet resultSet = null;
            connection = Pool.getInstance().getConnection();
            PreparedStatement ps;
            ps = connection.prepareStatement("select login from tutors where login = ? and password = ? ;");
            ps.setString(1, login);
            ps.setString(2, password);
            resultSet = ps.executeQuery();
            resultSet.next();
            try {
                if (resultSet.getString(1).equals(login)) {
                    System.out.println("Welcome, " + login);
                    return true;
                }
            } catch (SQLException ex) {
                System.out.println("Wrong tutor name or password");
            }
            return false;

        } catch (InterruptedException | SQLException e) {
            throw new DAOException(e);
        } finally {
            try {
                Pool.getInstance().returnConnection(connection);
                Pool.getInstance().closePool();
            } catch (SQLException | InterruptedException e) {
                throw new DAOException(e);
            }
        }
    }
}
