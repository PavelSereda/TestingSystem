package by.tc.nb.dao;


import by.tc.nb.dao.mySQL.MySQLTestDao;
import by.tc.nb.dao.mySQL.MySQLTutorDao;

public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();

    private final TestDao testDAO = new MySQLTestDao();

    private final TutorDao tutorDAO = new MySQLTutorDao();


    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public TestDao getTestDAO() {
        return testDAO;
    }

    public TutorDao getTutorDAO() {
        return tutorDAO;
    }
}
