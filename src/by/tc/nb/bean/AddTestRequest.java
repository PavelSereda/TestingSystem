package by.tc.nb.bean;

import by.tc.nb.bean.entity.Test;

import java.util.ArrayList;
import java.util.List;

public class AddTestRequest extends Request {

    List<Test> test = new ArrayList<>();

    public List<Test> getTest() {
        return test;
    }

    public void setTest(List<Test> test) {
        this.test = test;
    }
}
