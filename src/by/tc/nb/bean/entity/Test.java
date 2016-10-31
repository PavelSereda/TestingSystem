package by.tc.nb.bean.entity;


public class Test {

    private String subj_name;
    private String testName;
    private String question;
    private String right_answer;

    public Test(String subj_name, String testName, String question, String right_answer) {
        this.subj_name = subj_name;
        this.testName = testName;
        this.question = question;
        this.right_answer = right_answer;
    }

    public String getSubj_name() {
        return subj_name;
    }

    public void setSubj_name(String subj_name) {
        this.subj_name = subj_name;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(String right_answer) {
        this.right_answer = right_answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (subj_name != null ? !subj_name.equals(test.subj_name) : test.subj_name != null) return false;
        if (testName != null ? !testName.equals(test.testName) : test.testName != null) return false;
        if (question != null ? !question.equals(test.question) : test.question != null) return false;
        return right_answer != null ? right_answer.equals(test.right_answer) : test.right_answer == null;

    }

    @Override
    public int hashCode() {
        int result = subj_name != null ? subj_name.hashCode() : 0;
        result = 31 * result + (testName != null ? testName.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (right_answer != null ? right_answer.hashCode() : 0);
        return result;
    }
}
