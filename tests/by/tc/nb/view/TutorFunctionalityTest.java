package by.tc.nb.view;

import by.tc.nb.bean.*;
import by.tc.nb.command.impl.AddTest;
import by.tc.nb.command.impl.IsTutor;
import by.tc.nb.command.impl.ShowResults;
import by.tc.nb.controller.Controller;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public class TutorFunctionalityTest {

    private final String validLogin = "pavel";
    private final String validPass = "123456";
    private final String classDescription = "Tutor";
    String data2Command;
    String data3Command;
    String data4Command;
    DataIntegrator dataIntegrator = new DataIntegrator();

    List<Data> listData = dataIntegrator.returnCommands(classDescription);

    public TutorFunctionalityTest() throws ParserConfigurationException, SAXException, IOException {
    }

    @BeforeClass
    public void setUp() throws ParserConfigurationException, SAXException, IOException {


        for (Data data : listData) {
            if (data.DProviderName == "dp2") {
                data2Command = data.commandName;
            }
            if (data.DProviderName == "dp3") {
                data3Command = data.commandName;
            }
            if (data.DProviderName == "dp4") {
                data4Command = data.commandName;
            }
        }
    }

    @DataProvider(name = "dp1")
    public Object[][] createSomeData() {
        return new Object[][]{
                {"vasiliy", "2323"},
                {"senya", "311331"},
                {"pavel", "12345"},
                {"pavel", "123"},
                {"pavel", ""},
                {"senya", "12345"}
        };
    }

    @Test(dataProvider = "dp1")
    public void testIsTutorNegative(String login, String password) throws Exception {
        Controller controller = new Controller();
        IsTutorRequest isTutorRequest = new IsTutorRequest();
        isTutorRequest.setCommandName("CHECK_TUTOR");
        isTutorRequest.setLogin(login);
        isTutorRequest.setPassword(password);
        IsTutorResponce isTutorResponce = (IsTutorResponce) controller.doRequest(isTutorRequest);
        Assert.assertEquals(isTutorResponce.isTutor(), false);
    }

    @Test
    public void testIsTutorPositive() throws Exception {
        Controller controller = new Controller();
        IsTutorRequest isTutorRequest = new IsTutorRequest();
        isTutorRequest.setCommandName("CHECK_TUTOR");
        isTutorRequest.setLogin(validLogin);
        isTutorRequest.setPassword(validPass);
        IsTutorResponce isTutorResponce = (IsTutorResponce) controller.doRequest(isTutorRequest);
        Assert.assertEquals(isTutorResponce.isTutor(), true);
    }

    @DataProvider(name = "dp2")
    public Object[][] createSomeData2() {
        return new Object[][]{
                {data2Command},
        };
    }

    @Test(dataProvider = "dp2")
    public void testIsTutorResponse(String command) throws Exception {

        IsTutorRequest isTutorRequest = new IsTutorRequest();
        isTutorRequest.setCommandName(command);
        IsTutor isTutor = new IsTutor();
        Assert.assertEquals(isTutor.execute(isTutorRequest).getClass().toString(), "class by.tc.nb.bean.IsTutorResponce");
    }

    @DataProvider(name = "dp3")
    public Object[][] createSomeData3() {
        return new Object[][]{
                {data3Command},
        };
    }

    @Test(dataProvider = "dp3")
    public void testShowTestResultsResponse(String command) throws Exception {

        Request showResultsRequest = new Request();
        showResultsRequest.setCommandName(command);
        ShowResults sr = new ShowResults();
        Assert.assertEquals(sr.execute(showResultsRequest).getClass().toString(), "class by.tc.nb.bean.ShowResultsResponse");
    }

    @DataProvider(name = "dp4")
    public Object[][] createSomeData4() {
        return new Object[][]{
                {data4Command},
        };
    }

    @Test(dataProvider = "dp4")
    public void testAddResponse(String command) throws Exception {

        AddTestRequest addTestRequest = new AddTestRequest();
        addTestRequest.setCommandName(command);
        AddTest at = new AddTest();
        Assert.assertEquals(at.execute(addTestRequest).getClass().toString(), "class by.tc.nb.bean.Response");
    }
}