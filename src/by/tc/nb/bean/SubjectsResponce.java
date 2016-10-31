package by.tc.nb.bean;

import by.tc.nb.bean.entity.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SubjectsResponce extends Response {
    private ArrayList<Test> tests;

    public List<Test> getTests() {
        return tests;
    }


    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }
}
