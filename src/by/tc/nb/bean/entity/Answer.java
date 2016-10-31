package by.tc.nb.bean.entity;

/**
 * Created by Admin on 25.10.2016.
 */
public class Answer {
    private String login;
    private String subj_name;
    private String test_name;
    private String answer;

    public Answer(String login, String subj_name, String test_name, String answer) {
        this.login = login;
        this.subj_name = subj_name;
        this.test_name = test_name;
        this.answer = answer;
    }

    public Answer() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSubj_name(String subj_name) {
        this.subj_name = subj_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public String getLogin() {
        return login;
    }

    public String getSubj_name() {
        return subj_name;
    }

    public String getTest_name() {
        return test_name;
    }

    public String getAnswer() {
        return answer;
    }
}
