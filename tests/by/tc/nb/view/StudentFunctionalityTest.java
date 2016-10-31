package by.tc.nb.view;

import by.tc.nb.bean.AddAnswerRequest;
import by.tc.nb.bean.AddAnswerResponce;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.SubjectsResponce;
import by.tc.nb.bean.entity.Answer;
import by.tc.nb.command.impl.AddAnswer;
import by.tc.nb.command.impl.ShowSubjects;
import by.tc.nb.controller.Controller;
import by.tc.nb.dao.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;


public class StudentFunctionalityTest {

    private final String classDescription = "Student";
    String data1Command;
    String data2Command;
    DataIntegrator dataIntegrator = new DataIntegrator();
    List<Data> listData = dataIntegrator.returnCommands(classDescription);

    public StudentFunctionalityTest() throws ParserConfigurationException, SAXException, IOException {
    }

    @BeforeClass
    public void setUp() throws ParserConfigurationException, SAXException, IOException {

        for (Data data : listData) {
            if (data.DProviderName == "dp1") {
                data1Command = data.commandName;
            }
            if (data.DProviderName == "dp2") {
                data2Command = data.commandName;
            }
        }
    }


    @DataProvider(name = "dp1")
    public Object[][] createSomeData() {
        return new Object[][]{
                {data1Command},
        };
    }

    @Test(expectedExceptions = {DAOException.class}, dataProvider = "dp1")
    public void addEmptyAnswerTest(String command) throws Exception {
        AddAnswerRequest testRequest = new AddAnswerRequest();
        testRequest.setCommandName(command);
        AddAnswer addAnswerResponce = new AddAnswer();
        Assert.assertEquals(addAnswerResponce.execute(testRequest).getClass().toString(), "class by.tc.nb.bean.SubjectsResponce");
    }


    @Test(dataProvider = "dp1")
    public void addAnswerTest(String command) throws Exception {
        AddAnswerRequest testRequest = new AddAnswerRequest();
        testRequest.setCommandName(command);
        List<Answer> ans = new ArrayList<>();
        ans.add(new Answer());
        testRequest.setAnswers(ans);
        AddAnswer addAnswerResponce = new AddAnswer();
        Assert.assertEquals(addAnswerResponce.execute(testRequest).getClass().toString(), "class by.tc.nb.bean.AddAnswerResponce");

    }

    @DataProvider(name = "dp2")
    public Object[][] createSomeData2() {
        return new Object[][]{
                {data2Command},
        };
    }

    @Test(expectedExceptions = {NullPointerException.class}, dataProvider = "dp1")
    public void testsSize(String command) throws Exception {
        Controller controller = new Controller();
        Request testRequest = new Request();
        testRequest.setCommandName(command);
        SubjectsResponce subjectsResponce = (SubjectsResponce) controller.doRequest(testRequest);
        List<by.tc.nb.bean.entity.Test> tests = subjectsResponce.getTests();
        Assert.assertFalse(tests.size() == 0);
    }


    @Test(dataProvider = "dp2")
    public void getSubjTests(String command) throws Exception {
        Request testRequest = new Request();
        testRequest.setCommandName(command);
        ShowSubjects ssubj = new ShowSubjects();
        Assert.assertEquals(ssubj.execute(testRequest).getClass().toString(), "class by.tc.nb.bean.SubjectsResponce");
    }


}