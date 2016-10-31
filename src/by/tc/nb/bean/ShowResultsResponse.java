package by.tc.nb.bean;


import by.tc.nb.bean.entity.Answer;

import java.util.ArrayList;
import java.util.List;

public class ShowResultsResponse extends Response {
    List<Answer> answers = new ArrayList<>();

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
