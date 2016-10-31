package by.tc.nb.bean;


import by.tc.nb.bean.entity.Result;

import java.util.ArrayList;
import java.util.List;

public class AddAnswerResponce extends Response {
    List<Result> result = new ArrayList<>();

    public AddAnswerResponce(List<Result> result) {
        this.result = result;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public AddAnswerResponce() {
    }
}
