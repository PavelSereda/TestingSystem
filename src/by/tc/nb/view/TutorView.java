package by.tc.nb.view;


import by.tc.nb.bean.*;
import by.tc.nb.bean.entity.Answer;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.controller.Controller;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static javax.swing.UIManager.get;

public class TutorView {

    Controller controller = new Controller();
    Scanner in = new Scanner(System.in);
    List<Test> tests = new LinkedList<>();
    private String subject;
    private String testName;
    StringBuilder question;
    private String partQuestion;
    private String answer;
    private String login;
    private String password;
    Set<String> subjNames;
    Set<String> testNames;

    public boolean isTutor() throws ServiceException, ClassNotFoundException, DAOException, ParseException, IOException {
        System.out.println("Please, write your login\n");
        login = in.nextLine();
        System.out.println("Password: ");
        password = in.nextLine();
        IsTutorRequest isTutorRequest = new IsTutorRequest();
        isTutorRequest.setCommandName("CHECK_TUTOR");
        isTutorRequest.setLogin(login);
        isTutorRequest.setPassword(password);
        IsTutorResponce isTutorResponce = (IsTutorResponce) controller.doRequest(isTutorRequest);
        return isTutorResponce.isTutor() ? true : false;
    }


    public List<Test> add() {
        System.out.println("Write a subject: ");
        subject = in.nextLine();
        System.out.println("Write a topic : ");
        testName = in.nextLine();
        boolean testLoop = true;
        while (testLoop) {
            question = new StringBuilder();
            System.out.println("Write question, empty input for exit: ");
            while (true) {
                partQuestion = in.nextLine();
                if (partQuestion.equals("")) {
                    break;
                }
                question.append(partQuestion + "\r\n");
            }
            System.out.println("Write numbs of right answers. (Example: 123) :");
            answer = in.nextLine();
            Test test = new Test(subject, testName, question.toString(), answer);
            tests.add(test);
            System.out.println("Continue: any input\n Exit: 2");
            try {
                char choice = in.nextLine().charAt(0);
                if (choice == '2') {
                    break;
                } else {
                    continue;
                }
            } catch (StringIndexOutOfBoundsException ex) {
                continue;
            }
        }
        return tests;
    }

    public void showTestResults() {

        Request showResultsRequest = new Request();
        showResultsRequest.setCommandName("SHOW_RESULTS");
        try {
            ShowResultsResponse showResultsResponse = (ShowResultsResponse) controller.doRequest(showResultsRequest);
            ArrayList<Answer> answers = (ArrayList<Answer>) showResultsResponse.getAnswers();
            subjNames = new HashSet<>();
            testNames = new HashSet<>();
            for (int i = 0; i < answers.size(); i++) {
                subjNames.add(answers.get(i).getSubj_name());
            }
            List<String> subjList = new ArrayList<String>(subjNames);

            System.out.println("Choose subject names");
            for (int i = 0; i < subjList.size(); i++) {
                System.out.println((i + 1) + ". " + subjList.get(i));
            }
            int choice = in.nextInt() - 1;
            String subject = subjList.get(choice);

            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(i).getSubj_name().equals(subject)) {
                    testNames.add(answers.get(i).getTest_name());
                }
            }
            List<String> testList = new ArrayList<String>(testNames);
            System.out.println("Chose test name");
            for (int i = 0; i < testList.size(); i++) {

                System.out.println((i + 1) + ". " + testList.get(i));
            }

            choice = in.nextInt() - 1;
            String testName = testList.get(choice);

            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(i).getSubj_name().equals(subject) && answers.get(i).getTest_name().equals(testName)) {
                    System.err.println("Student " + answers.get(i).getLogin() + "\nHis answers " + answers.get(i).getAnswer() + "\n");
                }
            }
        } catch (Exception ex) {
            System.out.println("Unexpected behaviour");
        }
        System.out.println("Succesfully showed");

    }


    public void view() {
        System.out.println("1. Show tests results \n2. Create a test");
        char flag = in.nextLine().charAt(0);
        switch (flag) {
            case '1':
                showTestResults();
                break;
            case '2':
                AddTestRequest addTestRequest = new AddTestRequest();
                addTestRequest.setCommandName("ADD_TEST");
                addTestRequest.setTest(add());
                try {
                    Response addTestResponce = controller.doRequest(addTestRequest);
                    if (addTestResponce.isErrorStatus()) {
                        System.out.println("Error with responce");
                    }
                } catch (Exception ex) {
                    System.out.println("Unexpected behaviour");
                }
                System.out.println("Succesfully added");
        }
    }
}
