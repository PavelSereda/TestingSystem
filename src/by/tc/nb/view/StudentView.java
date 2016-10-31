package by.tc.nb.view;

import by.tc.nb.bean.AddAnswerRequest;
import by.tc.nb.bean.AddAnswerResponce;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.SubjectsResponce;
import by.tc.nb.bean.entity.Answer;
import by.tc.nb.bean.entity.Test;
import by.tc.nb.controller.Controller;
import by.tc.nb.dao.exception.DAOException;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;


public class StudentView {
    Controller controller = new Controller();
    Scanner in = new Scanner(System.in);
    private char choice;
    private String subject;
    private String testName;
    private StringBuilder studAnswer = new StringBuilder();
    private List<Answer> answers = new ArrayList<>();//Список ответов(Верный и ответ студента)
    private HashSet<String> subjects = new HashSet<>();//коллекция для исключения повторений предметов
    private HashSet<String> test_names = new HashSet<>();//коллекция для исключения повторений имён тестов
    private ArrayList<String> listSubj; //Список неповторяющихся предметов
    private ArrayList<String> listTest_names; //Список неповторяющихся тестов
    private String login;

    public void view() throws ServiceException, ClassNotFoundException, DAOException, ParseException, IOException {
        while (true) {
            System.out.println();
            Request testRequest = new Request();
            System.out.println("Please, write your name\n");
            login = in.nextLine();
            testRequest.setCommandName("SHOW_SUBJECTS");

            SubjectsResponce subjectsResponce = (SubjectsResponce) controller.doRequest(testRequest);
            if (subjectsResponce.isErrorStatus() == true) {
                System.out.println(subjectsResponce.getErrorMessage());
            } else {
                List<Test> tests = subjectsResponce.getTests();

                for (Test t : tests) {
                    subjects.add(t.getSubj_name());
                }

                if (subjects.size() != 0) {
                    System.out.println("Choose a subject");

                    listSubj = new ArrayList<>(subjects);
                    for (int i = 0; i < listSubj.size(); i++) {
                        System.out.println("Press " + (i + 1) + ". " + listSubj.get(i));

                    }
                    try {
                        choice = in.nextLine().charAt(0);
                        subject = listSubj.get(Character.getNumericValue(choice) - 1);
                        System.out.println("Choose a test");
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("Invalid input");
                        break;
                    }
                    for (Test t : tests) {
                        if (t.getSubj_name().equalsIgnoreCase(subject))
                            test_names.add(t.getTestName());
                    }

                    listTest_names = new ArrayList<>(test_names);

                    for (int i = 0; i < listTest_names.size(); i++) {
                        System.out.println("Press " + (i + 1) + ". " + listTest_names.get(i));
                    }
                    try {
                        choice = in.nextLine().charAt(0);
                        System.out.println("Choose an answer");
                        testName = listTest_names.get(Character.getNumericValue(choice) - 1);
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println("Invalid input");
                        break;
                    }

                    Answer answer = new Answer();
                    for (int i = 0; i < tests.size(); i++) {
                        if (tests.get(i).getSubj_name().equals(subject) && tests.get(i).getTestName().equals(testName)) {
                            System.out.println(tests.get(i).getQuestion());
                            if (i < tests.size() - 1) {
                                studAnswer.append(in.nextLine() + " ");
                            } else {
                                studAnswer.append(in.nextLine());
                            }
                        }

                        answer.setLogin(login);
                        answer.setSubj_name(subject);
                        answer.setTest_name(testName);
                        answer.setAnswer(studAnswer.toString());
                        answers.add(answer);
                    }

                    AddAnswerRequest addAnswerRequest = new AddAnswerRequest();
                    //Request registrationRequest = new RegistrationRequest();
                    addAnswerRequest.setCommandName("ADD_ANSWER");
                    addAnswerRequest.setAnswers(answers);

                    AddAnswerResponce addResponce = (AddAnswerResponce) controller.doRequest(addAnswerRequest);
                    System.out.print("Your answer: " + addResponce.getResult().get(0).getRight_answer());
                    System.out.print(" Correct answer: ");
                    for (int i = 0; i < addResponce.getResult().size(); i++)
                        System.out.print(addResponce.getResult().get(i).getWrong_answer() + " ");


                } else {
                    System.out.println("There are no tests for you");
                }
            }
            System.out.println("Test is completed");
            break;

        }

    }

    public StudentView() {
    }
}
