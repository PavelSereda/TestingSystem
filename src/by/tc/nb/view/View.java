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

public class View {

    public static void main(String[] args) throws IOException, ParseException, ServiceException, ClassNotFoundException, DAOException {
        Scanner in = new Scanner(System.in);
        char choice;
        int loop = 1;
        while (loop == 1) {
            System.out.println("You are: \n 1. Student \n 2. Tutor \n 3. Exit");
            choice = in.nextLine().charAt(0);

            switch (choice) {
                case '1':
                    StudentView studentView = new StudentView();
                    studentView.view();
                    continue;
                case '2':
                    TutorView tv = new TutorView();
                    if (tv.isTutor()) {
                        tv.view();
                    }
                    continue;
                case '3':
                    loop = 0;
                    continue;
                default:
                    System.out.println("Wrong choice!");
                    continue;
            }

        }
    }
}
