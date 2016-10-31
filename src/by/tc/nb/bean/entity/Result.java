package by.tc.nb.bean.entity;


public class Result {
private String right_answer;
private String wrong_answer;

    public String getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(String right_answer) {
        this.right_answer = right_answer;
    }

    public String getWrong_answer() {
        return wrong_answer;
    }

    public void setWrong_answer(String wrong_answer) {
        this.wrong_answer = wrong_answer;
    }

    public Result(String right_answer, String wrong_answer) {
        this.right_answer = right_answer;
        this.wrong_answer = wrong_answer;
    }

    public Result() {
    }
}
